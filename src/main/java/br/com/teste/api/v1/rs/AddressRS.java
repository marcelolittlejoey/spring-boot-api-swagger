package br.com.teste.api.v1.rs;

import br.com.teste.address.service.IAddressService;
import br.com.teste.api.v1.input.CreateUpdateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;
import br.com.teste.api.v1.output.SuccessCreateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/api/v1/enderecos", description = "CRUD de endereços")
@RestController
@RequestMapping(path = "/api/v1/enderecos")
public class AddressRS {

    private final IAddressService addressService;

    public AddressRS(IAddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "Inseri um novo endereço.")
    @RequestMapping(name = "createAddress", consumes = {"application/json"}, method =  RequestMethod.POST)
    public ResponseEntity<SuccessCreateDTO> createAddress(
        @ApiParam(value = "Dados do endereço", required = true)
        @RequestBody CreateUpdateAddressDTO createUpdateAddressDTO) {
        final Long id = addressService.insert(createUpdateAddressDTO);
        return new ResponseEntity<>(new SuccessCreateDTO(id), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Recupera um endereco através do Id.")
    @RequestMapping(name = "getAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.GET)
    public GetAddressDTO getAddress(
        @ApiParam(value = "Id do endereço cadastrado", required = true)
        @PathVariable("id") Long id) {
        return addressService.findById(id);
    }

    @ApiOperation(value = "Atualiza um endereco através do Id.")
    @RequestMapping(name = "updateAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.PUT)
    public ResponseEntity updateAddress(
        @ApiParam(value = "Id do endereço cadastrado", required = true)
        @PathVariable("id") Long id,
        @ApiParam(value = "Novos dados do endereço", required = true)
        @RequestBody CreateUpdateAddressDTO createUpdateAddressDTO) {
        addressService.update(id, createUpdateAddressDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Exclui um endereco através do Id.")
    @RequestMapping(name = "deleteAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.DELETE)
    public ResponseEntity deleteAddress(
        @ApiParam(value = "Id do endereço cadastrado", required = true)
        @PathVariable("id") Long id) {
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
