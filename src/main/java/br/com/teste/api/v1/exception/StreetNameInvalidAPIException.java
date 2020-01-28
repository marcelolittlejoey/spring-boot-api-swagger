package br.com.teste.api.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StreetNameInvalidAPIException extends RuntimeException {

    public StreetNameInvalidAPIException() {
        super("Campo street_name não informado");
    }
}
