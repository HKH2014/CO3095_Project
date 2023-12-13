package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Buyer extends User {

    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
