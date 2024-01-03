package CP15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {

    private Buyer buyer;
    private Property property1;
    private Property property2;
    private Bid bid1;
    private Bid bid2;

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
        property1 = new Property("House A");
        property2 = new Property("Apartment B");
        bid1 = new Bid(1000, Bid.BidStatus.WON, property1);
        bid2 = new Bid(800, Bid.BidStatus.LOST, property2);
    }

    @Test
    void testAddBidToHistory() {
        buyer.addBidToHistory(bid1);
        buyer.addBidToHistory(bid2);

        assertEquals(2, buyer.getBidHistory().size());
        assertTrue(buyer.getBidHistory().contains(bid1));
        assertTrue(buyer.getBidHistory().contains(bid2));
    }

    @Test
    void testDeleteBidFromHistory() {
        buyer.addBidToHistory(bid1);
        buyer.addBidToHistory(bid2);

        buyer.deleteBidFromHistory(bid1);

        assertEquals(1, buyer.getBidHistory().size());
        assertFalse(buyer.getBidHistory().contains(bid1));
        assertTrue(buyer.getBidHistory().contains(bid2));
    }

    @Test
    void testViewWonBidsOnPersonalPage() {
        buyer.addBidToHistory(bid1);
        buyer.addBidToHistory(bid2);

        // Assuming bid1 is set as WON, bid2 as LOST
        buyer.viewWonBidsOnPersonalPage();

        
    }

    
}
