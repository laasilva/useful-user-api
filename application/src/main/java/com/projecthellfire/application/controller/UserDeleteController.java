package com.projecthellfire.application.controller;

import com.projecthellfire.application.dto.ResponseDto;
import com.projecthellfire.application.dto.response.UserPostResponse;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.port.command.RemoveUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserDeleteController {

    private final RemoveUserCommand removeUserCommand;
    private final UserDtoMapper mapper;

    @DeleteMapping("/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseDto> saveUser(@PathVariable String username) throws PasswordEncryptionException {
        log.info("Sending request for POST User endpoint...");

        removeUserCommand.delete(username);

        return ResponseEntity.ok(ResponseDto.builder()
                .data(UserPostResponse.builder()
                        .username(username)
                        .status(HttpStatus.OK.getReasonPhrase())
                        .build()).build());
    }
}
