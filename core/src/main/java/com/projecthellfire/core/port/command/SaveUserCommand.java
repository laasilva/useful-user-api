package com.projecthellfire.core.port.command;

import com.projecthellfire.core.model.User;

public interface SaveUserCommand {
    User save(User user);
}

