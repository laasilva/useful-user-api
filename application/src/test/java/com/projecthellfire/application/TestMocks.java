package com.projecthellfire.application;

import com.projecthellfire.application.dto.request.UserRequest;
import com.projecthellfire.application.dto.response.UserResponse;
import com.projecthellfire.core.model.User;

import java.util.List;

public class TestMocks {
    public final static Integer ID = 1;
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password123";

    public final static String ENDPOINT = "/user";
    public final static String PATH_ALL = "/all";
    public final static String PATH_SEARCH_USERNAME = "/search/username/";
    public final static String PATH_SEARCH_ID = "/search/";
    public final static String PATH_NEW = "/new";

    // models
    public static List<User> userModelListMock() { return List.of(userModelMock(), userModelMock()); }
    public static User userModelMock() {
        return User.builder().id(ID).username(USERNAME).password(PASSWORD).build();
    }

    // request
    public static UserRequest userRequestMock() { return new UserRequest(USERNAME, PASSWORD); }

    // response
    public static UserResponse userResponseMock() { return new UserResponse(ID, USERNAME, PASSWORD); }
}
