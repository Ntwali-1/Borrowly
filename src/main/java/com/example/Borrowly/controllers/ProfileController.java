package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.ProfileRequest;
import com.example.Borrowly.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequest profileRequest) {
        return profileService.createProfile(profileRequest);
    }

}
