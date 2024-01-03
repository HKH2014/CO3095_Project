import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TermandConditionTest {

    @Test // attempt to edit terms and conditions as a seller
    public void testSellerEditing() throws IOException, ClassNotFoundException, SQLException {
        // doesn't need this but won't work without it???
        String expectedContent = "This is a modified line\n";
        String input = "This is a modified line\n5\nN\n"; 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("UserRole", "seller");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        TermandCondition.main(new String[]{}, userMap);
        String consoleOutput = outputStream.toString();
        assertFalse(consoleOutput.contains(expectedContent)); // modification is not allowed for sellers
    }
    
    @Test // have the role of admin but don't want to update terms and conditions
    public void testAdminNotEditing() throws IOException, ClassNotFoundException, SQLException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("UserRole", "admin");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulateUserInput(userMap, "N"); // Admin wants to make modifications
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Have a good day!"));
    }
	
    @Test // admin who wants to change specific terms and contions
    public void testAdminEditing() throws IOException, ClassNotFoundException, SQLException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("UserRole", "admin");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulateUserInput(userMap, "Y");
        simulateUserInput(userMap, "Hello there");
        simulateUserInput(userMap, "12");
        simulateUserInput(userMap, "N"); 
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Have a good day!"));
    }
    
    @Test // an admin who wants to edit multiple specific lines with specific terms and conditionss
    public void testAdminMultipleEditing() throws IOException, ClassNotFoundException, SQLException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("UserRole", "admin");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulateUserInput(userMap, "Y");
        simulateUserInput(userMap, "Hello there");
        simulateUserInput(userMap, "12");
        simulateUserInput(userMap, "Y"); 
        simulateUserInput(userMap, "Hello there AGAIN");
        simulateUserInput(userMap, "13");
        simulateUserInput(userMap, "N");
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Have a good day!"));
    }
    
    @Test // admin but enter a random line and random content to modify terms and conditions file
    public void testAdminRandomEditing() throws IOException, ClassNotFoundException, SQLException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("UserRole", "admin");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulateUserInput(userMap, "Y");
        String randomString = generateRandomString(40);
        simulateUserInput(userMap, randomString);
        int randomInteger = generateRandomInteger(1, 25);
        simulateUserInput(userMap, String.valueOf(randomInteger));
        simulateUserInput(userMap, "N");
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Have a good day!"));
    }
    
    private void simulateUserInput(Map<String, String> userMap, String userInput) {
        String input = userInput + "\n"; 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream); 
        try {
            TermandCondition.main(new String[]{}, userMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String generateRandomString(int maxLength) {
        Random random = new Random();
        int length = random.nextInt(maxLength) + 1;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    private int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}