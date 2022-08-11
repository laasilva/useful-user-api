package com.projecthellfire.datasource.adapter;

import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersistUser implements PersistUserAdapter {

    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User save(User user) {
        final var entity = repository.save(mapper.toEntity(user));
        return mapper.toModel(entity);
    }
}
