package br.com.teste.address.domain;

import br.com.teste.address.exception.CityInvalidException;
import br.com.teste.address.exception.CountryInvalidException;
import br.com.teste.address.exception.NeighbourhoodInvalidException;
import br.com.teste.address.exception.NumberInvalidException;
import br.com.teste.address.exception.StateInvalidException;
import br.com.teste.address.exception.StreetNameInvalidException;
import br.com.teste.address.exception.ZipCodeInvalidException;
import br.com.teste.api.v1.exception.NeighbourhoodInvalidAPIException;
import br.com.teste.api.v1.exception.StreetNameInvalidAPIException;
import org.springframework.util.StringUtils;

public class Address {

    private Long id;

    private String streetName;

    private Integer number;

    private String complement;

    private String neighbourhood;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    private Double latitude;

    private Double longitude;

    public void register(final String streetName, final Integer number, final String complement,
                         final String neighbourhood, final String city, final String state, final String country,
                         final String zipcode, final Double latitude, final Double longitude) {
        checkStreetName(streetName);
        this.streetName = streetName;
        checkNumber(number);
        this.number = number;
        this.complement = complement;
        checkNeighbourhood(neighbourhood);
        this.neighbourhood = neighbourhood;
        checkCity(city);
        this.city = city;
        checkState(state);
        this.state = state;
        checkCountry(country);
        this.country = country;
        checkZipcode(zipcode);
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void rehydrate(final Long id, final String streetName, final Integer number, final String complement,
                          final String neighbourhood, final String city, final String state, final String country,
                          final String zipcode, final Double latitude, final Double longitude) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    private void checkStreetName(String streetName) {
        if(isFieldEmpty(streetName)){
            throw new StreetNameInvalidException();
        }
    }

    private void checkNumber(Integer number) {
        if(isFieldEmpty(number)){
            throw new NumberInvalidException();
        }
    }

    private void checkNeighbourhood(String neighbourhood) {
        if(isFieldEmpty(neighbourhood)){
            throw new NeighbourhoodInvalidException();
        }
    }

    private void checkState(String state) {
        if(isFieldEmpty(state)){
            throw new StateInvalidException();
        }
    }

    private void checkCountry(String country) {
        if(isFieldEmpty(country)){
            throw new CountryInvalidException();
        }
    }

    private void checkZipcode(String zipcode) {
        if(isFieldEmpty(zipcode)){
            throw new ZipCodeInvalidException();
        }
    }

    private void checkCity(String city) {
        if(isFieldEmpty(city)){
            throw new CityInvalidException();
        }
    }

    private boolean isFieldEmpty(String streetName) {
        return StringUtils.isEmpty(streetName);
    }

    private boolean isFieldEmpty(Number field) {
        return field == null;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setCoordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }
}
