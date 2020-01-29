package br.com.teste.api.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundAPIException extends RuntimeException {

    public AddressNotFoundAPIException() {
        super("Endereço não encontrado");
    }
}
