package com.example.Borrowly.dto;

import com.example.Borrowly.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthResponse {
    String token;
    String message;
    User user;

    public AuthResponse(String token, String message, User user) {
        this.token = token;
        this.message = message;
        this.user = user;
    }
}
