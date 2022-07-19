package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.UserExistsException;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.core.TestMocks.*;

public class SaveUserTest {
    private final PersistUserAdapter persist = mock(PersistUserAdapter.class);
    private final FindUserAdapter find = mock(FindUserAdapter.class);

    private final SaveUser saveUser = new SaveUser(persist, find);

    @Test
    public void saveSuccess_test() {
        when(find.findByUsername(any())).thenReturn(null);
        when(persist.save(any())).thenReturn(userModelMock());

        var response = saveUser.save(userModelMock());

        assertEquals(response, userModelMock());
    }

    @Test
    public void saveUserExists_test() {
        when(find.findByUsername(any())).thenReturn(userModelMock());

        assertThrows(UserExistsException.class, () -> saveUser.save(userModelMock()));
    }

}
