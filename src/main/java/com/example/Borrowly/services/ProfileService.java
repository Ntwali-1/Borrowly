package com.example.Borrowly.services;

import com.example.Borrowly.dto.ProfileRequest;
import com.example.Borrowly.dto.ProfileResponse;
import com.example.Borrowly.dto.UpdateProfileRequest;
import com.example.Borrowly.entity.Profile;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.ProfileRepository;
import com.example.Borrowly.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getProfile() != null) {
            throw new RuntimeException("Profile already exists for this user");
        }

        Profile profile = new Profile();
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setEmail(email);
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setLocation(profileRequest.getLocation());
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);
        return mapToResponse(savedProfile);
    }

    public ProfileResponse getMyProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return mapToResponse(profile);
    }

    public ProfileResponse getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return mapToResponse(profile);
    }

    @Transactional
    public ProfileResponse updateProfile(UpdateProfileRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        if (request.getFirstName() != null) profile.setFirstName(request.getFirstName());
        if (request.getLastName() != null) profile.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) profile.setPhoneNumber(request.getPhoneNumber());
        if (request.getLocation() != null) profile.setLocation(request.getLocation());

        Profile updatedProfile = profileRepository.save(profile);
        return mapToResponse(updatedProfile);
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .email(profile.getEmail())
                .phoneNumber(profile.getPhoneNumber())
                .location(profile.getLocation())
                .userId(profile.getUser().getId())
                .build();
    }
}
