package org.tripgain.Exception;

public class InvalidFlightNumberException extends RuntimeException {
    public InvalidFlightNumberException(String message) {
        super(message);
    }
}
