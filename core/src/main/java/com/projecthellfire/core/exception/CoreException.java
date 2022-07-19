package com.projecthellfire.core.exception;

public class CoreException extends RuntimeException {
    private final Error error;

    public CoreException(String status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}
