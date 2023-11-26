import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSellerAccountTest {

    private static final String TEST_CSV_FILE_PATH = "src//testCreateAccount.csv";

    @BeforeEach
    void setUp() {
        // Create a copy of the original create seller account csv file for the testing
        try {
            Files.copy(Path.of(CreateSellerAccount.CSV_FILE_PATH), Path.of(TEST_CSV_FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // Restore the original create seller account csv file after each test
        try {
            Files.copy(Path.of(TEST_CSV_FILE_PATH), Path.of(CreateSellerAccount.CSV_FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateAccount() {
        // Test createAccount method
        Scanner scanner = createTestScanner("testUsername", "testPassword", "testEmail");
        assertTrue(CreateSellerAccount.createAccount(scanner));

        // Verify the contents of the csv file after account creation
        assertCsvFileContains("testUsername,testPassword,testEmail");
    }

    @Test
    void testUpdateAccount() {
        // Test updateAccount method
        Scanner scanner = createTestScanner("testUsername", "newTestPassword", "newTestEmail");
    }
    

    private Scanner createTestScanner(String... inputs) {
        String inputString = String.join(System.lineSeparator(), inputs);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());
        return new Scanner(byteArrayInputStream);
    }

    private void assertCsvFileContains(String expectedContent) {
        try {
            String fileContent = Files.readString(Path.of(CreateSellerAccount.CSV_FILE_PATH));
            assertTrue(fileContent.contains(expectedContent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
	private void assertCsvFileDoesNotContain(String unexpectedContent) {
        try {
            String fileContent = Files.readString(Path.of(CreateSellerAccount.CSV_FILE_PATH));
            assertFalse(fileContent.contains(unexpectedContent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
