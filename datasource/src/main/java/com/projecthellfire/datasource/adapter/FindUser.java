package com.projecthellfire.datasource.adapter;

import com.projecthellfire.core.exception.ErrorMessage;
import com.projecthellfire.core.exception.UserNotFoundException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.datasource.mapper.UserEntityMapper;
import com.projecthellfire.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUser implements FindUserAdapter {
    private final UserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public List<User> findAll() {
        var entity = repository.findAll();

        return entity.stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        var entity = repository.findByUsername(username);

        if(entity.isEmpty())
            return null;

        return mapper.toModel(entity.get());
    }

    @Override
    public User findByEmail(String email) {
        var entity = repository.findByEmail(email);

        if(entity.isEmpty())
            return null;

        return mapper.toModel(entity.get());
    }

    @Override
    public User findById(Integer id) {
        var entity = repository.findById(id);

        if(entity.isEmpty())
            throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND_ID.getCode(),
                    ErrorMessage.USER_NOT_FOUND_ID.getMessage());

        return mapper.toModel(entity.get());
    }
}
