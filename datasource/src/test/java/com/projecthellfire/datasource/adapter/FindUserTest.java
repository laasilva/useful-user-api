package com.projecthellfire.datasource.adapter;

import com.projecthellfire.core.exception.UserNotFoundException;
import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.datasource.DatasourceTestMocks.*;

@ExtendWith(SpringExtension.class)
class FindUserTest {

    private final UserRepository repository = mock(UserRepository.class);
    private final UserEntityMapper mapper = mock(UserEntityMapper.class);
    private final FindUser findUser = new FindUser(repository, mapper);

    @Test
    void findAllSuccess_test() {
        when(repository.findAll()).thenReturn(userEntityListMock());
        when(mapper.toModel(any())).thenReturn(userModelMock());

        var response = findUser.findAll();

        assertEquals(response, userModelListMock());
    }

    @Test
    void findByUsernameSuccess_test() {
        var entity = userEntityMock();

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(userModelMock());

        var response = findUser.findByUsername(anyString());

        assertEquals(response, userModelMock());
    }

    @Test
    void findByUsernameNull_test() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertNull(findUser.findByUsername(anyString()));
    }

    @Test
    void findByIdSuccess_test() {
        var entity = userEntityMock();

        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(userModelMock());

        var response = findUser.findById(anyInt());

        assertEquals(response, userModelMock());
    }

    @Test
    void findByIdException_test() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> findUser.findById(anyInt()));
    }
}
