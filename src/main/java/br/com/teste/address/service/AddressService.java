package br.com.teste.address.service;

import br.com.teste.address.domain.Address;
import br.com.teste.address.repository.IAddressRepository;
import br.com.teste.address.repository.dto.LocationDTO;
import br.com.teste.api.v1.input.CreateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTOBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    private final IAddressRepository addressRepository;

    public AddressService(final IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Long insert(CreateAddressDTO createAddressDTO) {
        Address address = buildDomain(createAddressDTO);
        if(!hasCoordinates(address)){
            LocationDTO coordinates = addressRepository.getCoordinates(address);
            address.setCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
        }
        return addressRepository.insert(address);
    }

    private boolean hasCoordinates(Address address) {
        return address.getLatitude() != null && address.getLongitude() != null;
    }

    @Override
    public GetAddressDTO findById(Long id) {
        final Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return builGetDTO(address.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        final Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            addressRepository.delete(address.get());
        }
    }

    private GetAddressDTO builGetDTO(Address address) {
        return new GetAddressDTOBuilder()
                .withId(address.getId())
                .withCity(address.getCity())
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
                .createGetAddressDTO();
    }

    private Address buildDomain(CreateAddressDTO createAddressDTO) {
        Address address = new Address();
        address.register(createAddressDTO.getStreetName(), createAddressDTO.getNumber(), createAddressDTO.getComplement(),
                createAddressDTO.getNeighbourhood(), createAddressDTO.getCity(), createAddressDTO.getState(),
                createAddressDTO.getCountry(), createAddressDTO.getZipcode(), createAddressDTO.getLatitude(),
                createAddressDTO.getLongitude());
        return address;
    }
}
