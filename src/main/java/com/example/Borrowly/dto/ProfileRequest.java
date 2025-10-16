package com.example.Borrowly.dto;

import lombok.*;

@Data
@Getter
@Setter

public class ProfileRequest {
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
}
