package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.exception.ValidationException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.util.Validation;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.core.CoreTestMocks.*;

class SaveUserTest {
    private final PersistUserAdapter persist = mock(PersistUserAdapter.class);
    private final FindUserAdapter find = mock(FindUserAdapter.class);
    private final Validation validation = new Validation(find);
    private final SaveUser saveUser = new SaveUser(persist, validation);

    @Test
    void saveSuccess_test() throws PasswordEncryptionException {
        when(find.findByUsername(any())).thenReturn(null);
        when(persist.save(any())).thenReturn(userModelMock());

        var response = saveUser.save(userModelMock());

        assertEquals(response, userModelMock());
    }

    @Test
    void saveUserExists_test() {
        when(find.findByUsername(any())).thenReturn(userModelMock());

        assertThrows(ValidationException.class, () -> saveUser.save(userModelMock()));
    }

    @Test
    void saveUserEmailExists_test() {
        when(find.findByEmail(any())).thenReturn(userModelMock());

        assertThrows(ValidationException.class, () -> saveUser.save(userModelMock()));
    }

    @Test
    void saveUserNull_test() {
        assertThrows(ValidationException.class, () -> saveUser.save(User.builder()
                        .username("")
                        .email("")
                        .password("")
                .build()));
    }

    @Test
    void saveEmailPatternWrong_test() {
        when(find.findByUsername(any())).thenReturn(null);

        assertThrows(ValidationException.class, () -> saveUser.save(User.builder()
                        .username("test")
                        .email("test")
                        .password("test")
                .build()));
    }

}
