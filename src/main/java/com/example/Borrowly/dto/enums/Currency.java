package com.example.Borrowly.dto.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Enumerated;

public enum Currency {
    @JsonProperty("FRW")
    FRW,
    @JsonProperty("USD")
    USD,
    @JsonProperty("EUR")
    EUR
}
