package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.port.command.EditPasswordCommand;
import com.projecthellfire.core.util.PasswordUtil;
import com.projecthellfire.core.util.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class EditUser implements EditPasswordCommand {
    private final PersistUserAdapter persist;
    private final FindUserAdapter find;
    private final Validation validation;
    @Override
    public User update(User user, String username) throws PasswordEncryptionException {
        User oldUser = find.findByUsername(username);

        validation.verifyValidPassword(user.getPassword());

        user.setId(oldUser.getId());
        user.setUsername(oldUser.getUsername());
        user.setEmail(oldUser.getEmail());
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));

        return persist.save(user);
    }
}
