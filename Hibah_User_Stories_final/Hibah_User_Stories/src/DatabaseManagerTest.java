import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
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
        databaseManager = new DatabaseManager();
        databaseManager.setConnectionParameters(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "toor";

    private static Connection connection;

    @BeforeAll
    public static void setUp1() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
    }

    @Test
    public void testAddProperty() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        int initialPropertyCount = getPropertyCount();
        
        String propertyName = "Test Property";
        String propertyDescription = "This is a test property";
        int numBeds = 3;
        int numBathrooms = 2;
        int startBid = 100000;
        String endDate = "2023-12-31";

        boolean propertyAdded = databaseManager.addProperty(propertyName, propertyDescription, numBeds,
                numBathrooms, startBid, endDate);
        assertTrue(propertyAdded);
        int updatedPropertyCount = getPropertyCount();
        assertTrue(updatedPropertyCount > initialPropertyCount);
        assertTrue(propertyExists(propertyName));
    }

    @AfterAll
    public static void tearDown1() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
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

    
   
    
    @Test // test if user credentials are successfully obtained
    void testFetchUserCredentials() {
        try {
            databaseManager.fetchUserCredentials();
            assertNotNull(databaseManager.getUserCredentialsMap());
            assertFalse(databaseManager.getUserCredentialsMap().isEmpty());
        } catch (ClassNotFoundException | SQLException e) {
            fail("Exception thrown while fetching user credentials: " + e.getMessage());
        }
    }

    @Test //test if user credentials for a specific user are correctly obtained
    void testFetchUserCredentialsForSpecificUser() {
        try {
            databaseManager.fetchUserCredentials();
            String knownUsername = "SR388";
            MyApplication.UserCredentials userCredentials = databaseManager.getUserCredentialsMap().get(knownUsername);

            // note to self - remember if you change anythibg in the db, change it here!
            assertNotNull(userCredentials);
            assertEquals("446f3afaec43a53bde1494db52ac517d2bfda31a1d0f40dd89f4db95eb814dd3", userCredentials.password);
            assertEquals("admin", userCredentials.userRole);
            assertEquals("Jonas", userCredentials.firstName);
            assertEquals("Aran", userCredentials.lastName);
            assertEquals("2.1", userCredentials.userRating);
        } catch (ClassNotFoundException | SQLException e) {
            fail("Exception thrown while fetching user credentials: " + e.getMessage());
        }
    }
    

    @Test
    public void testIsUsernameValid() throws Exception {
        String validUsername = "PC212";
        String invalidUsername = "sdfkhdsf";
        assertTrue(databaseManager.isUsernameValid(validUsername));
        assertFalse(databaseManager.isUsernameValid(invalidUsername));
    }

    @Test
    public void testCalculateAverageRating() throws Exception {
        String existingUsername = "PC212";
        float existingRating = 4f;
        float newRating = 4.0f;
        float expectedAverageRating = (existingRating + newRating) / 2;
        float actualAverageRating = databaseManager.calculateAverageRating(existingUsername, newRating); //update rating in db
        float delta = 1f; // Adjust the delta based on your precision requirements
        assertEquals(expectedAverageRating, actualAverageRating, delta);
    }
    
    @Before
    public void setUp11() throws SQLException {
        initializeDatabase();
    }

    @Test
    public void testDeleteExistingAccount() {
        String existingUsername = "qwerty";
        boolean result = DatabaseManager.deleteAccount(existingUsername);
        assertTrue(result);
    }

    @Test
    public void testDeleteNonExistingAccount() {
        String nonExistingUsername = "PEETAMELLARK";
        boolean result = DatabaseManager.deleteAccount(nonExistingUsername);
        assertFalse(result);
    }

    private void initializeDatabase() throws SQLException {
        try (Connection con = DriverManager.getConnection(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
             PreparedStatement statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS seller_details (username VARCHAR(255))")) {
            statement.execute();
        }
        if (!userExists("LLOY16")) { // if the username doesnt exist already
            insertTestUser("LLOY16", "InitialFirstName", "InitialLastName", "InitialPassword");
        }
    }
    
    @Test
    public void testUpdateDetails() {
        // Test data
        String existingUsername = "LLOY16";
        String newFirstName = "Lloyd";
        String newLastName = "Garmadon";
        String newUsername = "LLOY16";
        String newUnhashedPassword = "Green_Ninja";
        String newPassword = hashPassword(newUnhashedPassword);

        boolean result = DatabaseManager.updateDetails(existingUsername, newFirstName, newLastName, newUsername, newPassword);
        assertTrue(result);
    }

    @Test
    public void testUpdateDetailsNonExistingUser() {
        String nonExistingUsername = "Fake12";
        String newFirstName = "no_im_real_xoxo";
        String newLastName = "new_surname";
        String newUsername = "Fake12";
        String newUnhashedPassword = "new_passwrd_idk";
        String newPassword = hashPassword(newUnhashedPassword);

        boolean result = DatabaseManager.updateDetails(nonExistingUsername, newFirstName, newLastName, newUsername, newPassword);
        assertFalse(result);
    }

    private boolean userExists(String username) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM seller_details WHERE username = ?")) {
            statement.setString(1, username);
            try (var resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    private void insertTestUser(String username, String firstName, String lastName, String unhashedPassword) throws SQLException {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = con.prepareStatement("INSERT INTO seller_details (username, firstname, lastname, pass) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, hashPassword(unhashedPassword));
            statement.execute();
        }
    }

    private String hashPassword(String unhashedPassword) {
        return unhashedPassword;
    }
    
}




