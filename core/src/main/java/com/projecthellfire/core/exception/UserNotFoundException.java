package com.projecthellfire.core.exception;

public class UserNotFoundException extends RuntimeException {
    private final Error error;

    public UserNotFoundException(String status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}
