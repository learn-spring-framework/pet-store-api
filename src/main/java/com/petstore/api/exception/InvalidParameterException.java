package com.petstore.api.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(final String message) {
        super(message);
    }
}
