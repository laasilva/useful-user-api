package com.projecthellfire.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.port.command.LoginCommand;
import com.projecthellfire.core.port.command.SaveUserCommand;
import com.projecthellfire.core.port.command.SearchUserCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static com.projecthellfire.application.ApplicationTestMocks.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserPostController.class)
@RunWith(SpringRunner.class)
class UserPostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDtoMapper mapper;
    @MockBean
    private SaveUserCommand saveUserCommand;
    @MockBean
    private SearchUserCommand searchUserCommand;
    @MockBean
    private LoginCommand loginCommand;

    @Test
    void save2xx_test() throws Exception {
        var userRequest = userRequestMock();

        when(mapper.toDto(any())).thenReturn(userResponseMock());

        ObjectMapper jsonMapper = new ObjectMapper();

        var response = mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT + PATH_NEW)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(userRequest))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void login2xx_test() throws Exception {
        var loginRequest = loginRequestMock();

        when(mapper.toDto(any())).thenReturn(userResponseMock());

        ObjectMapper jsonMapper = new ObjectMapper();

        var response = mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT + PATH_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(loginRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
