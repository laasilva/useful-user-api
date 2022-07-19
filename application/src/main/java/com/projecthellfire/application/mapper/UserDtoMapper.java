package com.projecthellfire.application.mapper;

import com.projecthellfire.application.dto.request.UserRequest;
import com.projecthellfire.application.dto.response.UserResponse;
import com.projecthellfire.core.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User toModel(UserRequest request);
    UserResponse toDto(User user);
}
