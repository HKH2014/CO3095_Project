import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertyHistoryTest {

    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String TEST_DB_USER = "root";
    private static final String TEST_DB_PASSWORD = "toor";

    private DatabaseManager databaseManager;
    private PropertyHistory propertyHistory;

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException {
        databaseManager = new DatabaseManager();
        databaseManager.setConnectionParameters(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
        propertyHistory = new PropertyHistory(databaseManager, new HashMap<>());
    }

    @Test
    public void testWritePropertiesToCSV() throws IOException, ClassNotFoundException, SQLException {
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        propertyHistory.writePropertiesToCSV();
        simulateInput1("A\nY\nN\n");
        File salesReportFile = new File("src/sales_report.csv");
        assertTrue(salesReportFile.exists());
    }
    
    private void simulateInput1(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}




//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.nio.charset.StandardCharsets;
//import java.sql.SQLException;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class PropertyHistoryTest {
//
//    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/hkh6";
//    private static final String TEST_DB_USER = "root";
//    private static final String TEST_DB_PASSWORD = "toor";
//
//    private DatabaseManager databaseManager;
//    private PropertyHistory propertyHistory;
//
//    @BeforeEach
//    public void setUp() throws ClassNotFoundException, SQLException {
//        databaseManager = new DatabaseManager();
//        databaseManager.setConnectionParameters(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
//        propertyHistory = new PropertyHistory(databaseManager, new HashMap<>());
//    }
//
//    @Test
//    public void testFilterByDate() throws IOException, ClassNotFoundException, SQLException {
//        simulateInput("2022-06-19\nN\n");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
//        propertyHistory.filterByDate();
//        String consoleOutput = outputStream.toString().trim();
//  
//        assertTrue(consoleOutput.contains("Property ID: 13"));
//        assertTrue(consoleOutput.contains("Item Name: Five bed semi-detached house"));
//        assertTrue(consoleOutput.contains("Final Sale Price: 48000500"));
//        assertTrue(consoleOutput.contains("Winning Bidder: NO14N"));
//        assertTrue(consoleOutput.contains("Sale Date: 2022-06-19"));
//    }
//
//    
//    private void simulateInput(String input) {
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//    }
//    
//    
//    @Test
//    public void testFilterHighLow() throws IOException, ClassNotFoundException, SQLException {
//        simulateInput("B\nN\n");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
//        propertyHistory.orderByFinalSalePrice();
//        String consoleOutput = outputStream.toString().trim();
//  
//        assertTrue(consoleOutput.contains("Property ID: 14"));
//        assertTrue(consoleOutput.contains("Item Name: A mansion"));
//        assertTrue(consoleOutput.contains("Final Sale Price: 498000500"));
//        assertTrue(consoleOutput.contains("Winning Bidder: TICK37"));
//        assertTrue(consoleOutput.contains("Sale Date: 2022-06-19"));
//    }
//    
//    
//  
//    @Test
//    public void testFilterLowHigh() throws IOException, ClassNotFoundException, SQLException {
//        simulateInput("C\nN\n");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
//        propertyHistory.orderByFinalSalePriceHigher();
//        String consoleOutput = outputStream.toString().trim();
//  
//        assertTrue(consoleOutput.contains("Property ID: 11"));
//        assertTrue(consoleOutput.contains("Item Name: 3 bed apartment"));
//        assertTrue(consoleOutput.contains("Final Sale Price: 400500"));
//        assertTrue(consoleOutput.contains("Winning Bidder: PC212"));
//        assertTrue(consoleOutput.contains("Sale Date: 2023-12-12"));
//    }
//}



