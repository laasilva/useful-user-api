package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.port.command.SaveUserCommand;
import com.projecthellfire.core.util.PasswordUtil;
import com.projecthellfire.core.util.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SaveUser implements SaveUserCommand {
    private final PersistUserAdapter persist;
    private final Validation validation;
    @Override
    public User save(User user) throws PasswordEncryptionException {
        validation.verifyUser(user);

        user.setPassword(PasswordUtil.encrypt(user.getPassword()));

        return persist.save(user);
    }
}
