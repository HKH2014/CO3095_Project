package CP15;

public class Bid {
    private double amount;
    private BidStatus status;
    private Property property;

    public Bid(double amount, BidStatus status, Property property) {
        this.amount = amount;
        this.status = status;
        this.property = property;
    }

    public double getAmount() {
        return amount;
    }

    public BidStatus getStatus() {
        return status;
    }

    public Property getProperty() {
        return property;
    }

    public enum BidStatus {
        WON,
        LOST,
        ENDED
    }

    
}
