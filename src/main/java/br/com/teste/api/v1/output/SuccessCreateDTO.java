package br.com.teste.api.v1.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessCreateDTO {

    @JsonProperty("id")
    private Long id;

    public SuccessCreateDTO() {}

    public SuccessCreateDTO(Long id) {
        this.id = id;
    }
}
