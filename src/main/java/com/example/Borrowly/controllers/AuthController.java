package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.AuthResponse;
import com.example.Borrowly.dto.SignupRequest;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
