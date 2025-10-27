package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.ProfileRequest;
import com.example.Borrowly.dto.UpdateProfileRequest;
import com.example.Borrowly.services.ProfileService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
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

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        return profileService.getMyProfile();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request) {
        return profileService.updateProfile(request);
    }
}
