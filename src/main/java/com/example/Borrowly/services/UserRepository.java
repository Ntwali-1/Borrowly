package com.example.Borrowly.services;

import com.example.Borrowly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
