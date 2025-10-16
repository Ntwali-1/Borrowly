package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Location;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter

public class ProfileRequest {
    String firstName;
    String lastName;
    String phoneNumber;
    Location location;
}
