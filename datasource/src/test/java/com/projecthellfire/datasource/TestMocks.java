package com.projecthellfire.datasource;

import com.projecthellfire.core.model.User;
import com.projecthellfire.datasource.entity.UserEntity;

import java.util.List;

public class TestMocks {
    private final static Integer ID = 1;
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password123";

    // entities
    public static UserEntity userEntityMock() { return new UserEntity(ID, USERNAME, PASSWORD); }
    public static List<UserEntity> userEntityListMock() {
        return List.of(userEntityMock(), userEntityMock());
    }

    // models
    public static List<User> userModelListMock() { return List.of(userModelMock(), userModelMock()); }
    public static User userModelMock() {
        return User.builder().id(ID).username(USERNAME).password(PASSWORD).build();
    }
}
