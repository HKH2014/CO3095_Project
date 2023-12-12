package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
