package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
