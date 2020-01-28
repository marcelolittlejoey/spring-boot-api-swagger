package br.com.teste.api.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CountryInvalidAPIException extends RuntimeException {

    public CountryInvalidAPIException() {
        super("Campo country não informado");
    }
}
