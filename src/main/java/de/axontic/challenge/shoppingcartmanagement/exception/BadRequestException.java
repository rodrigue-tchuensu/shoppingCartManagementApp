package de.axontic.challenge.shoppingcartmanagement.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
