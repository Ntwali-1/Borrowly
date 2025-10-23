package com.example.Borrowly.dto;

import com.example.Borrowly.dto.enums.Currency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class ItemOfferRequest {
    private String itemName;
    private String imageUrl;
    private String description;
    private Double rentPrice;
    private Currency currency;
    private String duration;
}
