package com.example.Borrowly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileRequest {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
}
