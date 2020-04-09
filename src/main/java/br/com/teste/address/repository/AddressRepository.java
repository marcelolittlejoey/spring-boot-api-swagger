package br.com.teste.address.repository;

import br.com.teste.address.domain.Address;
import br.com.teste.address.entity.AddressEntity;
import br.com.teste.address.entity.AddressEntityBuilder;
import br.com.teste.address.repository.dto.LocationDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Repository
public class AddressRepository implements IAddressRepository {

    private static final String GOOGLE_SERVICE_KEY = "api.address.getCoordinates.key";
    private final String URI_GOOGLE_SERVICE = "api.address.getCoordinates";
    private static final Logger LOG = LoggerFactory.getLogger(AddressRepository.class);

    private final RestTemplate restTemplate;
    private final Environment env;
    private final ObjectMapper mapper;

    @PersistenceContext
    private EntityManager em;

    public AddressRepository(RestTemplate restTemplate, Environment env, ObjectMapper mapper, EntityManager em) {
        this.restTemplate = restTemplate;
        this.env = env;
        this.mapper = mapper;
        this.em = em;
    }

    @Override
    @Transactional
    public Long insert(Address address) {
        AddressEntity addressEntity = buildAddressEntity(address);
        em.persist(addressEntity);
        return addressEntity.getId();
    }

    @Override
    public Optional<Address> findById(Long id) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT address ");
        query.append(" FROM " + AddressEntity.class.getName() + " address ");
        query.append(" WHERE address.id = :id ");
        Query q = em.createQuery(query.toString());
        q.setParameter("id", id);

        try {
            AddressEntity addressEntity = (AddressEntity) q.getSingleResult();
            return buildDomainAddress(addressEntity);
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public LocationDTO getCoordinates(Address address) {
        try {
            final String uri = getUri(URI_GOOGLE_SERVICE);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("address", buildAddrees(address))
                .queryParam("key", getUri(GOOGLE_SERVICE_KEY));
            final ResponseEntity<String> response =
                restTemplate.exchange(builder.toUriString(), HttpMethod.GET, buildHttpEntity(), String.class);
            JsonNode rootNode = mapper.readTree(response.getBody());
            LocationDTO locationDTO = new ObjectMapper().readValue(rootNode.path("results").get(0)
                .path("geometry").path("location").toString(), LocationDTO.class);
            return locationDTO;
        } catch (Exception e) {
            LOG.error("Erro ao buscar coordenadas", e);
            throw new RuntimeException(e);
        }
    }

    private String buildAddrees(Address address) {
        return address.getNumber().toString()
            + " "
            + address.getStreetName()
            + ","
            + address.getNeighbourhood()
            + ","
            + address.getCity()
            + ","
            + address.getState();
    }

    @Override
    @Transactional
    public void delete(Address address) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT address ");
        query.append(" FROM " + AddressEntity.class.getName() + " address ");
        query.append(" WHERE address.id = :id ");
        Query q = em.createQuery(query.toString());
        q.setParameter("id", address.getId());

        try {
            AddressEntity addressEntity = (AddressEntity) q.getSingleResult();
            em.remove(addressEntity);
        } catch (NoResultException e){
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public void update(Address newAddress, Long id) {
        AddressEntity addressEntity = em.find(AddressEntity.class, id);
        updateAddressEntity(newAddress, addressEntity);
        em.merge(addressEntity);
    }

    private void updateAddressEntity(Address newAddress, AddressEntity addressEntity) {
        addressEntity.setCity(newAddress.getCity());
        addressEntity.setComplement(newAddress.getComplement());
        addressEntity.setCountry(newAddress.getCountry());
        addressEntity.setLatitude(newAddress.getLatitude());
        addressEntity.setLongitude(newAddress.getLongitude());
        addressEntity.setNeighbourhood(newAddress.getNeighbourhood());
        addressEntity.setNumber(newAddress.getNumber());
        addressEntity.setState(newAddress.getState());
        addressEntity.setZipcode(newAddress.getZipcode());
        addressEntity.setStreetName(newAddress.getStreetName());

    }

    private String getUri(String uriPropterty) {
        final String baseUri = env.getProperty(uriPropterty);
        if (StringUtils.isEmpty(baseUri)) {
            throw new IllegalArgumentException("buri base not configured");
        }
        return baseUri;
    }

    private String getKey(){
        final String baseUri = env.getProperty(GOOGLE_SERVICE_KEY);
        if (StringUtils.isEmpty(baseUri)) {
            throw new IllegalArgumentException("uri base not configured");
        }
        return baseUri;
    }

    private HttpEntity buildHttpEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(new LinkedMultiValueMap<>(), headers);
    }

    private Optional<Address> buildDomainAddress(AddressEntity addressEntity) {
        Address address = new Address();
        address.rehydrate(addressEntity.getId(), addressEntity.getStreetName(), addressEntity.getNumber(),
                addressEntity.getComplement(), addressEntity.getNeighbourhood(), addressEntity.getCity(),
                addressEntity.getState(), addressEntity.getCountry(), addressEntity.getZipcode(),
                addressEntity.getLatitude(), addressEntity.getLongitude());
        return Optional.of(address);
    }

    private AddressEntity buildAddressEntity(Address address) {
        return new AddressEntityBuilder().withCity(address.getCity())
                .withComplement(address.getComplement())
                .withCountry(address.getCountry())
                .withNeighbourhood(address.getNeighbourhood())
                .withNumber(address.getNumber())
                .withCity(address.getCity())
                .withState(address.getState())
                .withStreetName(address.getStreetName())
                .withLatitude(address.getLatitude())
                .withLongitude(address.getLongitude())
                .withZipcode(address.getZipcode())
                .createAddressEntity();
    }
}