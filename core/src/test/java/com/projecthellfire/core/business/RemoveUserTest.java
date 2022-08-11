package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.exception.UserNotFoundException;
import com.projecthellfire.core.port.adapter.DeleteUserAdapter;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.projecthellfire.core.CoreTestMocks.*;

class RemoveUserTest {

    private final DeleteUserAdapter delete = mock(DeleteUserAdapter.class);
    private final FindUserAdapter find = mock(FindUserAdapter.class);
    private final RemoveUser remove = new RemoveUser(delete, find);

    @Test
    void saveSuccess_test() throws PasswordEncryptionException {
        when(find.findByUsername(any())).thenReturn(userModelMock());
        when(delete.delete(userModelMock())).thenReturn(true);

        var response = remove.delete(getUSERNAME());

        assertTrue(response);
    }

    @Test
    void saveException_test() throws PasswordEncryptionException {
        when(find.findByUsername(any())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> remove.delete(getUSERNAME()));
    }
}
