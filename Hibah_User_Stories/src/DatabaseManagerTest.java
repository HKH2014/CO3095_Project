import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseManagerTest {

    private DatabaseManager databaseManager;
    
    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String TEST_DB_USER = "root";
    private static final String TEST_DB_PASSWORD = "toor";



    @BeforeEach
    void setUp() {
        // Initialize DatabaseManager before each test
        databaseManager = new DatabaseManager();
        databaseManager.setConnectionParameters(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "toor";

    private static Connection connection;

    @BeforeAll
    public static void setUp1() throws SQLException {
        // Establish a connection to the database before running the tests
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
    }

    @Test
    public void testAddProperty() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();

        // Fetch the current number of properties in the database
        int initialPropertyCount = getPropertyCount();

        // Create a new property for testing
        
        String propertyName = "Test Property";
        String propertyDescription = "This is a test property";
        int numBeds = 3;
        int numBathrooms = 2;
        int startBid = 100000;
        String endDate = "2023-12-31";

        // Call the addProperty method to add the new property
        boolean propertyAdded = databaseManager.addProperty(propertyName, propertyDescription, numBeds,
                numBathrooms, startBid, endDate);

        // Ensure that the property was added successfully
        assertTrue(propertyAdded);

        // Fetch the updated number of properties in the database
        int updatedPropertyCount = getPropertyCount();

        // Ensure that the number of properties increased after adding the new property
        assertTrue(updatedPropertyCount > initialPropertyCount);

        // Verify that the new property exists in the database
        assertTrue(propertyExists(propertyName));
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        // Close the database connection after running the tests
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Helper method to get the current number of properties in the database
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

    // Helper method to check if a property with the given name exists in the database
    private static boolean propertyExists(String propertyName) throws SQLException {
        String query = "SELECT * FROM properties WHERE property_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a property with the given name is found
            }
        }
    }

    @Test
    void testFetchUserCredentials() {
        // Test if user credentials are successfully obtained
        try {
            databaseManager.fetchUserCredentials();
            assertNotNull(databaseManager.getUserCredentialsMap());
            assertFalse(databaseManager.getUserCredentialsMap().isEmpty());
        } catch (ClassNotFoundException | SQLException e) {
            fail("Exception thrown while fetching user credentials: " + e.getMessage());
        }
    }

    @Test
    void testFetchUserCredentialsForSpecificUser() {
        // Test if user credentials for a specific user are correctly obtained
        try {
            databaseManager.fetchUserCredentials();

            // Assuming you have a user with a known username in your test data
            String knownUsername = "SR388";
            MyApplication.UserCredentials userCredentials = databaseManager.getUserCredentialsMap().get(knownUsername);

            // note to self - remember if you change anythibg in the db, change it here!
            assertNotNull(userCredentials);
            assertEquals("446f3afaec43a53bde1494db52ac517d2bfda31a1d0f40dd89f4db95eb814dd3", userCredentials.password);
            assertEquals("admin", userCredentials.userRole);
            assertEquals("Jonasu", userCredentials.firstName);
            assertEquals("Aran", userCredentials.lastName);
            assertEquals("2.1", userCredentials.userRating);
        } catch (ClassNotFoundException | SQLException e) {
            fail("Exception thrown while fetching user credentials: " + e.getMessage());
        }
    }
    

        @Test
        public void testIsUsernameValid() throws Exception {
            // Choose a valid and invalid username for testing
            String validUsername = "PC212";
            String invalidUsername = "sdfkhdsf";

            // Test with a valid username
            assertTrue(databaseManager.isUsernameValid(validUsername));

            // Test with an invalid username
            assertFalse(databaseManager.isUsernameValid(invalidUsername));
        }

        @Test
        public void testCalculateAverageRating() throws Exception {
            // Choose an existing username for testing
            String existingUsername = "PC212";

            // yo make sure the rating is accurate in the db!!!
            float existingRating = 4f;

            // Calculate the new average rating (e.g., the user rates them with 4.0)
            float newRating = 4.0f;
            float expectedAverageRating = (existingRating + newRating) / 2;

            // Update the rating in the database
            float actualAverageRating = databaseManager.calculateAverageRating(existingUsername, newRating);

            // Verify that the average rating is updated in the database with a small delta
            float delta = 1f; // Adjust the delta based on your precision requirements
            assertEquals(expectedAverageRating, actualAverageRating, delta);
        }

    }


