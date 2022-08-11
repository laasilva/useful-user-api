package com.projecthellfire.datasource.adapter;

import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.projecthellfire.datasource.DatasourceTestMocks.userModelMock;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeleteUserTest {
    private final UserRepository repository = mock(UserRepository.class);
    private final UserEntityMapper mapper =  mock(UserEntityMapper.class);
    private final DeleteUser deleteUser = new DeleteUser(repository, mapper);


    @Test
    void saveSuccess_test() {

        when(mapper.toModel(any())).thenReturn(userModelMock());

        var response = deleteUser.delete(userModelMock());

        assertTrue(response);
    }
}
