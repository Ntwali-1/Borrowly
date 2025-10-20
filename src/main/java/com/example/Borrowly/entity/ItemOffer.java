package com.example.Borrowly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item_offers")
public class ItemOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Double rentPrice;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
