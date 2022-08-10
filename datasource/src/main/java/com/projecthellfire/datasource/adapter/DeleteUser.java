package com.projecthellfire.datasource.adapter;

import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.DeleteUserAdapter;
import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUser implements DeleteUserAdapter {
    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public boolean delete(User user) {
        repository.delete(mapper.toEntity(user));

        return true;
    }
}
