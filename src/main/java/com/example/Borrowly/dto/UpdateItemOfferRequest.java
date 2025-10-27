package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemOfferRequest {
    private String itemName;
    private String imageUrl;
    private Double rentPrice;
    private Currency currency;
    private String duration;
    private String description;
}

