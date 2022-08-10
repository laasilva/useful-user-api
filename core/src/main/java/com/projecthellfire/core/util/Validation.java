package com.projecthellfire.core.util;

import com.projecthellfire.core.exception.Error;
import com.projecthellfire.core.exception.PasswordEncryptionException;
import com.projecthellfire.core.exception.ValidationException;
import com.projecthellfire.core.model.User;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.projecthellfire.core.exception.ErrorMessage.*;

@RequiredArgsConstructor
@Slf4j
public class Validation {
    private final FindUserAdapter find;

    public void verifyUser(User user) {
        verifyNull(user);

        var model = find.findByUsername(user.getUsername());

        log.info("Verifying if username exists...");
        if(model != null) {
            log.error(String.format("Username \"%s\" already exists.", user.getUsername()));

            throw new ValidationException(USER_EXISTS.getCode(),
                    USER_EXISTS.getMessage());
        }

        verifyEmailPattern(user.getEmail());

        model = find.findByEmail(user.getEmail());

        log.info("Verifying if email exists...");
        if(model != null) {
            log.error(String.format("email \"%s\" address already in use.", user.getEmail()));

            throw new ValidationException(EMAIL_EXISTS.getCode(),
                    EMAIL_EXISTS.getMessage());
        }

        verifyValidPassword(user.getPassword());

        log.info("User validation complete.");
    }
    public boolean verifyUserAndPassword(String username, String password) throws PasswordEncryptionException {
        var user = find.findByUsername(username);

        log.info("Verifying if username exists...");

        if(user != null) {
            var correctPassword = PasswordUtil.encrypt(password).equals(user.getPassword());

            if(correctPassword) {
                log.info("User validation complete.");
                return true;
            } else {
                log.error(USER_PASSWORD_INCORRECT.getMessage());
                throw new ValidationException(USER_PASSWORD_NOT_A_MATCH.getCode(),
                        USER_PASSWORD_NOT_A_MATCH.getMessage());
            }
        } else {
            log.error(USER_NOT_FOUND_USERNAME.getMessage());
            throw new ValidationException(USER_PASSWORD_NOT_A_MATCH.getCode(),
                    USER_PASSWORD_NOT_A_MATCH.getMessage());
        }
    }

    public void verifyValidPassword(String password) {
        var match = password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

        if(!match) {
            log.error(INVALID_PASSWORD_PATTERN.getMessage());
            throw new ValidationException(INVALID_PASSWORD_PATTERN.getCode(),
                    INVALID_PASSWORD_PATTERN.getMessage());
        }
    }
    private void verifyNull(User user) {
        log.info("Verifying user information...");

        List<Error> errors = new ArrayList<>();

        if(user.getUsername().isBlank()) {
            log.error(EMPTY_USER.getMessage());
            errors.add(new Error(EMPTY_USER.getCode(), EMPTY_USER.getMessage()));
        }

        if(user.getEmail().isBlank()) {
            log.error(EMPTY_EMAIL.getMessage());
            errors.add(new Error(EMPTY_EMAIL.getCode(), EMPTY_EMAIL.getMessage()));
        }

        if(user.getPassword().isBlank()) {
            log.error(EMPTY_PASSWORD.getMessage());
            errors.add(new Error(EMPTY_PASSWORD.getCode(), EMPTY_PASSWORD.getMessage()));
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors.get(0).getCode(), errors.get(0).getMessage());
        }
    }

    private void verifyEmailPattern(String email) {
        log.info("Verifying email pattern...");

        var match = email.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$");

        if(!match) {
            log.error(INVALID_EMAIL_PATTERN.getMessage());
            throw new ValidationException(INVALID_EMAIL_PATTERN.getCode(),
                    INVALID_EMAIL_PATTERN.getMessage());
        }
    }
}
