import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavedSearchesServiceTest {
    @Test
    public void testAddSavedSearch() {
        SavedSearchesService service = new SavedSearchesService();
        Buyer buyer = new Buyer("john_doe", "password");
        SavedSearch search = new SavedSearch("New York", 500000, 1000000);
        buyer.addSavedSearch(service, search);
        assertEquals(1, service.viewSavedSearches(buyer).size());
    }

    @Test
    public void testUpdateSavedSearch() {
        SavedSearchesService service = new SavedSearchesService();
        Buyer buyer = new Buyer("john_doe", "password");
        SavedSearch search = new SavedSearch("New York", 500000, 1000000);
        buyer.addSavedSearch(service, search);
        SavedSearch updatedSearch = new SavedSearch("Los Angeles", 600000, 1200000);
        buyer.updateSavedSearch(service, 0, updatedSearch);
        assertEquals(updatedSearch, service.viewSavedSearches(buyer).get(0));
    }

    @Test
    public void testDeleteSavedSearch() {
        SavedSearchesService service = new SavedSearchesService();
        Buyer buyer = new Buyer("john_doe", "password");
        SavedSearch search = new SavedSearch("New York", 500000, 1000000);
        buyer.addSavedSearch(service, search);
        buyer.deleteSavedSearch(service, 0);
        assertTrue(service.viewSavedSearches(buyer).isEmpty());
    }

    @Test
    public void testViewSavedSearches() {
        SavedSearchesService service = new SavedSearchesService();
        Buyer buyer = new Buyer("john_doe", "password");
        SavedSearch search1 = new SavedSearch("New York", 500000, 1000000);
        SavedSearch search2 = new SavedSearch("Los Angeles", 600000, 1200000);
        buyer.addSavedSearch(service, search1);
        buyer.addSavedSearch(service, search2);
        List<SavedSearch> searches = service.viewSavedSearches(buyer);
        assertEquals(2, searches.size());
        assertTrue(searches.contains(search1));
        assertTrue(searches.contains(search2));
    }
}
