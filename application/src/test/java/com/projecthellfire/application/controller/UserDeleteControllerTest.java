package com.projecthellfire.application.controller;

import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.port.command.RemoveUserCommand;
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
import static com.projecthellfire.application.TestMocks.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserDeleteController.class)
@RunWith(SpringRunner.class)
public class UserDeleteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RemoveUserCommand removeUserCommand;
    @MockBean
    private UserDtoMapper mapper;

    @Test
    public void searchAllUsers2xx_test() throws Exception {
        when(removeUserCommand.delete(anyString())).thenReturn(true);

        var response = mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT + "/" + USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
