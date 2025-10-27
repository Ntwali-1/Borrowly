package com.example.Borrowly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRequestDTO {
    private Long offerId;
    private String offerType;
    private String message;
}

