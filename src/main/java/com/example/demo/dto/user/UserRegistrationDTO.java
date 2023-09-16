package com.example.demo.dto.user;

import javax.validation.constraints.*;

public class UserRegistrationDTO {
    @NotBlank(message = "Name must not be empty.")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
    @NotBlank(message = "Email must not be empty.")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password must not be empty.")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    // Constructor vacío
    public UserRegistrationDTO() {}

    // Getters y setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
