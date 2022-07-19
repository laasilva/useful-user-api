package com.projecthellfire.core.business;

import com.projecthellfire.core.exception.ErrorMessage;
import com.projecthellfire.core.exception.UserExistsException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.port.command.SaveUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class SaveUser implements SaveUserCommand {
    private final PersistUserAdapter persist;
    private final FindUserAdapter find;
    @Override
    public User save(User user) {
        var exists = verifyUser(user.getUsername());

        if(exists)
            throw new UserExistsException(ErrorMessage.USER_EXISTS.getCode(),
                    ErrorMessage.USER_EXISTS.getMessage());
        return persist.save(user);
    }

    private boolean verifyUser(String username) {
        var user = find.findByUsername(username);

        return user != null;
    }
}
