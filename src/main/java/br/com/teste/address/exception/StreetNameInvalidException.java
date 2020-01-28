package br.com.teste.address.exception;

public class StreetNameInvalidException extends RuntimeException {

    public StreetNameInvalidException() {
        super("Invalid street name");
    }
}
