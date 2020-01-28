package br.com.teste.address.exception;

public class NeighbourhoodInvalidException extends RuntimeException {

    public NeighbourhoodInvalidException() {
        super("Invalid neighbourhood");
    }
}
