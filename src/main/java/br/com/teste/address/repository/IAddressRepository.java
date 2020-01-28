package br.com.teste.address.repository;

import br.com.teste.address.domain.Address;

import java.util.Optional;

public interface IAddressRepository {

    Long insert(Address address);

    Optional<Address> findById(Long id);
}
