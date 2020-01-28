package br.com.teste.address.exception;

public class ZipCodeInvalidException extends RuntimeException {

    public ZipCodeInvalidException() {
        super("Invalid zip code");
    }
}
