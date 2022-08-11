package com.projecthellfire.core.port.command;

import com.projecthellfire.core.exception.PasswordEncryptionException;

public interface RemoveUserCommand {
    boolean delete(String username) throws PasswordEncryptionException;
}
