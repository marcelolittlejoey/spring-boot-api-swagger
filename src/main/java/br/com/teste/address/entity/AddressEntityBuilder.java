package br.com.teste.address.entity;

public class AddressEntityBuilder {
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

    public AddressEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressEntityBuilder withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public AddressEntityBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public AddressEntityBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressEntityBuilder withNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public AddressEntityBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressEntityBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressEntityBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressEntityBuilder withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public AddressEntityBuilder withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public AddressEntityBuilder withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public AddressEntity createAddressEntity() {
        return new AddressEntity(id, streetName, number, complement, neighbourhood, city, state, country, zipcode, latitude, longitude);
    }
}