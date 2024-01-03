import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManageProfileTest {

	
    @Test
    public void testOptA() {
        String input = "A\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        System.setIn(System.in);
    }
    
    @Test
    public void testOptB() {
        String input = "B\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        System.setIn(System.in);
    }
	
    @Test
    public void testOptC() {
        String input = "C\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        System.setIn(System.in);
    }
    
    @Test
    void testRandomInput() {
        char randomLetter = generateRandomLetter(); // get a random imput from keyboard
        String input = randomLetter + "\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        System.setIn(System.in);
    }
    

    private char generateRandomLetter() {
        String alphabet = "GHIJKLMNOPQRSTUVWXYZghijklmnopqrstuvwxyz1234567890!_.,'~£";
        Random random = new Random();
        return alphabet.charAt(random.nextInt(alphabet.length()));
    }
}
