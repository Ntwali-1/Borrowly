package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Location location;
}

