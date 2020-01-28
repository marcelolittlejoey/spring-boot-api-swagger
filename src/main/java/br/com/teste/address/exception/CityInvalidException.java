package br.com.teste.address.exception;

public class CityInvalidException extends RuntimeException {

    public CityInvalidException() {
        super("Invalid city");
    }
}
