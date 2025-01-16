package de.axontic.challenge.shoppingcartmanagement.exception;

public class UnexpectedServerException extends RuntimeException {
    public UnexpectedServerException() {
        super();
    }

    public UnexpectedServerException(String message) {
        super(message);
    }
}
