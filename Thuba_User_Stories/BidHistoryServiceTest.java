import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BidHistoryServiceTest {
    @Test
    public void testAddBid() {
        BidHistoryService service = new BidHistoryService();
        Buyer buyer = new Buyer("john_doe", "password");
        Bid bid = new Bid("123 Main St", 500000, "2022-01-01");
        buyer.addBid(service, bid);
        assertEquals(1, service.viewBidHistory(buyer).size());
    }

    @Test
    public void testUpdateBid() {
        BidHistoryService service = new BidHistoryService();
        Buyer buyer = new Buyer("john_doe", "password");
        Bid bid = new Bid("123 Main St", 500000, "2022-01-01");
        buyer.addBid(service, bid);
        Bid updatedBid = new Bid("123 Main St", 600000, "2022-01-02");
        buyer.updateBid(service, 0, updatedBid);
        assertEquals(updatedBid, service.viewBidHistory(buyer).get(0));
    }

    @Test
    public void testDeleteBid() {
        BidHistoryService service = new BidHistoryService();
        Buyer buyer = new Buyer("john_doe", "password");
        Bid bid = new Bid("123 Main St", 500000, "2022-01-01");
        buyer.addBid(service, bid);
        buyer.deleteBid(service, 0);
        assertTrue(service.viewBidHistory(buyer).isEmpty());
    }

    @Test
    public void testViewBidHistory() {
        BidHistoryService service = new BidHistoryService();
        Buyer buyer = new Buyer("john_doe", "password");
        Bid bid1 = new Bid("123 Main St", 500000, "2022-01-01");
        Bid bid2 = new Bid("456 Elm St", 600000, "2022-01-02");
        buyer.addBid(service, bid1);
        buyer.addBid(service, bid2);
        List<Bid> bids = service.viewBidHistory(buyer);
        assertEquals(2, bids.size());
        assertTrue(bids.contains(bid1));
        assertTrue(bids.contains(bid2));
    }
}