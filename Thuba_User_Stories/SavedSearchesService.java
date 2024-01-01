import java.util.*;

public class SavedSearchesService {
    private List<SavedSearch> savedSearches;

    public SavedSearchesService() {
        this.savedSearches = new ArrayList<>();
    }

    public void addSavedSearch(Buyer buyer, SavedSearch search) {
        savedSearches.add(search);
    }

    public void updateSavedSearch(Buyer buyer, int index, SavedSearch updatedSearch) {
        savedSearches.set(index, updatedSearch);
    }

    public void deleteSavedSearch(Buyer buyer, int index) {
        savedSearches.remove(index);
    }

    public List<SavedSearch> viewSavedSearches(Buyer buyer) {
        return new ArrayList<>(savedSearches);
    }
}
