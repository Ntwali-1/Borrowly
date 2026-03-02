package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.ProfileRequest;
import com.example.Borrowly.dto.ProfileResponse;
import com.example.Borrowly.dto.UpdateProfileRequest;
import com.example.Borrowly.services.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Tag(name = "Profile", description = "User profile management endpoints")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    @Operation(summary = "Create user profile")
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.createProfile(profileRequest));
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user's profile")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        return ResponseEntity.ok(profileService.getMyProfile());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile by ID")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "Update current user's profile")
    public ResponseEntity<ProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(profileService.updateProfile(request));
    }
}
