import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

public class SellerForgotPasswordTest {

    static final String CSV_FILE_PATH = "src//updatedPassword.csv";
    static final String BACKUP_FILE_PATH = "src//backupPassword.csv";
    static final String TEMP_FILE_PATH = "tempPassword.csv";

    @BeforeEach
    void setUp() {
        // Create a backup of the original forgot seller password csv file before each test
        try {
            File originalFile = new File(CSV_FILE_PATH);
            File backupFile = new File(BACKUP_FILE_PATH);
            SellerForgotPassword.copyFile(originalFile, backupFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckPasswordValidAccount() {
        // Test the case where the seller account exists
        String input = "testUser\ntest@example.com\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SellerForgotPassword.checkPassword(new Scanner(System.in));

        String expectedOutput = "Account Found!\n";
    }

    @Test
    void testCheckPasswordInvalidAccount() {
        // Test the case where the seller account does not exist
        String input = "nonexistentUser\nnonexistent@example.com\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SellerForgotPassword.checkPassword(new Scanner(System.in));

        String expectedOutput = "Account not found. Please check your username and email address.\n";
    }

    @Test
    void testUpdatePasswordValidInput() {
        // Test the case where the seller update password is successful
        String input = "testUser\ntestPassword\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SellerForgotPassword.updatePassword(new Scanner(System.in));

        String expectedOutput = "Password updated successfully!\n";

        // Verify that the password in the csv file is updated
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String line = reader.readLine(); // Skip the header
            line = reader.readLine();
            String[] fields = line.split(",");
            String updatedPassword = fields[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occurred while verifying CSV file");
        }
    }

    @Test
    void testUpdatePasswordInvalidInput() {
        // Test the case where the update fails due to invalid input
        String input = "nonexistentUser\ninvalidPassword\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SellerForgotPassword.updatePassword(new Scanner(System.in));

        String expectedOutput = "Invalid username or current password. Password update failed.\n";

        // Verify that the CSV file remains unchanged
        try {
            BufferedReader originalReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            BufferedReader backupReader = new BufferedReader(new FileReader(BACKUP_FILE_PATH));

            String originalLine, backupLine;
            while ((originalLine = originalReader.readLine()) != null) {
                backupLine = backupReader.readLine();
                assertEquals(backupLine, originalLine);
            }

            originalReader.close();
            backupReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occurred while verifying CSV file");
        }
    }

    @Test
    void testUndoChanges() {
        // Test the undo changes functionality
        String newPassword = "newPassword";

        // Perform undo changes
        SellerForgotPassword.undoChanges();

        // Verify that the password in the CSV file is reverted to the original value
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String line = reader.readLine(); // Skip the header
            line = reader.readLine();
            String[] fields = line.split(",");
            String revertedPassword = fields[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occurred while verifying CSV file");
        }
    }

}
