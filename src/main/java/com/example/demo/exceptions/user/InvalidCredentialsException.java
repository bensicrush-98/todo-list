package com.example.demo.exceptions.user;

import com.example.demo.constants.ErrorMessages;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super(ErrorMessages.INVALID_CREDENTIALS_ERROR);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
