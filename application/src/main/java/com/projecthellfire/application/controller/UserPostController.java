package com.projecthellfire.application.controller;

import com.projecthellfire.application.dto.ResponseDto;
import com.projecthellfire.application.dto.request.LoginRequest;
import com.projecthellfire.application.dto.request.UserRequest;
import com.projecthellfire.application.dto.response.UserPostResponse;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.port.command.LoginCommand;
import com.projecthellfire.core.port.command.SaveUserCommand;
import com.projecthellfire.core.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserPostController {
    private final SaveUserCommand saveUserCommand;
    private final LoginCommand loginCommand;
    private final UserDtoMapper mapper;

    @PostMapping("/new")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> saveUser(@RequestBody UserRequest request) throws PasswordEncryptionException {
        log.info("Sending request for POST User endpoint...");

        saveUserCommand.save(mapper.toModel(request));

        return new ResponseEntity<ResponseDto>(ResponseDto.builder()
                .data(UserPostResponse.builder()
                        .username(request.getUsername())
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .build()).build(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest request) throws PasswordEncryptionException {
        log.info("Sending request for POST login endpoint...");

        var response = loginCommand.login(mapper.toModel(request));

        return ResponseEntity.ok(ResponseDto.builder()
                .data(UserPostResponse.builder()
                        .username(request.getUsername())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .build()).build());
    }
}
