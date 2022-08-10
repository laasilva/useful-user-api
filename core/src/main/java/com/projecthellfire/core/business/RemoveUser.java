package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.exception.UserNotFoundException;
import com.projecthellfire.core.port.adapter.DeleteUserAdapter;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.command.RemoveUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.projecthellfire.core.exception.ErrorMessage.*;

@RequiredArgsConstructor
@Slf4j
public class RemoveUser implements RemoveUserCommand {
    private final DeleteUserAdapter delete;
    private final FindUserAdapter find;

    @Override
    public boolean delete(String username) throws PasswordEncryptionException {
        var user = find.findByUsername(username);

        if(user != null) {
            return delete.delete(user);
        } else {
            log.error(USER_NOT_FOUND_USERNAME.getMessage());

            throw new UserNotFoundException(USER_NOT_FOUND_USERNAME.getCode(),
                    USER_NOT_FOUND_USERNAME.getMessage());
        }
    }
}
