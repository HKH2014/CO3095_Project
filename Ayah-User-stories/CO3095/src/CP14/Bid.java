package CP14;

public class Bid {
    private double amount;
    private Buyer bidder;

    public Bid(double amount, Buyer bidder) {
        this.amount = amount;
        this.bidder = bidder;
    }

    public double getAmount() {
        return amount;
    }

    public Buyer getBidder() {
        return bidder;
    }
}
