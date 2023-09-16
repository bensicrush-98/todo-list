package com.example.demo.exceptions.user;

import com.example.demo.constants.ErrorMessages;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(){
        super(ErrorMessages.USER_NOT_FOUND_ERROR);
    }

    public UsernameNotFoundException(String message){
        super(message);
    }
}
