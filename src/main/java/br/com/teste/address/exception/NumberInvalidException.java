package br.com.teste.address.exception;

public class NumberInvalidException extends RuntimeException {

    public NumberInvalidException() {
        super("Invalid number");
    }
}
