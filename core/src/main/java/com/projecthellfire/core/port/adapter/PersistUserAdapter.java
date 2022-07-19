package com.projecthellfire.core.port.adapter;

import com.projecthellfire.core.model.User;

import java.util.List;

public interface PersistUserAdapter {
    User save(User user);
}