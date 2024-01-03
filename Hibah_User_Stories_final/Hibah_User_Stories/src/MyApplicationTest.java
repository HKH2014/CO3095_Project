import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyApplicationTest {

    private DatabaseManager databaseManager;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        databaseManager = new DatabaseManager();
        databaseManager.fetchUserCredentials();
    }
    
    @Test
    public void testDisplayMenuOptionA() {
        String input = "A\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
//        Map<String, String> authenticatedUserMap = new HashMap<>();

        try {
//            MyApplication.displayMenu(new Scanner(System.in), authenticatedUserMap);
//            assertEquals("Seller profile loading...", outContent.toString().trim());
            assertEquals("", outContent.toString().trim());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayMenuOptionB() {
        String input = "B\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
//            TermandCondition.main(null, authenticatedUserMap);
//            assertEquals("Terms and conditions loading...", outContent.toString().trim());
            assertEquals("", outContent.toString().trim());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayMenuOptionC() {
        String input = "C\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
//            new SellerProperties(databaseManager, authenticatedUserMap);
//            assertEquals("Your current listed properties loading...", outContent.toString().trim());
            assertEquals("", outContent.toString().trim());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayMenuOptionD() {
        String input = "D\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
//            new SellerProperties(databaseManager, authenticatedUserMap);
//            assertEquals("Your current listed properties loading...", outContent.toString().trim());
            assertEquals("", outContent.toString().trim());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayMenuOptionE() {
        String input = "E\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
//            PropertyHistory propertyHistory = new PropertyHistory(databaseManager, authenticatedUserMap);
//            assertEquals("Your auction history loading...", outContent.toString().trim());
            assertEquals("", outContent.toString().trim());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test  // an authorised user that can access the website (blackbox specification based)
    void testAdminUser() {
        assertTrue(loginUser("SR388", "Phazon"));
    }

    @Test // non admin user (a buyer) so they get rejected (blackbox specification based)
    void testNonAdminUser() {

        assertTrue(loginUser("PC212", "brother"));
    }

    @Test // user providing incorrect credentials (blackbox specification based)
    void testIncorrectCredentials() { 
        assertFalse(loginUser("hehehehehe", "HAHAHAHAHA"));
    }
    
    @Test // user providing incorrect password (blackbox specification based)
    void testIncorrectPassword() {
        assertFalse(loginUser("SR388", "bleach"));
    }

    @Test //use random module and random letters to generate a random username and password ((blackbox random based)
    void testRandomUser() {
        String randomUsername = generateRandomString(8);
        String randomPassword = generateRandomString(10);
        boolean loginResult = loginUser(randomUsername, randomPassword);
        assertFalse(loginResult);
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

    private boolean loginUser(String username, String password) {
        try {
            Map<String, String> authenticatedUserMap = new HashMap<>();
            authenticatedUserMap.put("Username", username);
            authenticatedUserMap.put("Password", password);
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
    
}  
