package br.com.teste.address.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lng")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
