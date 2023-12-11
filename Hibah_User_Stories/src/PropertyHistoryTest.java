import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyHistoryTest {

    private PropertyHistory propertyHistory;
    private static DatabaseManager mockDatabaseManager;
    private Map<String, String> authenticatedUserMap;

    @Test
    void testWritePropertiesToCSV() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
        mockDatabaseManager = new MockDatabaseManager();
        authenticatedUserMap = new HashMap<>();
        propertyHistory = new PropertyHistory(mockDatabaseManager, authenticatedUserMap);

        // Use reflection to access the private method and call directly
        Method method = PropertyHistory.class.getDeclaredMethod("writePropertiesToCSV");
        method.setAccessible(true);
        method.invoke(propertyHistory);

        // Assertions or verifications based on your specific needs
        // In this example, we're checking if the mock DatabaseManager was called
        // call method twice for the loop
        assertEquals(2, ((MockDatabaseManager) mockDatabaseManager).getWritePropertiesToCSVCallCount());
    }


    private static class MockDatabaseManager extends DatabaseManager {
        private boolean filterPropertiesByDateCalled = false;
        private int writePropertiesToCSVCallCount = 0;

        @Override
        public void filterPropertiesByDate(String date) throws ClassNotFoundException, SQLException {
            // Simulate the behavior of filterPropertiesByDate method
            filterPropertiesByDateCalled = true;
        }

        public boolean isFilterPropertiesByDateCalled() {
            return filterPropertiesByDateCalled;
        }
        @Override
        public void writePropertiesToCSV() throws ClassNotFoundException, SQLException, IOException {
            // Increment the call count to track how many times the method is called
            writePropertiesToCSVCallCount++;
        }

        public int getWritePropertiesToCSVCallCount() {
            return writePropertiesToCSVCallCount;
        }
        
    }
    
        @Test
        static void testFilterByDate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, SQLException, ClassNotFoundException {
            // Mock user input to simulate entering a date
            ByteArrayInputStream inputStream = new ByteArrayInputStream("2023-01-01\n".getBytes());
            System.setIn(inputStream);

            // Capture the console output to check later
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            PropertyHistoryTest testClass = new PropertyHistoryTest();
            testClass.propertyHistory = new PropertyHistory(testClass.mockDatabaseManager, testClass.authenticatedUserMap);

            // Use reflection to access the private method and call directly
            Method method = PropertyHistory.class.getDeclaredMethod("filterByDate");
            method.setAccessible(true);
            method.invoke(testClass.propertyHistory);

            // Assertions or verifications based on your specific needs
            // In this example, we're checking if the mock DatabaseManager's filterPropertiesByDate was called
            assertTrue(((MockDatabaseManager) testClass.mockDatabaseManager).isFilterPropertiesByDateCalled());

            // Check the console output
            String consoleOutput = outputStream.toString().trim();
            assertTrue(consoleOutput.contains("Property ID:"));
            assertTrue(consoleOutput.contains("Item Name:"));
            assertTrue(consoleOutput.contains("Final Sale Price:"));
            assertTrue(consoleOutput.contains("Winning Bidder:"));
            assertTrue(consoleOutput.contains("Sale Date:"));
            assertTrue(consoleOutput.contains("----------------------------"));
        }
    
    
}


