package com.projecthellfire.core.exception;

public enum ErrorMessage {

    USER_NOT_FOUND_USERNAME("404", "Username doesn't exist in the database."),
    USER_NOT_FOUND_ID("404", "Id doesn't exist in the database."),
    USER_EXISTS("409", "User already exists.");

    private final String code;
    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
