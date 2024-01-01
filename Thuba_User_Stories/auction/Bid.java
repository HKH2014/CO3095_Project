package auction;

public class Bid {
    private double amount;
    private AuctionRecord auctionRecord;

    // Constructor
    public Bid(double amount, AuctionRecord auctionRecord) {
        this.amount = amount;
        this.auctionRecord = auctionRecord;
    }

    public Bid(String string, int i, String string2) {
    }

    // Getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AuctionRecord getAuctionRecord() {
        return auctionRecord;
    }

    public void setAuctionRecord(AuctionRecord auctionRecord) {
        this.auctionRecord = auctionRecord;
    }
}