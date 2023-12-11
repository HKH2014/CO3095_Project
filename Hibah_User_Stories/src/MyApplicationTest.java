import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyApplicationTest {

    private DatabaseManager databaseManager;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        // Initialize DatabaseManager before each test
        databaseManager = new DatabaseManager();
        databaseManager.fetchUserCredentials();
    }

 // an authorised user that can access the website
    @Test
    void testAdminUser() {
        assertTrue(loginUser("SR388", "Phazon"));
    }

    // non admin user (or seller user either) so they get rejected
    @Test
    void testNonAdminUser() {
        // Test for a user that does not have the role of admin
        assertTrue(loginUser("PC212", "brother"));
    }

    @Test
    void testIncorrectCredentials() {
        // Test for a user providing incorrect credentials
        assertFalse(loginUser("hehehehehe", "HAHAHAHAHA"));
    }

    // Helper method to simulate user login
    private boolean loginUser(String username, String password) {
        try {
            // Simulate user input
            Map<String, String> authenticatedUserMap = new HashMap<>();
            authenticatedUserMap.put("Username", username);
            authenticatedUserMap.put("Password", password);

            // Simulate login process
            MyApplication.UserCredentials userCredentials = databaseManager.getUserCredentialsMap().get(username);
            if (userCredentials != null && userCredentials.password.equals(MyApplication.hashPassword(password))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    


//    @Test
//    void testDisplayMenuOptionA() {
//        // Test displayMenu with option A (View Profile)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "A\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    @Test
//    void testDisplayMenuOptionB() {
//        // Test displayMenu with option B (View Terms and Conditions)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "B\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    @Test
//    void testDisplayMenuOptionC() {
//        // Test displayMenu with option C (View Seller Properties)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "C\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    @Test
//    void testDisplayMenuOptionD() {
//        // Test displayMenu with option D (View History of Sales)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "D\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    @Test
//    void testDisplayMenuOptionE() {
//        // Test displayMenu with option E (Rate Previous Buyers)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "E\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    @Test
//    void testDisplayMenuOptionF() {
//        // Test displayMenu with option F (Exit Application)
//        Map<String, String> authenticatedUserMap = createAuthenticatedUserMap();
//        String simulatedInput = "F\n";
//        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(inputStream);
//
//        assertDoesNotThrow(() -> MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap));
//    }
//
//    private Map<String, String> createAuthenticatedUserMap() {
//        Map<String, String> authenticatedUserMap = new HashMap<>();
//        authenticatedUserMap.put("Username", "testuser");
//        authenticatedUserMap.put("Password", "testpassword");
//        authenticatedUserMap.put("FirstName", "Test");
//        authenticatedUserMap.put("LastName", "User");
//        authenticatedUserMap.put("UserRole", "seller");
//        authenticatedUserMap.put("UserRating", "4.5");
//
//        return authenticatedUserMap;
//    }
}
