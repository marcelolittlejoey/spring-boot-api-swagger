package br.com.teste.api.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CityInvalidAPIException extends RuntimeException {

    public CityInvalidAPIException() {
        super("Campo city não informado");
    }
}
