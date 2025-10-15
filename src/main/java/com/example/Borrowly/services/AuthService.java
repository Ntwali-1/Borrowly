package com.example.Borrowly.services;

import com.example.Borrowly.dto.AuthResponse;
import com.example.Borrowly.dto.SignupRequest;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> signup(SignupRequest signupRequest) {
       if (userRepository.existsByEmail(signupRequest.getEmail())) {
           return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
       }

       try{
           User user = new User();

           user.setEmail(signupRequest.getEmail());
           user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

           User saved = userRepository.save(user);
           return new ResponseEntity<>(saved, HttpStatus.CREATED);

       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
       }
    }
}