package com.projecthellfire.core.exception;

public class ValidationException extends RuntimeException {
    private final Error error;

    public ValidationException(String status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}
