package com.example.Borrowly.repositories;

import com.example.Borrowly.entity.ItemOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOfferRepository extends JpaRepository <ItemOffer, Long>{

}
