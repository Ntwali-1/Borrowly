package com.example.Borrowly.controllers;

import com.example.Borrowly.dto.BorrowRequestDTO;
import com.example.Borrowly.services.RequestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBorrowRequest(@RequestBody BorrowRequestDTO requestDTO) {
        return requestService.createBorrowRequest(requestDTO);
    }

    @GetMapping("/incoming")
    public ResponseEntity<?> getIncomingRequests() {
        return requestService.getIncomingRequests();
    }

    @GetMapping("/outgoing")
    public ResponseEntity<?> getOutgoingRequests() {
        return requestService.getOutgoingRequests();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveRequest(@PathVariable Long id) {
        return requestService.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {
        return requestService.rejectRequest(id);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelRequest(@PathVariable Long id) {
        return requestService.cancelRequest(id);
    }
}

