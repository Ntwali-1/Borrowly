package com.example.Borrowly.dto.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter

public enum Location {
    @JsonProperty("KICUKIRO")
    KICUKIRO,
    @JsonProperty("NYARUGENGE")
    NYARUGENGE,
    @JsonProperty("GASABO")
    GASABO
}
