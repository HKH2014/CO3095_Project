import auction.*;
import static org.junit.Assert.assertEquals;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class AuctionHistoryServiceTest {
    @Test
    public void testAddAuctionRecord() {
        AuctionHistoryService service = new AuctionHistoryService();
        Administrator admin = new Administrator("admin", "password");
        AuctionRecord record = new AuctionRecord("123 Main St", "john_doe", 500000, "2022-01-01");
        admin.addAuctionRecord(service, record);
        assertEquals(1, service.viewAuctionRecords().size());
    }

    @Test
    public void testUpdateAuctionRecord() {
        AuctionHistoryService service = new AuctionHistoryService();
        Administrator admin = new Administrator("admin", "password");
        AuctionRecord record = new AuctionRecord("123 Main St", "john_doe", 500000, "2022-01-01");
        admin.addAuctionRecord(service, record);
        AuctionRecord updatedRecord = new AuctionRecord("123 Main St", "jane_doe", 600000, "2022-01-02");
        admin.updateAuctionRecord(service, 0, updatedRecord);
        assertEquals(updatedRecord, service.viewAuctionRecords().get(0));
    }

    @Test
    public void testDeleteAuctionRecord() {
        AuctionHistoryService service = new AuctionHistoryService();
        Administrator admin = new Administrator("admin", "password");
        AuctionRecord record = new AuctionRecord("123 Main St", "john_doe", 500000, "2022-01-01");
        admin.addAuctionRecord(service, record);
        admin.deleteAuctionRecord(service, 0);
        assertTrue(service.viewAuctionRecords().isEmpty());
    }

    @Test
    public void testViewAuctionRecords() {
        AuctionHistoryService service = new AuctionHistoryService();
        Administrator admin = new Administrator("admin", "password");
        AuctionRecord record1 = new AuctionRecord("123 Main St", "john_doe", 500000, "2022-01-01");
        AuctionRecord record2 = new AuctionRecord("456 Broadway", "jane_doe", 600000, "2022-01-02");
        admin.addAuctionRecord(service, record1);
        admin.addAuctionRecord(service, record2);
        List<AuctionRecord> records = service.viewAuctionRecords();
        assertEquals(2, records.size());
        assertTrue(records.contains(record1));
        assertTrue(records.contains(record2));
    }
}