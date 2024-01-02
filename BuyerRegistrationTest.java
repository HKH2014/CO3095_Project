package auction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BuyerRegistrationTest {

    private final InputStream originalSystemIn = System.in;
    private final String lineSeparator = System.lineSeparator();

    @Before
    public void setUpStreams() {
        // Redirect System.in to provide input for testing
        String input = "testUsername" + lineSeparator + "testemail@example.com" + lineSeparator + "testPassword" + lineSeparator;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @After
    public void restoreStreams() {
        // Restore original System.in
        System.setIn(originalSystemIn);
    }

    @Test
    public void testBuyerRegistrationSuccess() {
        // Redirected input should be used for testing
        BuyerRegistration.main(new String[]{});

        
    }

    @Test
    public void testBuyerRegistrationFailure() {
        // Redirected input should be used for testing
        BuyerRegistration.main(new String[]{});


    }
}

