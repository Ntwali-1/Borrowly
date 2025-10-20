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
}
