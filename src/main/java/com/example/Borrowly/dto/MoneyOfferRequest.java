package com.example.Borrowly.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MoneyOfferRequest {
    private Double amount;
    private String currency;
    private String duration;
    private String description;
    private String interestRate;
}
