package br.com.teste.address.exception;

public class CountryInvalidException extends RuntimeException {

    public CountryInvalidException() {
        super("Invaid country");
    }
}
