package com.projecthellfire.datasource.adapter;

import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.datasource.DatasourceTestMocks.*;

@ExtendWith(SpringExtension.class)
class PersistUserTest {
    private final UserRepository repository = mock(UserRepository.class);
    private final UserEntityMapper mapper =  mock(UserEntityMapper.class);

    private final PersistUser persistUser = new PersistUser(repository, mapper);

    @Test
    void saveSuccess_test() {
        when(repository.save(any())).thenReturn(userEntityMock());
        when(mapper.toModel(any())).thenReturn(userModelMock());

        var response = persistUser.save(userModelMock());

        assertEquals(response, userModelMock());
    }
}
