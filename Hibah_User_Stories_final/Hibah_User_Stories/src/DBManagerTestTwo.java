import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class DBManagerTestTwo {
	
    private DatabaseManager databaseManager;
    private static Connection connection;

    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String TEST_DB_USER = "root";
    private static final String TEST_DB_PASSWORD = "toor";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws SQLException {
        System.setOut(new PrintStream(outContent));
        connection = DriverManager.getConnection(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        databaseManager = new DatabaseManager();
        databaseManager.setConnectionParameters(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
    }
    

    @Test
    public void testDisplayProperties() throws ClassNotFoundException, SQLException {
        DatabaseManager.displayProperties();
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Property ID:"));
        assertTrue(consoleOutput.contains("Property Name:"));
        assertTrue(consoleOutput.contains("Property Description:"));
        assertTrue(consoleOutput.contains("Number of Beds:"));
        assertTrue(consoleOutput.contains("Number of Bathrooms:"));
        assertTrue(consoleOutput.contains("Start Bid:"));
        assertTrue(consoleOutput.contains("End Date:"));
        assertTrue(consoleOutput.contains("Highest Bid:"));
        assertTrue(consoleOutput.contains("Number of Bidders:"));
    }
    
    @Test
    public void testDisplayPropertyHistory() throws ClassNotFoundException, SQLException {
        DatabaseManager.displayPropertyHistory();
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Property ID: 11"));
        assertTrue(consoleOutput.contains("Item Name: 3 bed apartment"));
        assertTrue(consoleOutput.contains("Final Sale Price: 400500"));
        assertTrue(consoleOutput.contains("Winning Bidder: PC212"));
        assertTrue(consoleOutput.contains("Sale Date: 2023-12-12"));
    }
    
    @Test
    public void testAddPropertyWithCorrectDataTypes() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        int initialPropertyCount = getPropertyCount();
        String propertyName = generateRandomString(10);
        String propertyDescription = "Random Description";
        int numBeds = generateRandomInt(1, 5);
        int numBathrooms = generateRandomInt(1, 3);
        int startBid = generateRandomInt(10000, 50000);
        String endDate = "2023-12-31";
        boolean propertyAdded = databaseManager.addProperty(propertyName, propertyDescription, numBeds,
                numBathrooms, startBid, endDate);
        assertTrue(propertyAdded);
        int updatedPropertyCount = getPropertyCount();
        assertTrue(updatedPropertyCount > initialPropertyCount);

        assertTrue(propertyExists(propertyName));
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }


    private int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private static int getPropertyCount() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM properties")) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        }
    }

    private static boolean propertyExists(String propertyName) throws SQLException {
        String query = "SELECT * FROM properties WHERE property_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a property with the given name is found
            }
        }
    }
    
}