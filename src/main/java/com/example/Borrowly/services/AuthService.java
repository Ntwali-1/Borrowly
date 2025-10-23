package com.example.Borrowly.services;

import com.example.Borrowly.dto.LoginRequest;
import com.example.Borrowly.dto.SignupRequest;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.UserRepository;
import com.example.Borrowly.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

           String jwt = jwtService.generateToken(saved.getEmail());
           return ResponseEntity.ok(jwt);

       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
       }
    }

    public ResponseEntity<?> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String jwt = jwtService.generateToken(request.getEmail());
        return ResponseEntity.ok(jwt);
    }
}