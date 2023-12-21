package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private User bidder;

    private Double currentBid;

    private Double maxBid;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(final Auction auction) {
        this.auction = auction;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(final User bidder) {
        this.bidder = bidder;
    }

    public Double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(final Double bid) {
        this.currentBid = bid;
    }

    public Double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(final Double maxBid) {
        this.maxBid = maxBid;
    }
}
