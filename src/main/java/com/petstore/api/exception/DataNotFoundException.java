package com.petstore.api.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(final String message) {
        super(message);
    }
}
