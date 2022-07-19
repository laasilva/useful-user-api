package com.projecthellfire.core.business;

import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.command.SearchUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class SearchUser implements SearchUserCommand {
    private final FindUserAdapter find;

    @Override
    public List<User> findAll() {
        return find.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return find.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return find.findById(id);
    }
}
