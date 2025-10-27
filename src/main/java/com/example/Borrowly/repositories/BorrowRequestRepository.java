package com.example.Borrowly.repositories;

import com.example.Borrowly.dto.enums.RequestStatus;
import com.example.Borrowly.entity.BorrowRequest;
import com.example.Borrowly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {
    List<BorrowRequest> findByOwner(User owner);
    List<BorrowRequest> findByRequester(User requester);
    List<BorrowRequest> findByOwnerAndStatus(User owner, RequestStatus status);
    List<BorrowRequest> findByRequesterAndStatus(User requester, RequestStatus status);
}

