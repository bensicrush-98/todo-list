package com.example.demo.exceptions.task;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){super();}

    public TaskNotFoundException(String message){
        super(message);
    }
}
