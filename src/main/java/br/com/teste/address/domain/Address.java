package br.com.teste.address.domain;

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

    public Long getId() {
        return id;
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
}
