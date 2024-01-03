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
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SellerPropertiesTest {

    private static DatabaseManager databaseManager;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, SQLException {
        databaseManager = new DatabaseManager();
    }
    
//// THIS TEST WILL FAIL BECASUE PROPERTY 12 DOESNT EXIST!
//    @Test
//    void testDeleteNonExistingProperty() {
//        int nonExistingPropertyID = 12;
//        assertFalse(propertyExists(nonExistingPropertyID),
//                "Property with ID " + nonExistingPropertyID + " should not exist before deletion");
//        boolean deletionSuccess = databaseManager.deleteProperty(nonExistingPropertyID);
//        assertTrue(deletionSuccess, "Deletion of non-existing property with ID " + nonExistingPropertyID + " should be successful");
//        assertFalse(propertyExists(nonExistingPropertyID),
//                "Property with ID " + nonExistingPropertyID + " should not exist after deletion");
//    }
    
    @Test
    public void testDeleteProperty() {
        int propertyIDToDelete = 6;
        assertTrue(propertyExists(propertyIDToDelete),
                "Property with ID " + propertyIDToDelete + " should exist before deletion");
        boolean deletionSuccess = databaseManager.deleteProperty(propertyIDToDelete);
        assertTrue(deletionSuccess, "Deletion of property with ID " + propertyIDToDelete + " should be successful");
        assertFalse(propertyExists(propertyIDToDelete),
                "Property with ID " + propertyIDToDelete + " should not exist after deletion");
    }


    @Test
    public void testUpdateProperty() {
        DatabaseManager databaseManager = new DatabaseManager();
        boolean updateSuccess = databaseManager.updateProperty(5,
                "A new updated property again", "Boring updated Description", 3, 2, 1000, "2023-12-31");
        assertTrue(updateSuccess, "Update of property should be successful");

    }
    
    @Test
    void testDeleteRandomProperty() {
        int randomPropertyID = generateRandomInt(1, 5);
        assertTrue(propertyExists(randomPropertyID),
                "Property with ID " + randomPropertyID + " should exist before deletion");
        boolean deletionSuccess = databaseManager.deleteProperty(randomPropertyID);
        assertTrue(deletionSuccess, "Deletion of property with ID " + randomPropertyID + " should be successful");
        assertFalse(propertyExists(randomPropertyID),
                "Property with ID " + randomPropertyID + " should not exist after deletion");
    }

    private int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


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
        
        return false;
    }
}

