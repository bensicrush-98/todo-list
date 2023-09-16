package com.example.demo.service.user;

import com.example.demo.auth.JwtProvider;
import com.example.demo.dto.auth.AuthenticationResponseDTO;
import com.example.demo.dto.auth.LoginRequestDTO;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserRegistrationDTO;
import com.example.demo.dto.user.UserUpdateDTO;
import com.example.demo.entity.User;
import com.example.demo.exceptions.user.InvalidCredentialsException;
import com.example.demo.exceptions.user.InvalidEmailException;
import com.example.demo.exceptions.user.UserAlreadyExistsException;
import com.example.demo.exceptions.user.UsernameNotFoundException;
import com.example.demo.mapper.UserMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import com.example.demo.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class UserServiceImpl implements  UserService, UserResolver {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,Validator validator,JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }



    @Override
    @Transactional
    public AuthenticationResponseDTO signUp(UserRegistrationDTO userRegistrationDTO){
        checkUserExistence(userRegistrationDTO.getUsername(), userRegistrationDTO.getEmail());
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(encryptPassword(userRegistrationDTO.getPassword()));
        User savedUser = userRepository.save(user);
        UserDTO userDTO = UserMapper.toDTO(savedUser);
        String token = jwtProvider.generateToken(userDTO.getId());
        return new AuthenticationResponseDTO(userDTO.getUsername(),userDTO.getEmail(),token);
    }

    @Override
    public UserDTO getCurrentUser(String token){
        User user = getUserFromToken(token);
        return UserMapper.toDTO(user);
    }


    @Override
    public AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String login = loginRequestDTO.getLogin();
        User user = isEmail(login) ? userRepository.findByEmail(login).orElse(null) : userRepository.findByUsername(login).orElse(null);
        if (userIsValid(user, loginRequestDTO.getPassword())) {
            String token = jwtProvider.generateToken(user.getId());
            return new AuthenticationResponseDTO(user.getUsername(), user.getEmail(), token);
        }
        throw new InvalidCredentialsException();
    }

    @Override
    @Transactional
    public UserDTO updateUser(String token, UserUpdateDTO updateUserDTO){
        User user = getUserFromToken(token);
        // check which field to update
        if (updateUserDTO.getUsername() != null) {
            validateUniqueValue(updateUserDTO.getUsername(), userRepository::findByUsername);
            user.setUsername(updateUserDTO.getUsername());
        }
        if (updateUserDTO.getEmail() != null) {
            if(isEmail(updateUserDTO.getEmail())){
                validateUniqueValue(updateUserDTO.getEmail(), userRepository::findByEmail);
                user.setEmail(updateUserDTO.getEmail());
            }else{
                throw new InvalidEmailException();
            }
        }
        return UserMapper.toDTO(user);
    }

    @Override
    @Transactional
    public void deleteUser(String token){
        UUID userId = jwtProvider.getUserIdFromToken(token);
        userRepository.deleteById(userId);
    }
    @Override
    public User getUserFromToken(String token){
        UUID userId = jwtProvider.getUserIdFromToken(token);
        return userRepository.findById(userId)
                .orElseThrow(UsernameNotFoundException::new);
    }

    // Function that searches a user based on a value (username,email)
    private void validateUniqueValue(String value, Function<String, Optional<User>> finder){
        Optional<User> existingUser = finder.apply(value);
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException();
        }
    }

    private boolean userIsValid(User user, String password) {
        return user != null && passwordMatches(password, user.getPassword());
    }

    private void checkUserExistence(String username, String email) {
        if(userRepository.findByUsername(username).isPresent() ||
                userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

    private String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private boolean isEmail(String identifier){
        return EmailValidator.getInstance().isValid(identifier);
    }

    private boolean passwordMatches(String rawPassword,String encodedPassword){
        if(rawPassword == null || encodedPassword == null){
            return false;
        }
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
}
