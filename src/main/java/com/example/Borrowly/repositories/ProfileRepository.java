package com.example.Borrowly.repositories;

import com.example.Borrowly.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    <T> ScopedValue<T> findByEmail(String email);
}
