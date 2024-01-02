package auction;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdminRegistrationTest {

    @Test
    public void testAdminSupervisionPermission() {
        // Creating an admin for testing
        Admin admin = new Admin("admin123", "adminPassword", true, "Admin Full Name", "admin@example.com");

        // Checking if the admin has supervision permission
        assertTrue(admin.hasSupervisionPermission());
    }

    @Test
    public void testAdminBanUser() {
        // Creating an admin for testing
        Admin admin = new Admin("admin123", "adminPassword", true, "Admin Full Name", "admin@example.com");

        // Creating a user to be banned
        User userToBan = new User("user123", "userPassword");

        // Banning the user
        admin.banUser(userToBan, "Breach of terms and conditions");

        // Checking if the user is banned
        assertTrue(userToBan.isBanned());
    }

    @Test
    public void testAdminCreateUserAccount() {
        // Creating an admin for testing
        Admin admin = new Admin("admin123", "adminPassword", true, "Admin Full Name", "admin@example.com");

        // Creating a personalized user account
        admin.createUserAccount("newUser123", "newUserPassword", "New User Full Name", "newuser@example.com", false);


    }
}

