package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.ItemOfferRequest;
import com.example.Borrowly.dto.MoneyOfferRequest;
import com.example.Borrowly.dto.UpdateItemOfferRequest;
import com.example.Borrowly.dto.UpdateMoneyOfferRequest;
import com.example.Borrowly.services.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
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

    @PostMapping("/item")
    public ResponseEntity<?> createItemOffer(@RequestBody ItemOfferRequest itemOfferRequest) {
        return offerService.createItemOffer(itemOfferRequest);
    }

    @GetMapping("/money/all")
    public ResponseEntity<?> getAllMoneyOffers() {
        return offerService.getAllMoneyOffers();
    }

    @GetMapping("/item/all")
    public ResponseEntity<?> getAllItemOffers() {
        return offerService.getAllItemOffers();
    }

    @GetMapping("/money/{id}")
    public ResponseEntity<?> getMoneyOfferById(@PathVariable Long id) {
        return offerService.getMoneyOfferById(id);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<?> getItemOfferById(@PathVariable Long id) {
        return offerService.getItemOfferById(id);
    }

    @GetMapping("/my-offers")
    public ResponseEntity<?> getMyOffers() {
        return offerService.getMyOffers();
    }

    @PutMapping("/money/{id}")
    public ResponseEntity<?> updateMoneyOffer(@PathVariable Long id, @RequestBody UpdateMoneyOfferRequest request) {
        return offerService.updateMoneyOffer(id, request);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItemOffer(@PathVariable Long id, @RequestBody UpdateItemOfferRequest request) {
        return offerService.updateItemOffer(id, request);
    }

    @DeleteMapping("/money/{id}")
    public ResponseEntity<?> deleteMoneyOffer(@PathVariable Long id) {
        return offerService.deleteMoneyOffer(id);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteItemOffer(@PathVariable Long id) {
        return offerService.deleteItemOffer(id);
    }
}
