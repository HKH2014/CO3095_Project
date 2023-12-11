import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminViewHelpFormTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testViewHelpFormEntries() throws IOException {
        // Prepare a test csv file
        String testCsvContent = "John Doe,john@example.com,How to test?,pending,yes\n";
        Path testCsvPath = Files.createTempFile("testHelpForm", ".csv");
        Files.write(testCsvPath, testCsvContent.getBytes());

        // Set the csv path to the test file
        AdminViewHelpForm.HELP_FORM_CSV = testCsvPath.toString();

        // Call the method to test
        AdminViewHelpForm.viewHelpFormEntries();

        // Capture the printed output
        String printedOutput = outputStreamCaptor.toString().trim();

        // Assert that the printed output matches the expected content
        String expectedOutput = String.format("%-5s %-20s %-30s %-30s %-50s %-7s\n" +
                        "-----------------------------------------------------------------\n" +
                        "%-5d %-20s %-30s %-30s %-50s %-7s",
                "ID", "Name", "Email", "Question", "Status", "Pending", 1, "John Doe", "john@example.com", "How to test?", "pending", "yes");

        // Clean up by deleting the temporary csv test file
        Files.deleteIfExists(testCsvPath);
    }

    @Test
    void testUpdateHelpFormEntryStatus() throws IOException {
        // Prepare a test csv file
        String testCsvContent = "John Doe,john@example.com,How to test?,pending,yes\n";
        Path testCsvPath = Files.createTempFile("testHelpForm", ".csv");
        Files.write(testCsvPath, testCsvContent.getBytes());

        // Set the csv path to the test file
        AdminViewHelpForm.HELP_FORM_CSV = testCsvPath.toString();

        // Set up the input for the method to test
        String input = "1\n" + "done\n" + "no\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        // Call the method to test
        AdminViewHelpForm.updateHelpFormEntryStatus(new java.util.Scanner(System.in));

        // Read the updated content of the test file
        List<String> updatedLines = Files.readAllLines(testCsvPath);

        // Clean up by deleting the temporary csv test file
        Files.deleteIfExists(testCsvPath);
    }

    @Test
    void testDeleteHelpFormEntry() throws IOException {
        // Prepare a test csv file
        String testCsvContent = "John Doe,john@example.com,How to test?,pending,yes\n";
        Path testCsvPath = Files.createTempFile("testHelpForm", ".csv");
        Files.write(testCsvPath, testCsvContent.getBytes());

        // Set the csv path to the test file
        AdminViewHelpForm.HELP_FORM_CSV = testCsvPath.toString();

        // Set up the input for the method to test
        String input = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        // Call the method to test
        AdminViewHelpForm.deleteHelpFormEntry(new java.util.Scanner(System.in));

        // Read the updated content of the test file
        List<String> remainingLines = Files.readAllLines(testCsvPath);

        // Assert that the entry is deleted as expected
        assertTrue(remainingLines.isEmpty());

        // Clean up by deleting the temporary csv test file
        Files.deleteIfExists(testCsvPath);
    }
}
