package br.com.teste.address.exception;

public class StateInvalidException extends RuntimeException {

    public StateInvalidException() {
        super("Invalid state");
    }
}
