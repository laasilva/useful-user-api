package com.projecthellfire.application.controller;

import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.port.command.SearchUserCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static com.projecthellfire.application.TestMocks.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserGetController.class)
@RunWith(SpringRunner.class)
class UserGetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDtoMapper mapper;
    @MockBean
    private SearchUserCommand searchUserCommand;

    @Test
    void searchAllUsers2xx_test() throws Exception {
        when(searchUserCommand.findAll()).thenReturn(userModelListMock());
        when(mapper.toDto(any())).thenReturn(userResponseMock());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + PATH_ALL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchAllUsers2xxEmpty_test() throws Exception {
        when(searchUserCommand.findAll()).thenReturn(List.of());
        when(mapper.toDto(any())).thenReturn(null);

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + PATH_ALL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchByUsername2xx_test() throws Exception {
        when(searchUserCommand.findByUsername(anyString())).thenReturn(userModelMock());
        when(mapper.toDto(any())).thenReturn(userResponseMock());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + PATH_SEARCH_USERNAME + USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void searchById2xx_test() throws Exception {
        when(searchUserCommand.findById(anyInt())).thenReturn(userModelMock());
        when(mapper.toDto(any())).thenReturn(userResponseMock());

        var response = mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + PATH_SEARCH_ID + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
