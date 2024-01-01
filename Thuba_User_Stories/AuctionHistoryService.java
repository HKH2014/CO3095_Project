import java.util.*;
import auction.*;

public class AuctionHistoryService {
    private List<AuctionRecord> auctionRecords;

    public AuctionHistoryService() {
        this.auctionRecords = new ArrayList<>();
    }

    public void addAuctionRecord(Administrator admin, AuctionRecord record) {
        auctionRecords.add(record);
    }

    public void updateAuctionRecord(Administrator admin, int index, AuctionRecord updatedRecord) {
        auctionRecords.set(index, updatedRecord);
    }

    public void deleteAuctionRecord(Administrator admin, int index) {
        auctionRecords.remove(index);
    }

    public List<AuctionRecord> viewAuctionRecords() {
        return new ArrayList<>(auctionRecords);
    }
}