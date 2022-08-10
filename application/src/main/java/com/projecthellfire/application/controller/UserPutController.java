package com.projecthellfire.application.controller;

import com.projecthellfire.application.dto.ResponseDto;
import com.projecthellfire.application.dto.request.NewPasswordRequest;
import com.projecthellfire.application.dto.request.UserRequest;
import com.projecthellfire.application.dto.response.UserPostResponse;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.port.command.EditPasswordCommand;
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
public class UserPutController {
    private final EditPasswordCommand editPasswordCommand;
    private final UserDtoMapper mapper;

    @PutMapping("/new-password/{username}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> editPassword(@RequestBody NewPasswordRequest request,
                                                    @PathVariable String username) throws PasswordEncryptionException {
        log.info("Sending request for PUT password endpoint...");

        editPasswordCommand.update(mapper.toModel(request), username);

        return ResponseEntity.ok(ResponseDto.builder()
                .data(UserPostResponse.builder()
                        .username(username)
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .build()).build());
    }
}
