package com.projecthellfire.core.port.command;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.model.User;

public interface EditPasswordCommand {
    User update(User user, String username) throws PasswordEncryptionException;
}
