package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.ItemOfferRequest;
import com.example.Borrowly.dto.MoneyOfferRequest;
import com.example.Borrowly.services.OfferService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<?> createMoneyOffer(@RequestBody MoneyOfferRequest moneyOfferRequest) {
        return offerService.createMoneyOffer(moneyOfferRequest);
    }
}
