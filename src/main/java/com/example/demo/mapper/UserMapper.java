package com.example.demo.mapper;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(),user.getUsername(),user.getEmail());
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // password is not set here because userDTO does not have the password
        return user;
    }
}
