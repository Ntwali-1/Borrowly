package com.example.Borrowly.services;

import com.example.Borrowly.dto.MoneyOfferRequest;
import com.example.Borrowly.entity.MoneyOffer;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.ItemOfferRepository;
import com.example.Borrowly.repositories.MoneyOfferRepository;
import com.example.Borrowly.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final AuthService authService;
    private final MoneyOfferRepository moneyOfferRepository;
    private final ItemOfferRepository itemOfferRepository;
    private final UserRepository userRepository;

    public OfferService(AuthService authService, MoneyOfferRepository moneyOfferRepository, ItemOfferRepository itemOfferRepository, UserRepository userRepository) {
        this.authService = authService;
        this.moneyOfferRepository = moneyOfferRepository;
        this.itemOfferRepository = itemOfferRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createMoneyOffer(MoneyOfferRequest moneyOfferRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        try{
            MoneyOffer offer = new MoneyOffer();

            offer.setAmount(moneyOfferRequest.getAmount());
            offer.setCurrency(moneyOfferRequest.getCurrency());
            offer.setUser(user);
            offer.setInterestRate(moneyOfferRequest.getInterestRate());
            offer.setDuration(moneyOfferRequest.getDuration());

            MoneyOffer savedOffer = moneyOfferRepository.save(offer);
            return ResponseEntity.ok(savedOffer);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
