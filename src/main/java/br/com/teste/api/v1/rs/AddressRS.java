package br.com.teste.api.v1.rs;

import br.com.teste.address.service.IAddressService;
import br.com.teste.api.v1.input.CreateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;
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
    public ResponseEntity createAddress(
        @ApiParam(value = "Dados do endereço", required = true)
        @RequestBody CreateAddressDTO createAddressDTO) {
        final Long id = addressService.insert(createAddressDTO);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera um endereco através do Id.")
    @RequestMapping(name = "createAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.GET)
    public GetAddressDTO getAddress(
        @ApiParam(value = "Id do endereço cadastrado", required = true)
        @PathVariable("id") Long id) {
        return addressService.findById(id);
    }

    @ApiOperation(value = "Exclui um endereco através do Id.")
    @RequestMapping(name = "createAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAddress(
        @ApiParam(value = "Id do endereço cadastrado", required = true)
        @PathVariable("id") Long id) {
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
