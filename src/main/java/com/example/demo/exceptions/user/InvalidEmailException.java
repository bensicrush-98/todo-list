package com.example.demo.exceptions.user;

import com.example.demo.constants.ErrorMessages;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException (){
        super(ErrorMessages.INVALID_EMAIL_ERROR);
    }

}
