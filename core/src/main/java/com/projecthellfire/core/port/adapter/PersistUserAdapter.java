package com.projecthellfire.core.port.adapter;

import com.projecthellfire.core.model.User;

public interface PersistUserAdapter {
    User save(User user);
}