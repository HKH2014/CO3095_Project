package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByEndDateAfter(LocalDate minDate);

}
