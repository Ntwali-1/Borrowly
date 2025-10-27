package com.example.Borrowly.services;

import com.example.Borrowly.dto.BorrowRequestDTO;
import com.example.Borrowly.dto.enums.OfferStatus;
import com.example.Borrowly.dto.enums.OfferType;
import com.example.Borrowly.dto.enums.RequestStatus;
import com.example.Borrowly.entity.BorrowRequest;
import com.example.Borrowly.entity.ItemOffer;
import com.example.Borrowly.entity.MoneyOffer;
import com.example.Borrowly.entity.User;
import com.example.Borrowly.repositories.BorrowRequestRepository;
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
public class RequestService {
    private final BorrowRequestRepository borrowRequestRepository;
    private final UserRepository userRepository;
    private final MoneyOfferRepository moneyOfferRepository;
    private final ItemOfferRepository itemOfferRepository;

    public ResponseEntity<?> createBorrowRequest(BorrowRequestDTO requestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User requester = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        OfferType offerType = OfferType.valueOf(requestDTO.getOfferType().toUpperCase());
        User owner;
        
        if (offerType == OfferType.MONEY) {
            MoneyOffer offer = moneyOfferRepository.findById(requestDTO.getOfferId())
                    .orElseThrow(() -> new RuntimeException("Money offer not found"));
            
            if (offer.getStatus() != OfferStatus.ACTIVE) {
                return ResponseEntity.badRequest().body("This offer is not available");
            }
            
            owner = offer.getUser();
        } else {
            ItemOffer offer = itemOfferRepository.findById(requestDTO.getOfferId())
                    .orElseThrow(() -> new RuntimeException("Item offer not found"));
            
            if (offer.getStatus() != OfferStatus.ACTIVE) {
                return ResponseEntity.badRequest().body("This offer is not available");
            }
            
            owner = offer.getUser();
        }

        if (owner.getId().equals(requester.getId())) {
            return ResponseEntity.badRequest().body("Cannot request your own offer");
        }

        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setRequester(requester);
        borrowRequest.setOwner(owner);
        borrowRequest.setOfferId(requestDTO.getOfferId());
        borrowRequest.setOfferType(offerType);
        borrowRequest.setMessage(requestDTO.getMessage());
        borrowRequest.setStatus(RequestStatus.PENDING);

        BorrowRequest savedRequest = borrowRequestRepository.save(borrowRequest);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getIncomingRequests() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<BorrowRequest> requests = borrowRequestRepository.findByOwner(owner);
        return ResponseEntity.ok(requests);
    }

    public ResponseEntity<?> getOutgoingRequests() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User requester = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<BorrowRequest> requests = borrowRequestRepository.findByRequester(requester);
        return ResponseEntity.ok(requests);
    }

    public ResponseEntity<?> approveRequest(Long requestId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getOwner().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Not authorized to approve this request");
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return ResponseEntity.badRequest()
                    .body("Can only approve pending requests");
        }

        request.setStatus(RequestStatus.APPROVED);
        
        if (request.getOfferType() == OfferType.MONEY) {
            MoneyOffer offer = moneyOfferRepository.findById(request.getOfferId())
                    .orElseThrow(() -> new RuntimeException("Offer not found"));
            offer.setStatus(OfferStatus.BORROWED);
            moneyOfferRepository.save(offer);
        } else {
            ItemOffer offer = itemOfferRepository.findById(request.getOfferId())
                    .orElseThrow(() -> new RuntimeException("Offer not found"));
            offer.setStatus(OfferStatus.BORROWED);
            itemOfferRepository.save(offer);
        }

        BorrowRequest updatedRequest = borrowRequestRepository.save(request);
        return ResponseEntity.ok(updatedRequest);
    }

    public ResponseEntity<?> rejectRequest(Long requestId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getOwner().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Not authorized to reject this request");
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return ResponseEntity.badRequest()
                    .body("Can only reject pending requests");
        }

        request.setStatus(RequestStatus.REJECTED);
        BorrowRequest updatedRequest = borrowRequestRepository.save(request);
        return ResponseEntity.ok(updatedRequest);
    }

    public ResponseEntity<?> cancelRequest(Long requestId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getRequester().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Not authorized to cancel this request");
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return ResponseEntity.badRequest()
                    .body("Can only cancel pending requests");
        }

        request.setStatus(RequestStatus.CANCELLED);
        BorrowRequest updatedRequest = borrowRequestRepository.save(request);
        return ResponseEntity.ok(updatedRequest);
    }
}

