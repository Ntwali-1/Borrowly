package com.example.Borrowly.services;

import com.example.Borrowly.dto.ItemOfferRequest;
import com.example.Borrowly.dto.MoneyOfferRequest;
import com.example.Borrowly.dto.UpdateItemOfferRequest;
import com.example.Borrowly.dto.UpdateMoneyOfferRequest;
import com.example.Borrowly.dto.enums.OfferStatus;
import com.example.Borrowly.entity.ItemOffer;
import com.example.Borrowly.entity.MoneyOffer;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.ItemOfferRepository;
import com.example.Borrowly.repositories.MoneyOfferRepository;
import com.example.Borrowly.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {

    private final AuthService authService;
    private final MoneyOfferRepository moneyOfferRepository;
    private final ItemOfferRepository itemOfferRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> createMoneyOffer(MoneyOfferRequest moneyOfferRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        try{
            MoneyOffer offer = new MoneyOffer();

            offer.setUserEmail(user.getEmail());
            offer.setAmount(moneyOfferRequest.getAmount());
            offer.setCurrency(moneyOfferRequest.getCurrency());
            offer.setUser(user);
            offer.setInterestRate(moneyOfferRequest.getInterestRate());
            offer.setDuration(moneyOfferRequest.getDuration());
            offer.setDescription(moneyOfferRequest.getDescription());

            MoneyOffer savedOffer = moneyOfferRepository.save(offer);
            return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> createItemOffer(ItemOfferRequest itemOfferRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        try{
            ItemOffer offer = new ItemOffer();

            offer.setUserEmail(user.getEmail());
            offer.setItemName(itemOfferRequest.getItemName());
            offer.setRentPrice(itemOfferRequest.getRentPrice());
            offer.setCurrency(itemOfferRequest.getCurrency());
            offer.setDuration(itemOfferRequest.getDuration());
            offer.setDescription(itemOfferRequest.getDescription());
            offer.setImageUrl(itemOfferRequest.getImageUrl());
            offer.setUser(user);

            ItemOffer savedOffer = itemOfferRepository.save(offer);
            return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> getAllMoneyOffers() {
        List<MoneyOffer> offers = moneyOfferRepository.findAll().stream()
                .filter(offer -> offer.getStatus() == OfferStatus.ACTIVE)
                .toList();
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<?> getAllItemOffers() {
        List<ItemOffer> offers = itemOfferRepository.findAll().stream()
                .filter(offer -> offer.getStatus() == OfferStatus.ACTIVE)
                .toList();
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<?> getMoneyOfferById(Long id) {
        MoneyOffer offer = moneyOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Money offer not found"));
        return ResponseEntity.ok(offer);
    }

    public ResponseEntity<?> getItemOfferById(Long id) {
        ItemOffer offer = itemOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item offer not found"));
        return ResponseEntity.ok(offer);
    }

    public ResponseEntity<?> getMyOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        List<MoneyOffer> moneyOffers = moneyOfferRepository.findAll().stream()
                .filter(offer -> offer.getUserEmail().equals(email))
                .toList();
        List<ItemOffer> itemOffers = itemOfferRepository.findAll().stream()
                .filter(offer -> offer.getUserEmail().equals(email))
                .toList();

        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("moneyOffers", moneyOffers);
            put("itemOffers", itemOffers);
        }});
    }

    public ResponseEntity<?> updateMoneyOffer(Long id, UpdateMoneyOfferRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        MoneyOffer offer = moneyOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Money offer not found"));

        if (!offer.getUserEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to update this offer");
        }

        if (request.getAmount() != null) offer.setAmount(request.getAmount());
        if (request.getCurrency() != null) offer.setCurrency(request.getCurrency());
        if (request.getInterestRate() != null) offer.setInterestRate(request.getInterestRate());
        if (request.getDuration() != null) offer.setDuration(request.getDuration());
        if (request.getDescription() != null) offer.setDescription(request.getDescription());

        MoneyOffer updatedOffer = moneyOfferRepository.save(offer);
        return ResponseEntity.ok(updatedOffer);
    }

    public ResponseEntity<?> updateItemOffer(Long id, UpdateItemOfferRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ItemOffer offer = itemOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item offer not found"));

        if (!offer.getUserEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to update this offer");
        }

        if (request.getItemName() != null) offer.setItemName(request.getItemName());
        if (request.getImageUrl() != null) offer.setImageUrl(request.getImageUrl());
        if (request.getRentPrice() != null) offer.setRentPrice(request.getRentPrice());
        if (request.getCurrency() != null) offer.setCurrency(request.getCurrency());
        if (request.getDuration() != null) offer.setDuration(request.getDuration());
        if (request.getDescription() != null) offer.setDescription(request.getDescription());

        ItemOffer updatedOffer = itemOfferRepository.save(offer);
        return ResponseEntity.ok(updatedOffer);
    }

    public ResponseEntity<?> deleteMoneyOffer(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        MoneyOffer offer = moneyOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Money offer not found"));

        if (!offer.getUserEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to delete this offer");
        }

        offer.setStatus(OfferStatus.DELETED);
        moneyOfferRepository.save(offer);
        return ResponseEntity.ok("Money offer deleted successfully");
    }

    public ResponseEntity<?> deleteItemOffer(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        ItemOffer offer = itemOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item offer not found"));

        if (!offer.getUserEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized to delete this offer");
        }

        offer.setStatus(OfferStatus.DELETED);
        itemOfferRepository.save(offer);
        return ResponseEntity.ok("Item offer deleted successfully");
    }
}
