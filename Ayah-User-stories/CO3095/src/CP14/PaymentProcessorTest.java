package CP14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PaymentProcessorTest {

    private Buyer buyer;
    private Property property;
    private PaymentProcessor paymentProcessor;

    @BeforeEach
    void setUp() {
        buyer = new Buyer("John Doe");
        property = new Property("House A");
        paymentProcessor = new PaymentProcessor();
    }

    @Test
    void testProcessPaymentWithWinningBid() {
        // Create a winning bid
        Bid winningBid = new Bid(500000.0, buyer);

        // Set the winning bid for the property
        property.setWinningBid(winningBid);

        
        // Process the payment
        paymentProcessor.processPayment(buyer, property);

    }

    @Test
    void testProcessPaymentWithoutWinningBid() {
        

        // Process the payment
        paymentProcessor.processPayment(buyer, property);

   
    }
}
