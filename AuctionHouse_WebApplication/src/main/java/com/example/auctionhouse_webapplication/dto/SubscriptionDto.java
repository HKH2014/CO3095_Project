package com.example.auctionhouse_webapplication.dto;

public class SubscriptionDto {
    private String userId;
    private int auctionId;

    // Konstruktor, gettery i settery
    public SubscriptionDto() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
}
