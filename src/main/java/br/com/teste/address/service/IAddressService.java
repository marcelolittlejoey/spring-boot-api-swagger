package br.com.teste.address.service;

import br.com.teste.api.v1.input.CreateUpdateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;

public interface IAddressService {

    Long insert(CreateUpdateAddressDTO createUpdateAddressDTO);

    GetAddressDTO findById(Long id);

    void delete(Long id);

    void update(Long id, CreateUpdateAddressDTO createUpdateAddressDTO);
}
