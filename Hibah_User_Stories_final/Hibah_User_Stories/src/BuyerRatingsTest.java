import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class BuyerRatingsTest {
	
	// TEST FAILS BECAUSE NO USERNAME IS SELECTED SO ASSERTION ERROR
//    @Test
//    public void testRateUserNo() {
//        String input = "N\n";
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        assertTrue(outContent.toString().contains("Would you like to rate this user? (Y/N): "));
//        assertTrue(outContent.toString().contains("Have a good day!"));
//    }
    
    // TEST FAILS BECAUSE NO USERNAME IS SELECTED SO ASSERTION ERRO
//    @Test
//    public void testRateUserYes() {
//        String input = "Y\n5\n";
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        DatabaseManager databaseManager = new databaseManager(); 
//        BuyerRatings buyerRatings = new BuyerRatings(databaseManager);
//        assertTrue(outContent.toString().contains("Would you like to rate this user? (Y/N): "));
//        assertTrue(outContent.toString().contains("Enter a rating between 1 and 5: "));
//        assertTrue(outContent.toString().contains("Rating applied - "));
//        assertTrue(outContent.toString().contains("Your rating: 5"));
//    }

    @Test // test a valid rating
    public void testValidRating() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        float result = BuyerRatings.getUserRating(new Scanner(System.in));
        assertEquals(4.0, result, 0.001); 
    }

    @Test // test an invalid entered rating then a valid one
    public void testInvalidRatingThenValidRating() {
        System.setIn(new ByteArrayInputStream("10\n3.5\n".getBytes()));
        float result = BuyerRatings.getUserRating(new Scanner(System.in));
        assertEquals(3.5, result, 0.001);
    }

    @Test // test the lowest possible rating
    public void testLowerBoundary() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        float result = BuyerRatings.getUserRating(new Scanner(System.in));
        assertEquals(1.0, result, 0.001); 
    }

    @Test // test the highest possible rating
    public void testUpperBoundary() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        float result = BuyerRatings.getUserRating(new Scanner(System.in));
        assertEquals(5.0, result, 0.001); 
    }

    @Test // test a series of random invalid ratings followed by a random valid rating
    public void testRandomRatings() {
        Random random = new Random();
        int validRating = random.nextInt(5) + 1; //  get a valid rating here boiii
        String invalidInput = generateRandomInvalidInput(random, 3, 6, 20);
        System.setIn(new ByteArrayInputStream((invalidInput + validRating + "\n").getBytes()));
        float result = BuyerRatings.getUserRating(new Scanner(System.in));
        assertEquals(validRating, result, 0.001);
    }

    private String generateRandomInvalidInput(Random random, int count, int min, int max) {
        StringBuilder invalidInput = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int invalidNumber = random.nextInt(max - min + 1) + min;
            invalidInput.append(invalidNumber).append("\n");
        }
        return invalidInput.toString();
    }

}
