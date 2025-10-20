package com.example.Borrowly.controllers;

import com.example.Borrowly.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/money")
    public ResponseEntity<?> createMoneyOffer() {
        return offerService.createMoneyOffer();
    }

    @PostMapping("/item")
    public ResponseEntity<?> createItemOffer() {
        return offerService.createItemOffer();
    }
}
