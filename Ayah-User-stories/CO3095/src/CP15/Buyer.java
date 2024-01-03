package CP15;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private List<Bid> bidHistory;

    public Buyer() {
        this.bidHistory = new ArrayList<>();
    }

    public void addBidToHistory(Bid bid) {
        bidHistory.add(bid);
    }

    public void deleteBidFromHistory(Bid bid) {
        bidHistory.remove(bid);
    }

    public void viewBidHistory() {
        for (Bid bid : bidHistory) {
            System.out.println("Bid amount: " + bid.getAmount() +
                    ", Status: " + bid.getStatus() +
                    ", Property: " + bid.getProperty().getName());
        }
    }

    public void viewWonBidsOnPersonalPage() {
        System.out.println("Won Bids on Personal Page:");
        for (Bid bid : bidHistory) {
            if (bid.getStatus() == Bid.BidStatus.WON) {
                System.out.println("Bid amount: " + bid.getAmount() +
                        ", Property: " + bid.getProperty().getName());
            }
        }
    }

    public List<Bid> getBidHistory() {
        return bidHistory;
    }
    
   
}
