package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.exception.ValidationException;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.util.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.projecthellfire.core.TestMocks.*;

public class LoginTest {
    private final FindUserAdapter find = mock(FindUserAdapter.class);
    private final Validation validation = new Validation(find);
    private final Login login = new Login(validation);

    @Test
    public void loginSuccess_test() throws PasswordEncryptionException {
        when(find.findByUsername(any())).thenReturn(userModelEncryptedMock());

        var response = login.login(userModelMock());

        assertTrue(response);
    }

    @Test
    public void loginInvalidPassword_test() {
        when(find.findByUsername(any())).thenReturn(userModelMock());

        assertThrows(ValidationException.class, () -> login.login(userModelMock()));
    }

    @Test
    public void loginInvalidUsername_test() {
        when(find.findByUsername(any())).thenReturn(null);

        assertThrows(ValidationException.class, () -> login.login(userModelMock()));
    }
}
