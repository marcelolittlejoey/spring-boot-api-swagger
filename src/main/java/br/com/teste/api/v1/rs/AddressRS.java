package br.com.teste.api.v1.rs;

import br.com.teste.address.service.IAddressService;
import br.com.teste.api.v1.input.CreateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class AddressRS {

    private final IAddressService addressService;

    public AddressRS(IAddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "Inseri um novo endereço.")
    @RequestMapping(name = "createAddress", path = "/enderecos", consumes = {"application/json"}, method =  RequestMethod.POST)
    public ResponseEntity createAddress(@RequestBody CreateAddressDTO createAddressDTO) {
        final Long id = addressService.insert(createAddressDTO);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera um endereco através do UUID.")
    @RequestMapping(name = "createAddress", path = "/enderecos/{id}", consumes = {"application/json"}, method =  RequestMethod.GET)
    public GetAddressDTO getAddress(@PathVariable("id") Long id) {
        return addressService.findById(id);
    }
}
