package com.projecthellfire.core.exception;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionException extends NoSuchAlgorithmException {
    private final Error error;

    public PasswordEncryptionException(String status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public PasswordEncryptionException(String message) {
        super();
        this.error = new Error("500", message);
    }

    public Error getErrorMessage() {
        return error;
    }
}
