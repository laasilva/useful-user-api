package com.projecthellfire.core.exception;

public enum ErrorMessage {

    USER_NOT_FOUND_USERNAME("404", "Username doesn't exist in the database."),
    USER_NOT_FOUND_ID("404", "Id doesn't exist in the database."),
    USER_PASSWORD_INCORRECT("422", "Incorrect password for matched user."),
    USER_EXISTS("409", "User already exists."),
    EMAIL_EXISTS("409", "Email address already in use."),
    USER_PASSWORD_NOT_A_MATCH("409", "User and/or password incorrect."),
    EMPTY_USER("422", "User cannot be empty."),
    EMPTY_EMAIL("422", "Email cannot be empty."),
    EMPTY_PASSWORD("422", "Password cannot be empty."),
    INVALID_EMAIL_PATTERN("422", "Email pattern is invalid."),
    INVALID_PASSWORD_PATTERN("422", "Password must contain at least 8 characters, " +
            "containing at least one letter, one number and one special character.");

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
