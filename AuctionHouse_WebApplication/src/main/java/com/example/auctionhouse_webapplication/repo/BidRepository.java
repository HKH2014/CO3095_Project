package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}
