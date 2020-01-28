package br.com.teste.api.v1.output;

public class GetAddressDTOBuilder {
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

    public GetAddressDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public GetAddressDTOBuilder withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public GetAddressDTOBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public GetAddressDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public GetAddressDTOBuilder withNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
        return this;
    }

    public GetAddressDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public GetAddressDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public GetAddressDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public GetAddressDTOBuilder withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public GetAddressDTOBuilder withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public GetAddressDTOBuilder withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public GetAddressDTO createGetAddressDTO() {
        return new GetAddressDTO(id, streetName, number, complement, neighbourhood, city, state, country, zipcode, latitude, longitude);
    }
}