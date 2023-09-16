package com.example.demo.service.user;

import com.example.demo.entity.User;

public interface UserResolver {
    User getUserFromToken(String token);
}
