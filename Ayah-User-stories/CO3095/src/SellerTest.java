import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void testUpdateCredibilityWithValidRating() {
        Seller seller = new Seller(1, "John Doe", 3.0);
        seller.updateCredibility(4.5);

        assertEquals(1, seller.getNumberOfRatings());
        assertEquals(4.5, seller.getCredibility());
    }

    @Test
    void testUpdateCredibilityWithInvalidRating() {
        Seller seller = new Seller(1, "Jane Smith", 2.0);
        seller.updateCredibility(8.0);

        assertEquals(0, seller.getNumberOfRatings());  
        assertEquals(2.0, seller.getCredibility());
    }
}
