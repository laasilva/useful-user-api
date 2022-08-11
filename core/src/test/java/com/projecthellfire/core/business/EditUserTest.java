package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.util.Validation;
import org.junit.jupiter.api.Test;

import static com.projecthellfire.core.CoreTestMocks.getUSERNAME;
import static com.projecthellfire.core.CoreTestMocks.userModelMock;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EditUserTest {
    private final PersistUserAdapter persist = mock(PersistUserAdapter.class);
    private final FindUserAdapter find = mock(FindUserAdapter.class);
    private final Validation validation = new Validation(find);
    private final EditUser editUser = new EditUser(persist, find, validation);

    @Test
    void editSuccess_test() throws PasswordEncryptionException {
        when(find.findByUsername(any())).thenReturn(userModelMock());
        when(persist.save(any())).thenReturn(userModelMock());

        var response = editUser.update(userModelMock(), getUSERNAME());

        assertEquals(response, userModelMock());
    }
}
