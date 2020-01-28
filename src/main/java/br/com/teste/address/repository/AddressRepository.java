package br.com.teste.address.repository;

import br.com.teste.address.domain.Address;
import br.com.teste.address.entity.AddressEntity;
import br.com.teste.address.entity.AddressEntityBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class AddressRepository implements IAddressRepository {

    @PersistenceContext
    private EntityManager em;

    public AddressRepository(EntityManager em) {
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