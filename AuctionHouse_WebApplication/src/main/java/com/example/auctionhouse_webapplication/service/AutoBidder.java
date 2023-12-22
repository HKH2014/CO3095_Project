package com.example.auctionhouse_webapplication.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.auctionhouse_webapplication.model.Auction;
import com.example.auctionhouse_webapplication.model.Bid;
import com.example.auctionhouse_webapplication.repo.AuctionRepository;
import com.example.auctionhouse_webapplication.repo.BidRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class AutoBidder {

    private AuctionRepository auctionRepository;
    private BidRepository bidRepository;

    public AutoBidder(BidRepository bidRepository, AuctionRepository auctionRepository) {
        this.bidRepository = bidRepository;
        this.auctionRepository = auctionRepository;
    }

    @Scheduled(cron = "0/10 * * * * *")
    @Transactional
    public void autoBid() {
        auctionRepository.findAllByEndDateAfter(LocalDate.now())
            .stream()
            .filter(this::doesAuctionRequireBidding)
            .forEach(this::bidSingleAuction);
    }

    private boolean doesAuctionRequireBidding(final Auction auction) {
        final double currentPrice = auction.getCurrentPrice();
        return auction.getBids().stream().filter(bid -> bid.getMaxBid() >= currentPrice).toList().size() > 1;
    }

    private void bidSingleAuction(final Auction auction) {
        final List<Bid> bids = auction.getBids();
        bids.forEach(bid -> {
            final double currentPrice = auction.getCurrentPrice();
            if (bid.getMaxBid() <= currentPrice) {
                bid.setCurrentBid(bid.getMaxBid());
            } else if (bid.getCurrentBid() + auction.getMinBet() <= currentPrice) {
                bid.setCurrentBid(Math.min(bid.getCurrentBid() + auction.getMinBet(), currentPrice));
            } else {
                bid.setCurrentBid(currentPrice + auction.getMinBet());
            }
        });
    }
}
