package com.projecthellfire.application.controller;

import com.projecthellfire.application.dto.ResponseDto;
import com.projecthellfire.application.mapper.UserDtoMapper;
import com.projecthellfire.core.port.command.SearchUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserGetController {

    private final SearchUserCommand searchUserCommand;
    private final UserDtoMapper mapper;

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseDto> searchAllUsers() {
        var users = searchUserCommand.findAll();

        if(!users.isEmpty())
            return ResponseEntity.ok(ResponseDto.builder().data(users.stream()
                        .map(mapper::toDto).collect(Collectors.toList())).build());
        else
            return ResponseEntity.ok(null);
    }

    @GetMapping("/search/username/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseDto> searchByUsername(@PathVariable String username) {
        var user = searchUserCommand.findByUsername(username);

        return ResponseEntity.ok(ResponseDto.builder()
                .data(mapper.toDto(user)).build());
    }

    @GetMapping("/search/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseDto> searchById(@PathVariable Integer id) {
        var user = searchUserCommand.findById(id);

        return ResponseEntity.ok(ResponseDto.builder()
                .data(mapper.toDto(user)).build());
    }
}
