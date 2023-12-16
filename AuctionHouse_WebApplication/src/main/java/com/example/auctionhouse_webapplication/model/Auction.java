package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import org.springframework.util.comparator.Comparators;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double basePrice;
    private double minBet;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "auction")
    private List<Bid> bids;

    public double getCurrentPrice() {
        return bids.stream()
            .map(Bid::getBid)
            .max((bid1, bid2) -> Comparators.comparable().compare(bid1, bid2))
            .orElse(basePrice);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final double basePrice) {
        this.basePrice = basePrice;
    }

    public double getMinBet() {
        return minBet;
    }

    public void setMinBet(final double minBet) {
        this.minBet = minBet;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(final User seller) {
        this.seller = seller;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(final List<Bid> bids) {
        this.bids = bids;
    }
}
