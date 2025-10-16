package com.example.Borrowly.services;

import com.example.Borrowly.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


}
