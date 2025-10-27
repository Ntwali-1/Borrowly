package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMoneyOfferRequest {
    private Currency currency;
    private Double amount;
    private String interestRate;
    private String duration;
    private String description;
}

