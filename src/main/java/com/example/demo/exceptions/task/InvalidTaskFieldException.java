package com.example.demo.exceptions.task;

public class InvalidTaskFieldException extends RuntimeException{
    public InvalidTaskFieldException(){
        super();
    }

    public InvalidTaskFieldException(String message){
        super(message);
    }
}
