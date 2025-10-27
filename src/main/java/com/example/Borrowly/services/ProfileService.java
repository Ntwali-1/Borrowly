package com.example.Borrowly.services;

import com.example.Borrowly.dto.ProfileRequest;
import com.example.Borrowly.dto.UpdateProfileRequest;
import com.example.Borrowly.dto.enums.Location;
import com.example.Borrowly.entity.Profile;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.ProfileRepository;
import com.example.Borrowly.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createProfile(ProfileRequest profileRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = new Profile();
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setEmail(email);
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setLocation(profileRequest.getLocation());
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getMyProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return ResponseEntity.ok(profile);
    }

    public ResponseEntity<?> getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return ResponseEntity.ok(profile);
    }

    public ResponseEntity<?> updateProfile(UpdateProfileRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        if (request.getFirstName() != null) profile.setFirstName(request.getFirstName());
        if (request.getLastName() != null) profile.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) profile.setPhoneNumber(request.getPhoneNumber());
        if (request.getLocation() != null) profile.setLocation(request.getLocation());

        Profile updatedProfile = profileRepository.save(profile);
        return ResponseEntity.ok(updatedProfile);
    }
}
