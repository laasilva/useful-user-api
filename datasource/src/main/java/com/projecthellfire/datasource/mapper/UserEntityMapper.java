package com.projecthellfire.datasource.mapper;

import com.projecthellfire.core.model.User;
import com.projecthellfire.datasource.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toModel(UserEntity entity);
    UserEntity toEntity(User model);
}
