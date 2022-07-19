package com.projecthellfire.core.exception;

public class UserExistsException  extends RuntimeException {
    private final Error error;

    public UserExistsException(String status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}
