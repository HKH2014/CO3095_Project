package com.example.auctionhouse_webapplication.dto;

import java.util.Objects;

public class BidDto {
    private int auctionId;
    private String userId;
    private double bid;

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAmount() {
        return bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidDto bidDto = (BidDto) o;
        return auctionId == bidDto.auctionId && Double.compare(bidDto.bid, bid) == 0 && Objects.equals(userId, bidDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId, userId, bid);
    }

    @Override
    public String toString() {
        return "BidDto{" +
                "auctionId=" + auctionId +
                ", userId='" + userId + '\'' +
                ", bid=" + bid +
                '}';
    }
}