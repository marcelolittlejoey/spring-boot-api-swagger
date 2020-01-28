package br.com.teste.address.repository;

import br.com.teste.address.domain.Address;
import br.com.teste.address.repository.dto.LocationDTO;
import br.com.teste.api.v1.output.GetAddressDTO;

import java.util.Optional;

public interface IAddressRepository {

    Long insert(Address address);

    Optional<Address> findById(Long id);

    LocationDTO getCoordinates(Address address);

    void delete(Address address);
}
