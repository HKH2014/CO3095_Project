package CP14;

public class PaymentProcessor {
    public void processPayment(Buyer buyer, Property property) {
        Bid winningBid = property.getWinningBid();

        if (winningBid != null) {
            double amountToPay = winningBid.getAmount();
            System.out.println("Payment processed successfully. Amount: £" + amountToPay);
        } else {
            System.out.println("No winning bid found. Payment cannot be processed.");
        }
    }
}
