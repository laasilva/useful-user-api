package com.projecthellfire.application.mapper;

import com.projecthellfire.application.dto.ResponseDto;
import com.projecthellfire.core.exception.CoreException;
import com.projecthellfire.core.exception.Error;
import com.projecthellfire.core.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Error> handleCoreException(CoreException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
                Error(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Error>> handleUserNotFoundException(UserNotFoundException exception,
                                                                          WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.<Error>builder()
                        .data(List.of(exception.getErrorMessage()))
                .build());
    }
}
