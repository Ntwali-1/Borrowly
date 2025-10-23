package com.example.Borrowly.entity;

import com.example.Borrowly.dto.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "money_offers")
public class MoneyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String interestRate;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
