import java.util.*;
import auction.*;

public class BidHistoryService {
    private List<Bid> bidHistory;

    public BidHistoryService() {
        this.bidHistory = new ArrayList<>();
    }

    public void addBid(Buyer buyer, Bid bid) {
        bidHistory.add(bid);
    }

    public void updateBid(Buyer buyer, int index, Bid updatedBid) {
        bidHistory.set(index, updatedBid);
    }

    public void deleteBid(Buyer buyer, int index) {
        bidHistory.remove(index);
    }

    public List<Bid> viewBidHistory(Buyer buyer) {
        return new ArrayList<>(bidHistory);
    }
}
