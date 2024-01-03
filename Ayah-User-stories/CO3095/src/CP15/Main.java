package CP15;

public class Main {
    public static void main(String[] args) {
        // Create instances of Buyer, Property, and Bid
        Buyer buyer = new Buyer();
        Property property1 = new Property("House A");
        Property property2 = new Property("Apartment B");

        // Assuming Bid has an int, BidStatus, and Property constructor
        Bid bid1 = new Bid(1000, Bid.BidStatus.WON, property1);
        Bid bid2 = new Bid(800, Bid.BidStatus.LOST, property2);

        // Set the winning bid for properties
        property1.setWinningBid(bid1);
        property2.setWinningBid(bid2);

        // Buyer places bids
        buyer.addBidToHistory(bid1);
        buyer.addBidToHistory(bid2);

        // Buyer views bid history
        buyer.viewBidHistory();

        // Buyer deletes a bid
        buyer.deleteBidFromHistory(bid1);

        // Buyer views bid history again
        buyer.viewBidHistory();

        // Buyer views won bids on the personal page
        buyer.viewWonBidsOnPersonalPage();
    }
}
