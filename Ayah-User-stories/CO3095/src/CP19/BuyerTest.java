package CP19;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BuyerTest {
    @Test
    public void testAddSellerRating() {
        Buyer buyer = new Buyer();
        Seller seller = new Seller(1, "John Doe", 3.0);

        // Add a rating
        buyer.addSellerRating(seller, 4.5);

        // Assert that the rating was added
        assertEquals(1, buyer.getSellerRatings().size());
        assertEquals(4.5, buyer.getSellerRatings().get(0).getRating(), 0.01);
    }

    @Test
    public void testUpdateSellerRating() {
        Buyer buyer = new Buyer();
        Seller seller = new Seller(1, "John Doe", 3.0);

        // Add a rating
        buyer.addSellerRating(seller, 4.5);

        // Update the rating
        buyer.updateSellerRating(seller, 3.8);

        // Assert that the rating was updated
        assertEquals(1, buyer.getSellerRatings().size());
        assertEquals(3.8, buyer.getSellerRatings().get(0).getRating(), 0.01);
    }

    @Test
    public void testDeleteSellerRating() {
        Buyer buyer = new Buyer();
        Seller seller = new Seller(1, "John Doe", 3.0);

        // Add a rating
        buyer.addSellerRating(seller, 4.5);

        // Delete the rating
        buyer.deleteSellerRating(seller);

        // Assert that the rating was deleted
        assertEquals(0, buyer.getSellerRatings().size());
    }

    @Test
    public void testViewAllSellerRatings() {
        Buyer buyer = new Buyer();
        Seller seller1 = new Seller(1, "John Doe", 3.0);
        Seller seller2 = new Seller(2, "Jane Dough", 1.0);

        // Add ratings
        buyer.addSellerRating(seller1, 4.5);
        buyer.addSellerRating(seller2, 3.0);

        // View all ratings
        String expectedOutput = "Buyer rated Seller John Doe with a rating of 4.5\n" +
                                "Buyer rated Seller Jane Dough with a rating of 3.0\n";

        assertEquals(expectedOutput, getActualOutput(buyer));
    }

    
    private String getActualOutput(Buyer buyer) {
        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

      
        buyer.viewAllSellerRatings();

        // Reset System.out
        System.out.flush();
        System.setOut(originalOut);

        return outputStream.toString();
    }
}

