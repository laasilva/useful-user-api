package com.projecthellfire.core.port.command;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;

public interface LoginCommand {
    boolean login(User user) throws PasswordEncryptionException;
}
