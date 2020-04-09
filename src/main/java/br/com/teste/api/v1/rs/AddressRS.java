package br.com.teste.api.v1.rs;

import br.com.teste.address.service.IAddressService;
import br.com.teste.api.v1.input.CreateUpdateAddressDTO;
import br.com.teste.api.v1.output.GetAddressDTO;
import br.com.teste.api.v1.output.SuccessCreateDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "/api/v1/address", description = "Address CRUD")
@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressRS {

    private final IAddressService addressService;

    public AddressRS(IAddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "Insert a new address.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Address registered with success", response = SuccessCreateDTO.class),
        @ApiResponse(code = 400, message = "City field not informed. |"
            + " Country field not informed |"
            + " Neighbourhood field not informed |"
            + " Number field not informed |"
            + " State field not informed |"
            + " Street_name field not informed |"
            + " Zip_code field not informed")})
    @RequestMapping(name = "createAddress", consumes = {"application/json"}, method =  RequestMethod.POST)
    public ResponseEntity<SuccessCreateDTO> createAddress(
        @ApiParam(value = "Address data", required = true)
        @RequestBody CreateUpdateAddressDTO createUpdateAddressDTO) {
        final Long id = addressService.insert(createUpdateAddressDTO);
        return new ResponseEntity<>(new SuccessCreateDTO(id), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Read an address by Id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Address found", response = GetAddressDTO.class),
        @ApiResponse(code = 404, message = "Address not found")})
    @RequestMapping(name = "getAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.GET)
    public ResponseEntity<GetAddressDTO> getAddress(
        @ApiParam(value = "Address Id", required = true)
        @PathVariable("id") Long id) {
        GetAddressDTO getAddressDTO = addressService.findById(id);
        return new ResponseEntity<>(getAddressDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an address by Id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Address updated with success"),
        @ApiResponse(code = 404, message = "Address not found"),
        @ApiResponse(code = 400, message = "City field not informed. |"
                + " Country field not informed |"
                + " Neighbourhood field not informed |"
                + " Number field not informed |"
                + " State field not informed |"
                + " Street_name field not informed |"
                + " Zip_code field not informed")})
    @RequestMapping(name = "updateAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.PUT)
    public ResponseEntity updateAddress(
        @ApiParam(value = "Address Id", required = true)
        @PathVariable("id") Long id,
        @ApiParam(value = "Address new data", required = true)
        @RequestBody CreateUpdateAddressDTO createUpdateAddressDTO) {
        addressService.update(id, createUpdateAddressDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an address by Id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Address deleted with success", response = GetAddressDTO.class),
        @ApiResponse(code = 404, message = "Address not found")})
    @RequestMapping(name = "deleteAddress", path = "/{id}", consumes = {"application/json"}, method =
        RequestMethod.DELETE)
    public ResponseEntity deleteAddress(
        @ApiParam(value = "Address Id", required = true)
        @PathVariable("id") Long id) {
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
