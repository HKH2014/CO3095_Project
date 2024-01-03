import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class DatabaseManager {
	//note to assessors - if you want to test the code, change this so you can run this in your own software
	// second note - i have submitted some dummy data for my database as an SQL script, execute the data in that script and use that for testing
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hkh6";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "toor";
	
    // reference to the dictionary with the user details (credentials) to be user in later user stories
    private Map<String, MyApplication.UserCredentials> userCredentialsMap;

    public DatabaseManager() {
        this.userCredentialsMap = new HashMap<>();
    }

    public void setConnectionParameters(String dbUrl, String dbUser, String dbPassword) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set connection parameters for testing");
        }
    }
    
    // connect to the database and select all the users from the table (its called 'seller_details' in DB)
    public void fetchUserCredentials() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");
            String query = "SELECT username, Pass, user_role, LastName, FirstName, user_rating FROM seller_details";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Iterate through the result set (all the users) and add user information to the map
                while (resultSet.next()) {
                    String username = resultSet.getString("username").trim();
                    String password = resultSet.getString("Pass").trim(); 
                    String userRole = resultSet.getString("user_role");
                    String lastName = resultSet.getString("LastName").trim();
                    String firstName = resultSet.getString("FirstName").trim();
                    String userRating = resultSet.getString("user_rating");
                    userCredentialsMap.put(username, new MyApplication.UserCredentials(password, userRole, firstName, lastName, userRating));
                }
            }
        }
    }
    
    // the dictionary reference to the user's details/ credentials
    public Map<String, MyApplication.UserCredentials> getUserCredentialsMap() {
        return userCredentialsMap;
    }
    
    // In 'user story: Manage user profile', this will connect to DB
    // and execute a query to delete the user based on the primary key of their username (stored in the dictionary)
    public static boolean deleteAccount(String username) {
        String query = "DELETE FROM seller_details WHERE username = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // similar to delete account, in the ManageProfile class, user will enter new information
    // new information replaces '?' in our query and the user info is updated
    public static boolean updateDetails(String username, String newFirstName, String newLastName, String newUsername, String newPassword) {
        String query = "UPDATE seller_details SET firstname = ?, lastname = ?, username = ?, pass = ? WHERE username = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newUsername);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setString(5, username);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // for user story 2: Manage listings, if we want to add a new property to the site
    // we need to get the relevent info from the class SellerProperties and put it into the table called 'properties'
    public boolean addProperty(String propertyName, String propertyDescription, int numBeds,
        int numBathrooms, int startBid, String endDate) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Property ID: "); // for some reason, i had to put this input here only then it seemed to work???
		int propertyID = scanner.nextInt(); // the rest of the inputs e.g. description, start dat etc are in SelLerProperties
		String query = "INSERT INTO properties (property_ID, property_name, property_description, num_beds, " +
		"num_bathrooms, start_bid, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement preparedStatement = con.prepareStatement(query)) {
			// write all the inputted details to the DB table
			preparedStatement.setInt(1, propertyID);
			preparedStatement.setString(2, propertyName);
			preparedStatement.setString(3, propertyDescription);
			preparedStatement.setInt(4, numBeds);
			preparedStatement.setInt(5, numBathrooms);
			preparedStatement.setInt(6, startBid);
			preparedStatement.setString(7, endDate);
			
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			scanner.close();
		}
	}
    
    // 8/8/8/8//
    // To update a property details as part of the user story of adding listings, Works similarly to ManageProfile's update feature
    public boolean updateProperty(int propertyID, String propertyName, String propertyDescription,
            int numBeds, int numBathrooms, int startBid, String endDate) {
        String selectQuery = "SELECT * FROM properties WHERE property_ID = ?";
        String updateQuery = "UPDATE properties SET property_name = ?, property_description = ?, " +
                "num_beds = ?, num_bathrooms = ?, start_bid = ?, end_date = ? WHERE property_ID = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement selectStatement = con.prepareStatement(selectQuery);
             PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {

            // Check if the property with the specified ID exists
            selectStatement.setInt(1, propertyID);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("The property with ID " + propertyID + " does not exist.");
                    return false;
                }
            }

            // Update the property with new details
            updateStatement.setString(1, propertyName);
            updateStatement.setString(2, propertyDescription);
            updateStatement.setInt(3, numBeds);
            updateStatement.setInt(4, numBathrooms);
            updateStatement.setInt(5, startBid);
            updateStatement.setString(6, endDate);
            updateStatement.setInt(7, propertyID);

            int rowsAffected = updateStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or throw a custom exception in production
            return false;
        }
    }
    public void invokeDisplayProperties() throws ClassNotFoundException, SQLException {
        displayProperties();
    }
   // for the user story: view seller listings - the seller can see what listings they already have available
    // works similarly to ManageProfile which shows all of the users details except here it shows all the properties
    public static void displayProperties() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");

            // SQL query to select all properties from DB table
            String query = "SELECT * FROM properties";

            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Display properties based on the way its structued in the DB
                while (resultSet.next()) {
                    int propertyID = resultSet.getInt("property_ID");
                    String propertyName = resultSet.getString("property_name");
                    String propertyDescription = resultSet.getString("property_description");
                    int numBeds = resultSet.getInt("num_beds");
                    int numBathrooms = resultSet.getInt("num_bathrooms");
                    int startBid = resultSet.getInt("start_bid");
                    String endDate = resultSet.getString("end_date");
                    int highestBid = resultSet.getInt("highest_bid");
                    int numBidders = resultSet.getInt("num_bidders");

                    // Output property information to the console (didn't work by printing in SellerProperties class for some reason)
                    System.out.println("Property ID: " + propertyID);
                    System.out.println("Property Name: " + propertyName);
                    System.out.println("Property Description: " + propertyDescription);
                    System.out.println("Number of Beds: " + numBeds);
                    System.out.println("Number of Bathrooms: " + numBathrooms);
                    System.out.println("Start Bid: " + startBid);
                    System.out.println("End Date: " + endDate);
                    System.out.println("Highest Bid: " + highestBid);
                    System.out.println("Number of Bidders: " + numBidders);
                    System.out.println("----------------------------");
                }
            }
        }
    }

    // for SellerProperties in the user story, works the same as ManageProfile's delete function
    public static boolean deleteProperty(int propertyID) {
        String query = "DELETE FROM properties WHERE property_ID = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            // Set property id as   parameter in the prepared statement (for the query)coz its tge primary key
        	// execute sql delete statement after
        	//return true if it was deleted, false if the DB threw an error
            preparedStatement.setInt(1, propertyID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    // for user story; view sales history, works similarly as SellerProperties but with a diff table in the DB
    public static void displayPropertyHistory() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");
            String query = "SELECT * FROM property_history";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int propertyID = resultSet.getInt("property_ID");
                    String itemName = resultSet.getString("item_name");
                    int finalSalePrice = resultSet.getInt("final_sale_price");
                    String winningBidder = resultSet.getString("winning_bidder");
                    String saleDate = resultSet.getString("sale_date");

                    // Output property history info
                    System.out.println("Property ID: " + propertyID);
                    System.out.println("Item Name: " + itemName);
                    System.out.println("Final Sale Price: " + finalSalePrice);
                    System.out.println("Winning Bidder: " + winningBidder);
                    System.out.println("Sale Date: " + saleDate);
                    System.out.println("----------------------------");
                }
            }
        }
    }
    
    // one of the subtasks is generating a sales report for user story: view sales histry
    // i;ve decided to do that as a cvs rather than text file for better readability
    // get all the properties from property_history table in the db
    // write it to a CSV file (create one if it already exists)
    public void writePropertiesToCSV() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");
            String query = "SELECT * FROM property_history";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Create csv writer to write data to file
                try (CSVWriter csvWriter = new CSVWriter(new FileWriter("sales_report.csv"))) {
                    // create da headers
                    String[] headers = {"Property ID", "Item Name", "Final Sale Price", "Winning Bidder", "Sale Date"};
                    csvWriter.writeNext(headers);

                    // Write each property to csv file
                    while (resultSet.next()) {
                        String[] propertyData = {
                                String.valueOf(resultSet.getInt("property_ID")),
                                resultSet.getString("item_name"),
                                String.valueOf(resultSet.getInt("final_sale_price")),
                                resultSet.getString("winning_bidder"),
                                resultSet.getString("sale_date")
                        };
                        csvWriter.writeNext(propertyData);
                    }
                }
            }
        }
    }
    
    // with the view seller history, we can sort the properties by sale price - high to low use sql query
    public void orderByFinalSalePrice() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println(""); // ignore, just for formatting
            String query = "SELECT * FROM property_history ORDER BY final_sale_price DESC";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
            	// again using a while loop to display each propery and move to the next
                while (resultSet.next()) {
                    int propertyID = resultSet.getInt("property_ID");
                    String itemName = resultSet.getString("item_name");
                    int finalSalePrice = resultSet.getInt("final_sale_price");
                    String winningBidder = resultSet.getString("winning_bidder");
                    String saleDate = resultSet.getString("sale_date");
                    System.out.println("Property ID: " + propertyID);
                    System.out.println("Item Name: " + itemName);
                    System.out.println("Final Sale Price: " + finalSalePrice);
                    System.out.println("Winning Bidder: " + winningBidder);
                    System.out.println("Sale Date: " + saleDate);
                    System.out.println("----------------------------");
                }
            }
        }
    }
    
    // same as above but this time for low to high prices
    public void orderByFinalSalePriceHigh() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");
            String query = "SELECT * FROM property_history ORDER BY final_sale_price ASC";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int propertyID = resultSet.getInt("property_ID");
                    String itemName = resultSet.getString("item_name");
                    int finalSalePrice = resultSet.getInt("final_sale_price");
                    String winningBidder = resultSet.getString("winning_bidder");
                    String saleDate = resultSet.getString("sale_date");

                    System.out.println("Property ID: " + propertyID);
                    System.out.println("Item Name: " + itemName);
                    System.out.println("Final Sale Price: " + finalSalePrice);
                    System.out.println("Winning Bidder: " + winningBidder);
                    System.out.println("Sale Date: " + saleDate);
                    System.out.println("----------------------------");
                }
            }
        }
    }
    
    // similar as the previous two for sales histry, we can filter by finding specific date of sale using sql query
    public void filterPropertiesByDate(String date) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("");
            String query = "SELECT * FROM property_history WHERE sale_date = ?";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, date);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int propertyID = resultSet.getInt("property_ID");
                        String itemName = resultSet.getString("item_name");
                        int finalSalePrice = resultSet.getInt("final_sale_price");
                        String winningBidder = resultSet.getString("winning_bidder");
                        String saleDate = resultSet.getString("sale_date");

                        System.out.println("Property ID: " + propertyID);
                        System.out.println("Item Name: " + itemName);
                        System.out.println("Final Sale Price: " + finalSalePrice);
                        System.out.println("Winning Bidder: " + winningBidder);
                        System.out.println("Sale Date: " + saleDate);
                        System.out.println("----------------------------");
                    }
                }
            }
        }
    }
    
    // this is a subtask of viewing sales history by getting the final bid price of each property sale
    // we then add the numbers together to get total income from all the sales
    // if there is an error or no profit made, return 0
    public int getTotalFinalBidAmount() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // SQL query does the calculation rather than JAVA
            String query = "SELECT SUM(final_sale_price) AS totalAmount FROM property_history";

            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("totalAmount");
                }
            }
        }
        return 0;
    }

    // this is for the rating user story
    // linked to the sales history, we get the username of all the buyers
    // then we can choose from those usernames and rate them (see below)
    public void displayBuyerUsers() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hkh6", "root", "toor")) {
            System.out.println("");
            String query = "SELECT * FROM seller_details WHERE user_role = 'buyer'";

            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    System.out.println("Username: " + username);
                    System.out.println("First Name: " + firstName);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("----------------------------");
                }
            }
        }
    }
    
    // since we only want to rate a user if they are in the user DB and have purchased a property
    // we get the seller_details (should be user_details tbh) and the property_history (so all the sold properties)
    // we combine them into a big table and then create a new list of data
    // if the username we want to rate is not in the list, we will not be able to rate them
    public boolean isUsernameValid(String username) throws SQLException {
        String query = "SELECT 1 FROM seller_details WHERE username = ? AND user_role = 'seller' " +
                       "UNION " +
                       "SELECT 1 FROM property_history WHERE winning_bidder = ?";
        
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If result set has >= 1 row, username is valid and can be rated
                return resultSet.next();
            }
        }
    }
    // similar to other methods, this will display the profile of the user you have selected to be rated
    public void displaySellerDetailsByUsername(String username) {
        String query = "SELECT * FROM seller_details WHERE username = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // User found, display details
                    System.out.println("Details for buyer: " + username);
                    System.out.println("----------------------------");
                    System.out.println("Username: " + resultSet.getString("username"));
                    System.out.println("Last Name: " + resultSet.getString("LastName"));
                    System.out.println("First Name: " + resultSet.getString("FirstName"));
                    System.out.println("User Rating: " + resultSet.getFloat("user_rating"));
                } else {
                    System.out.println("It looks like the user you're searching for doesn't exist.");
                    System.out.println("It may be that the account has been deleted or you have misspelled their username.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // once you have given your rating, it will get the selected user's current rating
    // it will add your rating into it and calculate a new average rating
    // the rating is then updated in the DB
    public float calculateAverageRating(String username, float newRating) throws SQLException {
        String query = "SELECT user_rating FROM seller_details WHERE username = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    float existingRating = resultSet.getFloat("user_rating");

                    // find new average rating (no idea how to do this so i just added both together and divided by 2)
                    // Update average rating in DB
                    // return 0 if theres no rating or an error has come from the DB
                    float updatedRating = (existingRating + newRating) / 2;
                    String updateQuery = "UPDATE seller_details SET user_rating = ? WHERE username = ?";
                    try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
                        updateStatement.setFloat(1, updatedRating);
                        updateStatement.setString(2, username);
                        updateStatement.executeUpdate();
                    }

                    return updatedRating;
                }
            }
        }

        return 0;
    }
}


