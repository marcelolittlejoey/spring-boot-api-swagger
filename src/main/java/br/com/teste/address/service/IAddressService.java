package br.com.teste.address.service;

import br.com.teste.api.v1.input.CreateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;

public interface IAddressService {

    Long insert(CreateAddressDTO createAddressDTO);

    GetAddressDTO findById(Long id);

    void delete(Long id);
}
