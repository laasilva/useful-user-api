package com.projecthellfire.core.business;

import com.projecthellfire.core.port.adapter.FindUserAdapter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.core.CoreTestMocks.*;

class SearchUserTest {

    private final FindUserAdapter find = mock(FindUserAdapter.class);
    private final SearchUser searchUser = new SearchUser(find);

    @Test
    void findAllSuccess_test() {
        when(find.findAll()).thenReturn(userModelListMock());

        var response = searchUser.findAll();
        assertEquals(response, userModelListMock());
    }

    @Test
    void findByUsernameSuccess_test() {
        when(find.findByUsername(anyString())).thenReturn(userModelMock());

        var response = searchUser.findByUsername(anyString());
        assertEquals(response, userModelMock());
    }

    @Test
    void findByIdSuccess_test() {
        when(find.findById(anyInt())).thenReturn(userModelMock());

        var response = searchUser.findById(anyInt());
        assertEquals(response, userModelMock());
    }
}
