package br.com.teste.api.v1.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class GetAddressDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("street_name")
    private String streetName;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("neighbourhood")
    private String neighbourhood;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zip_code")
    private String zipcode;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    public GetAddressDTO(Long id, String streetName, Integer number, String complement, String neighbourhood,
                         String city, String state, String country, String zipcode, Double latitude, Double longitude) {
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
}
