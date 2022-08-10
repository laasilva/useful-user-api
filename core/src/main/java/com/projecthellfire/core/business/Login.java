package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.command.LoginCommand;
import com.projecthellfire.core.util.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Login implements LoginCommand {
    private final Validation validation;

    @Override
    public boolean login(User user) throws PasswordEncryptionException {
        return validation.verifyUserAndPassword(user.getUsername(), user.getPassword());
    }
}
