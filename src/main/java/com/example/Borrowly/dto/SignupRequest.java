package com.example.Borrowly.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {
    String email;
    String password;
}
