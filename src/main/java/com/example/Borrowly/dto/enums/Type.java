package com.example.Borrowly.dto.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter

public enum Type {
    @JsonProperty("MONEY")
    MONEY,
    @JsonProperty("ITEM")
    ITEM
}
