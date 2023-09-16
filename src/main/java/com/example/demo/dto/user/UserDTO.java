package com.example.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class UserDTO {
    @JsonIgnore
    private UUID id;
    private String username;
    private String email;

    //we do not add the password field here in order to expose this field in the response

    //empty constructor
    public UserDTO(){

    }

    // constructor with parameter

    public UserDTO(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


    // GETTERS AND SETTERS



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
