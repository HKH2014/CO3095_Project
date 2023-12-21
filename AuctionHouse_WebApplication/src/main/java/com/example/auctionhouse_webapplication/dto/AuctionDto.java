package com.example.auctionhouse_webapplication.dto;

public class AuctionDto {

    private Long id;
    private String name;
    private String description;
    private String endDate;
    private String sellerName;
    private Double currentPrice;
    private Double minBet;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(final String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getMinBet() {
        return minBet;
    }

    public void setMinBet(final Double minBet) {
        this.minBet = minBet;
    }
}
