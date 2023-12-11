import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class FAQTest {

    @Test
    void filterByTopic() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.filterByTopic(1);

        assertEquals(2, filteredList.size());
    }

    @Test
    void filterByPriority() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.filterByPriority(2);

        assertEquals(1, filteredList.size());
    }

    @Test
    void filterByHelpfulness() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.filterByHelpfulness(5);

        assertEquals(1, filteredList.size());

    }

    @Test
    void clearAllFilters() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.clearAllFilters();

        assertEquals(testQanda.size(), filteredList.size());

    }

    @Test
    void applyFilter() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.filterByTopic(1);
        FAQ.applyFilter(filteredList);

        assertEquals(filteredList.size(), FAQ.qanda.size());

        // Add more assertions as needed
    }

    @Test
    void undoLatestFilter() {
        List<Map<String, Object>> testQanda = createTestQanda();
        FAQ.qanda = testQanda;
        List<Map<String, Object>> filteredList = FAQ.filterByTopic(1);
        FAQ.applyFilter(filteredList);

        int currentStateBeforeUndo = FAQ.getCurrentState();
        FAQ.undoLatestFilter();
        int currentStateAfterUndo = FAQ.getCurrentState();

        assertEquals(currentStateBeforeUndo - 1, currentStateAfterUndo);

    }

    @Test
    void printFAQDetails() {
        assertDoesNotThrow(() -> FAQ.printFAQDetails(createTestQanda().get(0)));
    }

    // Helper method to create a test FAQ list
    private List<Map<String, Object>> createTestQanda() {
        return List.of(
                Map.of("ID", 1, "Question", "Test Question 1", "Answer", "Test Answer 1", "Topic", 1, "Priority", 2, "Helpfulness", 3),
                Map.of("ID", 2, "Question", "Test Question 2", "Answer", "Test Answer 2", "Topic", 1, "Priority", 1, "Helpfulness", 5),
                Map.of("ID", 3, "Question", "Test Question 3", "Answer", "Test Answer 3", "Topic", 2, "Priority", 3, "Helpfulness", 4)
        );
    }
}
