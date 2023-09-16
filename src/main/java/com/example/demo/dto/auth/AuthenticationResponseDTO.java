package com.example.demo.dto.auth;

public class AuthenticationResponseDTO {
    private String token;

    public AuthenticationResponseDTO(String username, String email, String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

