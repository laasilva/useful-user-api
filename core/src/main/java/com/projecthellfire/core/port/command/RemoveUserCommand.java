package com.projecthellfire.core.port.command;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;

public interface RemoveUserCommand {
    boolean delete(String username) throws PasswordEncryptionException;
}
