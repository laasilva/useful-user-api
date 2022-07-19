package com.projecthellfire.datasource.adapter;

import com.projecthellfire.core.exception.UserNotFoundException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.datasource.TestMocks;
import com.projecthellfire.datasource.entity.UserEntity;
import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.datasource.TestMocks.*;

@ExtendWith(SpringExtension.class)
public class FindUserTest {

    private final UserRepository repository = mock(UserRepository.class);
    private final UserEntityMapper mapper = mock(UserEntityMapper.class);
    private final FindUser findUser = new FindUser(repository, mapper);

    @Test
    public void findAllSuccess_test() {
        when(repository.findAll()).thenReturn(userEntityListMock());
        when(mapper.toModel(any())).thenReturn(userModelMock());

        var response = findUser.findAll();

        assertEquals(response, userModelListMock());
    }

    @Test
    public void findByUsernameSuccess_test() {
        var entity = userEntityMock();

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(userModelMock());

        var response = findUser.findByUsername(anyString());

        assertEquals(response, userModelMock());
    }

    @Test
    public void findByUsernameNull_test() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertNull(findUser.findByUsername(anyString()));
    }

    @Test
    public void findByIdSuccess_test() {
        var entity = userEntityMock();

        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(userModelMock());

        var response = findUser.findById(anyInt());

        assertEquals(response, userModelMock());
    }

    @Test
    public void findByIdException_test() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> findUser.findById(anyInt()));
    }
}
