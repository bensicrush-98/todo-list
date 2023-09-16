package com.example.demo.service.user;

import com.example.demo.dto.auth.AuthenticationResponseDTO;
import com.example.demo.dto.auth.LoginRequestDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserRegistrationDTO;
import com.example.demo.dto.user.UserUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthenticationResponseDTO signUp(UserRegistrationDTO userRegistrationDTO);
    AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO);
    UserDTO getCurrentUser(String token);

    UserDTO updateUser(String token, UserUpdateDTO updateDTO);

    void deleteUser(String token);


}
