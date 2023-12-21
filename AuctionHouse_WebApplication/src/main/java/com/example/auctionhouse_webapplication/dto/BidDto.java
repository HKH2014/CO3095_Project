package com.example.auctionhouse_webapplication.dto;

public class BidDto {

    private Long auctionId;
    private Double maxBid;

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(final Long auctionId) {
        this.auctionId = auctionId;
    }

    public Double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Double maxBid) {
        this.maxBid = maxBid;
    }

}