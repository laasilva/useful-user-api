package com.projecthellfire.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.port.command.EditPasswordCommand;
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
@WebMvcTest(controllers = UserPutController.class)
@RunWith(SpringRunner.class)
class UserPutControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDtoMapper mapper;
    @MockBean
    private EditPasswordCommand editPasswordCommand;

    @Test
    void searchAllUsers2xx_test() throws Exception {
        when(editPasswordCommand.update(any(), anyString())).thenReturn(userModelMock());
        when(mapper.toDto(any())).thenReturn(userResponseMock());

        ObjectMapper jsonMapper = new ObjectMapper();

        var response = mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT + PATH_PASSWORD + "/" + USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(newPasswordRequestMock()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
