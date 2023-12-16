package com.example.auctionhouse_webapplication.dto;

import java.time.LocalDateTime;

public class NewAuctionDto {

    private String name;
    private String description;
    private double basePrice;
    private double minBet;
    private String endDate;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }
}
