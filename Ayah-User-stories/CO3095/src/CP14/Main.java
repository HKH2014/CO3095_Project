package CP14;

public class Main {
    public static void main(String[] args) {
        // Create instances of Buyer, Property, and Bid
        Buyer buyer = new Buyer("John Doe");
        Property property = new Property("House A");
        Bid winningBid = new Bid(345000.0, buyer);

        // Set the winning bid for the property
        property.setWinningBid(winningBid);

        // Create a PaymentProcessor instance and process the payment
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.processPayment(buyer, property);
    }
}
