package com.projecthellfire.core.port.command;

import com.projecthellfire.core.model.User;

import java.util.List;

public interface SearchUserCommand {
    List<User> findAll();
    User findByUsername(String username);
    User findById(Integer id);
}
