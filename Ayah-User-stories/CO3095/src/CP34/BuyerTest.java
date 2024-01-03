package CP34;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {

    private Buyer buyer;

    @BeforeEach
    void setUp() {
        buyer = new Buyer();
    }

    @Test
    void testAddReview() {
        Review review = new Review("Another test review.");
        buyer.addReview(review);

        assertEquals(1, buyer.getReviews().size());
        assertTrue(buyer.getReviews().contains(review));
    }

    @Test
    void testGetReviews() {
        Review review1 = new Review("Review 1");
        Review review2 = new Review("Review 2");
        buyer.addReview(review1);
        buyer.addReview(review2);

        assertEquals(2, buyer.getReviews().size());
        assertTrue(buyer.getReviews().contains(review1));
        assertTrue(buyer.getReviews().contains(review2));
    }
}
