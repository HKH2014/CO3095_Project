package CP12;

public class BiddingSystem {

    // Method to notify the winner based on the property's winning bid
    public void notifyWinner(Buyer buyer, Property property) {
        Bid winningBid = property.getWinningBid();

        if (winningBid != null && winningBid.getBidder() == buyer) {
            buyer.receiveNotification("Congratulations! You won the bidding for " + property.getName() +
                    ". Please proceed to payment.");
        } else {
            buyer.receiveNotification("Unfortunately, you did not win the bidding for " + property.getName() +
                    "!");
        }
    }

    
}
