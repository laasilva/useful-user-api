package com.projecthellfire.application.controller;

import com.projecthellfire.application.dto.request.UserRequest;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.port.command.SaveUserCommand;
import com.projecthellfire.core.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserPostController {
    private final SaveUserCommand saveUserCommand;
    private final UserDtoMapper mapper;

    @PostMapping("/new")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest request) throws PasswordEncryptionException {
        log.info("Sending request for test POST User endpoint...");

        request.setPassword(PasswordUtil.encrypt(request.getPassword()));

        saveUserCommand.save(mapper.toModel(request));
    }
}
