package com.example.Borrowly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "money_offers")
public class MoneyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String interestRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
