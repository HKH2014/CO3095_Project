import java.util.*;

public class WatchlistService {
    private Map<String, Watchlist> watchlists;

    public WatchlistService() {
        this.watchlists = new HashMap<>();
    }

    public void addProperty(Buyer buyer, Property property) {
        getWatchlist(buyer.getUsername()).addProperty(property);
    }

    public void updateProperty(Buyer buyer, String propertyId, String newDetails) {
        getWatchlist(buyer.getUsername()).updateProperty(propertyId, newDetails);
    }

    public void removeProperty(Buyer buyer, String propertyId) {
        getWatchlist(buyer.getUsername()).removeProperty(propertyId);
    }

    public List<Property> viewProperties(Buyer buyer) {
        return getWatchlist(buyer.getUsername()).viewProperties();
    }

    private Watchlist getWatchlist(String username) {
        return watchlists.computeIfAbsent(username, k -> new Watchlist());
    }
}