package com.projecthellfire.core;

import com.projecthellfire.core.model.User;

import java.util.List;

public class TestMocks {
    private final static Integer ID = 1;
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password123";

    // models
    public static List<User> userModelListMock() { return List.of(userModelMock(), userModelMock()); }
    public static User userModelMock() {
        return User.builder().id(ID).username(USERNAME).password(PASSWORD).build();
    }
}
