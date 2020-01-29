package br.com.teste.address.service;

import br.com.teste.address.domain.Address;
import br.com.teste.address.repository.IAddressRepository;
import br.com.teste.api.v1.exception.AddressNotFoundAPIException;
import br.com.teste.api.v1.exception.CityInvalidAPIException;
import br.com.teste.api.v1.exception.CountryInvalidAPIException;
import br.com.teste.api.v1.exception.NeighbourhoodInvalidAPIException;
import br.com.teste.api.v1.exception.NumberInvalidAPIException;
import br.com.teste.api.v1.exception.StateInvalidAPIException;
import br.com.teste.api.v1.exception.StreetNameInvalidAPIException;
import br.com.teste.api.v1.exception.ZipCodeInvalidAPIException;
import br.com.teste.api.v1.input.CreateUpdateAddressDTO;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class AddressServiceTest {

    @Test
    public void insert() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.insert(Mockito.any(Address.class))).thenReturn(1l);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", "South Beach",
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
        Mockito.verify(addressRepository, Mockito.times(1)).insert(Mockito.any(Address.class));
    }

    @Test(expected = StreetNameInvalidAPIException.class)
    public void insertWithoutStreetName() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO(null,1501,"Ap 4", "South Beach",
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = CityInvalidAPIException.class)
    public void insertWithoutCity() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", "South Beach",
                null, "FL", "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = CountryInvalidAPIException.class)
    public void insertWithoutCountry() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", "South Beach",
                "Miami Beach", "FL", null, "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = NeighbourhoodInvalidAPIException.class)
    public void insertWithoutNeighbourhood() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", null,
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = NumberInvalidAPIException.class)
    public void insertWithoutNumber() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",null,"Ap 4", "South Beach",
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = StateInvalidAPIException.class)
    public void insertWithoutState() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", "South Beach",
                "Miami Beach", null, "US", "33139", 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }

    @Test(expected = ZipCodeInvalidAPIException.class)
    public void insertWithoutZipCode() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Ocean Drive",1501,"Ap 4", "South Beach",
                "Miami Beach", "US", "US", null, 25.7616798, -80.1917902);
        AddressService addressService = new AddressService(addressRepository);
        addressService.insert(createUpdateAddressDTO);
    }


    @Test
    public void find() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Address address = new Address();
        address.register("Ocean Drive",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.of(address));

        AddressService addressService = new AddressService(addressRepository);
        addressService.findById(1l);
        Mockito.verify(addressRepository, Mockito.times(1)).findById(1l);
    }

    @Test(expected = AddressNotFoundAPIException.class)
    public void findWithInvalidId() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.empty());
        AddressService addressService = new AddressService(addressRepository);
        addressService.findById(1l);
    }

    @Test
    public void update() {
        Address address = new Address();
        address.register("Ocean Drive",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Collins",1501,"Ap 4", "South Beach",
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.of(address));
        AddressService addressService = new AddressService(addressRepository);
        addressService.update(1l, createUpdateAddressDTO);
        Mockito.verify(addressRepository, Mockito.times(1)).update(Mockito.any(Address.class),Mockito.anyLong());
    }

    @Test(expected = AddressNotFoundAPIException.class)
    public void updateWithInvalidId() {
        CreateUpdateAddressDTO createUpdateAddressDTO =
            new CreateUpdateAddressDTO("Collins",1501,"Ap 4", "South Beach",
                "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.empty());
        AddressService addressService = new AddressService(addressRepository);
        addressService.update(1l, createUpdateAddressDTO);
    }

    @Test
    public void delete() {
        Address address = new Address();
        address.register("Ocean Drive",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.of(address));
        AddressService addressService = new AddressService(addressRepository);
        addressService.delete(1l);
        Mockito.verify(addressRepository, Mockito.times(1)).delete(address);
    }

    @Test(expected = AddressNotFoundAPIException.class)
    public void deleteWithInvalidId() {
        IAddressRepository addressRepository = Mockito.mock(IAddressRepository.class);
        Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.empty());
        AddressService addressService = new AddressService(addressRepository);
        addressService.delete(1l);
    }
}
