package com.example.demo.controller;

import com.example.demo.dto.auth.AuthenticationResponseDTO;
import com.example.demo.dto.auth.LoginRequestDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserRegistrationDTO;
import com.example.demo.dto.user.UserUpdateDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.user.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        AuthenticationResponseDTO retrievedUser = userService.login(loginRequestDTO);
        return new ResponseEntity<AuthenticationResponseDTO>(retrievedUser,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errorMessages = new ArrayList<>();
            for(ObjectError error : bindingResult.getAllErrors()){
                errorMessages.add(error.getDefaultMessage());
            }

            return new ResponseEntity<>(errorMessages,HttpStatus.BAD_REQUEST);
        }
        AuthenticationResponseDTO retrievedUser = userService.signUp(userRegistrationDTO);
        return new ResponseEntity<>(retrievedUser, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token){
        UserDTO currentUser = userService.getCurrentUser(token);
        return new ResponseEntity<UserDTO>(currentUser,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserUpdateDTO userUpdateDTO){
        UserDTO updatedUser = userService.updateUser(token,userUpdateDTO);
        return new ResponseEntity<UserDTO>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token){
        userService.deleteUser(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
