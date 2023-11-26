import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterPropertiesTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream systemOutContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }
    @Test
    void testFilterByBathrooms() {
        // Prepare test data
        List<Map<String, Object>> propertiesList = Arrays.asList(
                createProperty(1, "Property A", "Description A", 2, 2, "12345"),
                createProperty(2, "Property B", "Description B", 3, 2, "54321"),
                createProperty(3, "Property C", "Description C", 2, 1, "67890")
        );

        // Set propertiesList in FilterProperties class
        FilterProperties.setPropertiesList(propertiesList);

        // Set user input for filter choice
        ByteArrayInputStream userInput = new ByteArrayInputStream("A\n2\nF\n".getBytes());
        System.setIn(userInput);

        // Execute the main method (assuming filterByBathrooms is called in the main method)
        FilterProperties.main(null);

        // Check the output
        assertTrue(systemOutContent.toString().contains("Filtering by 2 bathrooms:"));
    }
    
    @Test
    void testFilterByBeds() {
        // Prepare test data
        List<Map<String, Object>> propertiesList = Arrays.asList(
                createProperty(1, "Property A", "Description A", 2, 2, "12345"),
                createProperty(2, "Property B", "Description B", 3, 2, "54321"),
                createProperty(3, "Property C", "Description C", 2, 1, "67890")
        );

        // Set propertiesList in FilterProperties class
        FilterProperties.setPropertiesList(propertiesList);

        // Set user input for filter choice
        ByteArrayInputStream userInput = new ByteArrayInputStream("A\n2\nF\n".getBytes());
        System.setIn(userInput);

        // Execute the main method (assuming filterByBathrooms is called in the main method)
        FilterProperties.main(null);

    }
        @Test
        void testClearAllFilters() {
            List<Map<String, Object>> propertiesList = Arrays.asList(
                    createProperty(1, "Property A", "Description A", 2, 2, "12345"),
                    createProperty(2, "Property B", "Description B", 3, 2, "54321"),
                    createProperty(3, "Property C", "Description C", 2, 1, "67890")
            );

            FilterProperties.setPropertiesList(propertiesList);

        }
@Test
void testFilterByAddress() {
    // Prepare test data
    List<Map<String, Object>> propertiesList = Arrays.asList(
            createProperty(1, "Property A", "Description A", 2, 2, "12345"),
            createProperty(2, "Property B", "Description B", 3, 2, "54321"),
            createProperty(3, "Property C", "Description C", 2, 1, "67890")
    );

    // Set propertiesList in FilterProperties class
    FilterProperties.setPropertiesList(propertiesList);

    // Set user input for filter choice
    ByteArrayInputStream userInput = new ByteArrayInputStream("A\n2\nF\12345".getBytes());
    System.setIn(userInput);

    // Execute the main method (assuming filterByBathrooms is called in the main method)
    FilterProperties.main(null);
}
    

    @Test
    void testUndoLatestFilter() {
        List<Map<String, Object>> propertiesList = Arrays.asList(
                createProperty(1, "Property A", "Description A", 2, 2, "12345"),
                createProperty(2, "Property B", "Description B", 3, 2, "54321"),
                createProperty(3, "Property C", "Description C", 2, 1, "67890")
        );

        FilterProperties.setPropertiesList(propertiesList);

        FilterProperties.applyFilter(propertiesList);

        FilterProperties.undoLatestFilter();

        assertEquals(propertiesList, FilterProperties.getPropertiesList());
        assertTrue(systemOutContent.toString().contains("Undo latest filter."));
    }

    private Map<String, Object> createProperty(int id, String name, String description, int beds, int bathrooms, String postcode) {
        Map<String, Object> property = new HashMap<>();
        property.put("ID", id);
        property.put("Name", name);
        property.put("Description", description);
        property.put("Beds", beds);
        property.put("Bathrooms", bathrooms);
        property.put("Postcode", postcode);
        return property;
    }
}
