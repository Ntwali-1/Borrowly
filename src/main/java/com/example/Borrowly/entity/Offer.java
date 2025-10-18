package com.example.Borrowly.entity;

import com.example.Borrowly.dto.enums.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private String interestRate;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String message;

}
