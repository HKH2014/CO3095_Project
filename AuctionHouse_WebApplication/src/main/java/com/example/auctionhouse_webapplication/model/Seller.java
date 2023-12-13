package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seller extends User {

    @OneToMany(mappedBy = "seller")
    private List<Auction> auctions;

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
}
