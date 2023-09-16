package com.example.demo.exceptions.user;

import com.example.demo.constants.ErrorMessages;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(ErrorMessages.USER_ALREADY_EXISTS_ERROR);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
