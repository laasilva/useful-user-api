package com.projecthellfire.core;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.util.PasswordUtil;

import java.util.List;

public class TestMocks {
    private final static Integer ID = 1;
    private final static String USERNAME = "username";
    private final static String EMAIL = "username@teste.com";
    private final static String PASSWORD = "password123@!";

    // models
    public static List<User> userModelListMock() { return List.of(userModelMock(), userModelMock()); }
    public static User userModelMock() {
        return User.builder().id(ID).username(USERNAME).email(EMAIL).password(PASSWORD).build();
    }
    public static User userModelEncryptedMock() throws PasswordEncryptionException {
        return User.builder().id(ID).username(USERNAME).email(EMAIL).password(PasswordUtil.encrypt(PASSWORD)).build();
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
