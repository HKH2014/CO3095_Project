import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

public class SubmitHelpFormTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testSubmitNewForm() {
        String input = "John Doe\njohn.doe@example.com\nHow can I reset my password?\nyes\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void testDeleteForm() {
        // Provide input for Request ID 1
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    @Test
    public void testUpdateForm() {

        // Updated  form with requestId 1
        provideInput("Jane \n\n\n\nyes\n"); // Update only the name
    }

    @Test
    public void testViewSubmittedForms() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method to view submitted forms
        SubmitHelpForm.viewSubmittedForms();

        // Check if the output contains the expected form details
        assertTrue(outputStream.toString().contains("Submitted Forms"));
    }

    // Helper method to provide input to the Scanner during testing
    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}
