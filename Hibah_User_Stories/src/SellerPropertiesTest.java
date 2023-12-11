import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SellerPropertiesTest {

    private static DatabaseManager databaseManager;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, SQLException {
        // Set up the DatabaseManager for testing
        databaseManager = new DatabaseManager();
    }

    @Test
    public void testDeleteProperty() {
        // Use a known PropertyID that exists in your database
        int propertyIDToDelete = 343;

        // Before deleting, check if the property exists in the database
        assertTrue(propertyExists(propertyIDToDelete),
                "Property with ID " + propertyIDToDelete + " should exist before deletion");

        // Delete the property
        boolean deletionSuccess = databaseManager.deleteProperty(propertyIDToDelete);

        // Assert that the deletion was successful
        assertTrue(deletionSuccess, "Deletion of property with ID " + propertyIDToDelete + " should be successful");

        // After deleting, check if the property no longer exists in the database
        assertFalse(propertyExists(propertyIDToDelete),
                "Property with ID " + propertyIDToDelete + " should not exist after deletion");
    }


    @Test
    public void testUpdateProperty() {
        // Create an instance of DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager();

        // Call the method with the property details
        boolean updateSuccess = databaseManager.updateProperty(6,
                "A new updated property again", "Boring updated Description", 3, 2, 1000, "2023-12-31");

        // Assert that the update was successful
        assertTrue(updateSuccess, "Update of property should be successful");

    }

    // Helper method to check if a property with the given ID exists in the database
    public boolean propertyExists(int propertyID) {

    	String query = "SELECT COUNT(*) FROM properties WHERE property_ID = ?";
    	
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hkh6", "root", "toor");
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, propertyID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count is greater than 0, the property exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // In case of an exception or no results
    }
}

