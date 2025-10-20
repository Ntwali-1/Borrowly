package com.example.Borrowly.services;

import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final AuthService authService;
    public OfferService(AuthService authService) {
        this.authService = authService;
    }
}
