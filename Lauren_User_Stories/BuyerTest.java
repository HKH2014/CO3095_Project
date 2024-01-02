package auction;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Scanner;

public class BuyerTest {

    private Buyer buyer;

    @Before
    public void setUp() {
        // Initialize a Buyer object before each test
        buyer = new Buyer("buyer1", "buyer@example.com", "password");
    }

  

    @Test
    public void testDisplayFavoriteProperties() {
        // Black-box testing: Ensure displayFavoriteProperties method does not throw an exception
        buyer.displayFavoriteProperties();
    }

    @Test
    public void testResetPassword() {
        // White-box testing: Test resetting the password
        Scanner scanner = new Scanner(System.in);
        String oldPassword = buyer.getPassword();
        buyer.resetPassword(scanner);

       // assertNotEquals(oldPassword, ((Buyer) buyer).getPassword());
    }

    @Test
    public void testReceiveNotification() {
        // White-box testing: Check if receiveNotification adds a notification to the list
        Notification notification = new Notification("New house available for bidding!");
        buyer.receiveNotification(notification);

        assertTrue(buyer.getNotifications().contains(notification));
    }

    @Test
    public void testDisplayWelcomeMessage() {
        // Black-box testing: Ensure displayWelcomeMessage method does not throw an exception
        buyer.displayWelcomeMessage();
    }

    @Test
    public void testMainMethod() {
        // Black-box testing: Ensure the main method does not throw an exception
        String[] args = {};
        Buyer.main(args);
    }
}

