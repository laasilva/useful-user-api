package com.projecthellfire.core.port.adapter;

import com.projecthellfire.core.model.User;

import java.util.List;

public interface FindUserAdapter {

    List<User> findAll();
    User findByUsername(String username);
    User findById(Integer id);
}
