package br.com.teste.address.domain;

import br.com.teste.address.exception.CityInvalidException;
import br.com.teste.address.exception.CountryInvalidException;
import br.com.teste.address.exception.NeighbourhoodInvalidException;
import br.com.teste.address.exception.NumberInvalidException;
import br.com.teste.address.exception.StateInvalidException;
import br.com.teste.address.exception.StreetNameInvalidException;
import br.com.teste.address.exception.ZipCodeInvalidException;
import org.junit.Test;

public class AddressTest {

    @Test(expected = StreetNameInvalidException.class)
    public void insertWithoutStreetName() {
        Address address = new Address();
        address.register(null,1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = CityInvalidException.class)
    public void insertWithoutCity() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", "South Beach",
            null, "FL", "US", "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = CountryInvalidException.class)
    public void insertWithoutCountry() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", null, "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = NeighbourhoodInvalidException.class)
    public void insertWithoutNeighbourhood() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", null,
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = NumberInvalidException.class)
    public void insertWithoutNumber() {
        Address address = new Address();
        address.register("Collins Ave",null,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = StateInvalidException.class)
    public void insertWithoutState() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", "South Beach",
            "Miami Beach", null, "US", "33139", 25.7616798, -80.1917902);
    }

    @Test(expected = ZipCodeInvalidException.class)
    public void insertWithoutZipCode() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", null, 25.7616798, -80.1917902);
    }

    @Test()
    public void insertWithRequiredFields() {
        Address address = new Address();
        address.register("Collins Ave",1501,"Ap 4", "South Beach",
            "Miami Beach", "FL", "US", "33139", 25.7616798, -80.1917902);
    }
}
