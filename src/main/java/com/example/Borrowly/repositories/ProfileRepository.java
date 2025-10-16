package com.example.Borrowly.repositories;

import com.example.Borrowly.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
