package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Currency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MoneyOfferRequest {
    private Double amount;
    private Currency currency;
    private String duration;
    private String description;
    private String interestRate;
}
