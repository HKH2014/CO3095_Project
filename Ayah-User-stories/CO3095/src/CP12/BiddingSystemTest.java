package CP12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BiddingSystemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private BiddingSystem biddingSystem;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        biddingSystem = new BiddingSystem();
    }

    // Test when the buyer wins the bidding
    @Test
    void testNotifyWinnerWhenBuyerWins() {
        Buyer winningBuyer = new Buyer("John Doe", new EmailNotification());
        Property property = new Property("House A");
        property.setWinningBid(new Bid(500000.0, winningBuyer));

        biddingSystem.notifyWinner(winningBuyer, property);

        String expectedOutput = "Sending email to John Doe: Congratulations! You won the bidding for House A. Please proceed to payment.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    // Test when the buyer loses the bidding
    @Test
    void testNotifyWinnerWhenBuyerLoses() {
        Buyer losingBuyer = new Buyer("Jane Smith", new EmailNotification());
        Buyer winningBuyer = new Buyer("John Doe", new EmailNotification());
        Property property = new Property("House A");
        property.setWinningBid(new Bid(500000.0, winningBuyer));

        biddingSystem.notifyWinner(losingBuyer, property);

        String expectedOutput = "Sending email to Jane Smith: Unfortunately, you did not win the bidding for House A!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    // Test when there is no winning bid
    @Test
    void testNotifyWinnerWhenNoWinningBid() {
        Buyer buyer = new Buyer("John Doe", new EmailNotification());
        Property property = new Property("House A");

        biddingSystem.notifyWinner(buyer, property);

        String expectedOutput = "Sending email to John Doe: Unfortunately, you did not win the bidding for House A!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
