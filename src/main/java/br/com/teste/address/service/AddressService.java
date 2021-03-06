package br.com.teste.address.service;

import br.com.teste.address.domain.Address;
import br.com.teste.address.exception.CityInvalidException;
import br.com.teste.address.exception.CountryInvalidException;
import br.com.teste.address.exception.NeighbourhoodInvalidException;
import br.com.teste.address.exception.NumberInvalidException;
import br.com.teste.address.exception.StateInvalidException;
import br.com.teste.address.exception.StreetNameInvalidException;
import br.com.teste.address.exception.ZipCodeInvalidException;
import br.com.teste.address.repository.IAddressRepository;
import br.com.teste.address.repository.dto.LocationDTO;
import br.com.teste.api.v1.exception.AddressNotFoundAPIException;
import br.com.teste.api.v1.exception.CityInvalidAPIException;
import br.com.teste.api.v1.exception.CountryInvalidAPIException;
import br.com.teste.api.v1.exception.NeighbourhoodInvalidAPIException;
import br.com.teste.api.v1.exception.NumberInvalidAPIException;
import br.com.teste.api.v1.exception.StateInvalidAPIException;
import br.com.teste.api.v1.exception.StreetNameInvalidAPIException;
import br.com.teste.api.v1.exception.ZipCodeInvalidAPIException;
import br.com.teste.api.v1.input.CreateUpdateAddressDTO;
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
    public Long insert(CreateUpdateAddressDTO createAddressDTO) {
        try {
            Address address = buildDomain(createAddressDTO);
            if(!hasCoordinates(address)){
                LocationDTO coordinates = addressRepository.getCoordinates(address);
                address.setCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
            }
            return addressRepository.insert(address);
        } catch (StreetNameInvalidException e){
            throw new StreetNameInvalidAPIException();
        } catch (CityInvalidException e){
            throw new CityInvalidAPIException();
        } catch (CountryInvalidException e){
            throw new CountryInvalidAPIException();
        } catch (NeighbourhoodInvalidException e){
            throw new NeighbourhoodInvalidAPIException();
        } catch (StateInvalidException e){
            throw new StateInvalidAPIException();
        } catch (NumberInvalidException e){
            throw new NumberInvalidAPIException();
        } catch (ZipCodeInvalidException e){
            throw new ZipCodeInvalidAPIException();
        }


    }

    private boolean hasCoordinates(Address address) {
        return address.getLatitude() != null && address.getLongitude() != null;
    }

    @Override
    public GetAddressDTO findById(Long id) {
        final Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return buildGetDTO(address.get());
        }
        throw new AddressNotFoundAPIException();
    }

    @Override
    public void delete(Long id) {
        final Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            addressRepository.delete(address.get());
            return;
        }
        throw new AddressNotFoundAPIException();
    }

    @Override
    public void update(Long id, CreateUpdateAddressDTO createUpdateAddressDTO) {
        final Optional<Address> oldAddress = addressRepository.findById(id);
        if(oldAddress.isPresent()){
            try {
                Address newAddress = buildDomain(createUpdateAddressDTO);
                if(!hasCoordinates(newAddress)){
                    LocationDTO coordinates = addressRepository.getCoordinates(newAddress);
                    newAddress.setCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
                }
                addressRepository.update(newAddress, id);
                return;
            } catch (StreetNameInvalidException e){
                throw new StreetNameInvalidAPIException();
            } catch (CityInvalidException e){
                throw new CityInvalidAPIException();
            } catch (CountryInvalidException e){
                throw new CountryInvalidAPIException();
            } catch (NeighbourhoodInvalidException e){
                throw new NeighbourhoodInvalidAPIException();
            } catch (StateInvalidException e){
                throw new StateInvalidAPIException();
            } catch (NumberInvalidException e){
                throw new NumberInvalidAPIException();
            } catch (ZipCodeInvalidException e){
                throw new ZipCodeInvalidAPIException();
            }
        }
        throw new AddressNotFoundAPIException();
    }

    private GetAddressDTO buildGetDTO(Address address) {
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

    private Address buildDomain(CreateUpdateAddressDTO createAddressDTO) {
        Address address = new Address();
        address.register(createAddressDTO.getStreetName(), createAddressDTO.getNumber(), createAddressDTO.getComplement(),
                createAddressDTO.getNeighbourhood(), createAddressDTO.getCity(), createAddressDTO.getState(),
                createAddressDTO.getCountry(), createAddressDTO.getZipcode(), createAddressDTO.getLatitude(),
                createAddressDTO.getLongitude());
        return address;
    }
}
