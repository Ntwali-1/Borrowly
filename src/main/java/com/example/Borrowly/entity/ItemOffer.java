package com.example.Borrowly.entity;

import com.example.Borrowly.dto.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private Currency currency;

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
