
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

// this is for user story: manage seller's property listings
public class SellerProperties {
    private DatabaseManager databaseManager;
    private Map<String, String> authenticatedUserMap;

    
    public SellerProperties(DatabaseManager databaseManager, Map<String, String> authenticatedUserMap) throws ClassNotFoundException, SQLException {
        this.databaseManager = databaseManager;
        this.authenticatedUserMap = authenticatedUserMap;
        displayProperties(); // just put this as a method beause it refused to work otherwise
    }

    // we start by getting the user role from the dictionary authenticatedMap
    // only seller's can have current property listings, not admins nor buyers
    // as buyers can't access this part of the application, focus on keeping admins out 
    // once seller role has been verified, display the properties table from the DB
    // then have a menu to choose what to do with the properties
    private void displayProperties() throws ClassNotFoundException, SQLException {
        String userRole = authenticatedUserMap.get("UserRole");
        if ("seller".equalsIgnoreCase(userRole)) {
            try {
                databaseManager.displayProperties();
                System.out.println("Seller Menu:");
                System.out.println("A. Delete a property");
                System.out.println("B. Update property details");
                System.out.println("C. Add a new property");
                System.out.println("D. Return to Main Menu");
                System.out.println("Enter the letter corresponding to your choice:");

          
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine().trim().toUpperCase();
                switch (choice) {
                    case "A":
                        // Delete property
                    	deleteProperty();
                        break;
                    case "B":
                        // Update property details
                        updateProperty();
                    	break;
                    case "C":
                        // Add new property
                    	addNewProperty();
                        break;
                    case "D":
                        // Go back to da main menu
                    	MyApplication.displayMenu(scanner, authenticatedUserMap);
                        
                    default:
                        System.out.println("Invalid choice. Please enter A, B, or C.");
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            } // if they have the role of an admin user then reject em
        } else if ("admin".equalsIgnoreCase(userRole)) {
            System.out.println("You do not have any properties as an admin user.");
        } else {
            System.out.println("Invalid user role: " + userRole);
        }
        
        // once they have completed the subtask they wanted to do, ask to go back to main menu
        Scanner scanner = new Scanner(System.in);
        System.out.print("Go back to main menu? Y/N: ");
        String userInput = scanner.nextLine();
        if ("Y".equalsIgnoreCase(userInput)) {
        	MyApplication.displayMenu(scanner, authenticatedUserMap);
        } else {
            System.out.println("Have a good day!");
            System.exit(0);
        }
        scanner.close();
        
    }
    
    // method for adding a property
    // get all the details from the user
    public void addNewProperty() throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter details for the new property:");
        System.out.print("Property Name: ");
        String propertyName = scanner.nextLine().trim();
        System.out.print("Property Description: ");
        String propertyDescription = scanner.nextLine().trim();
        System.out.print("Number of Beds: ");
        int numBeds = scanner.nextInt();
        System.out.print("Number of Bathrooms: ");
        int numBathrooms = scanner.nextInt();
        System.out.print("Start Bid: ");
        int startBid = scanner.nextInt();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = scanner.next().trim();
        // feed all the inputs through the databasemanager which will write it to the table
        // and create a new entry/ property in the DB
        boolean propertyAdded = databaseManager.addProperty(propertyName, propertyDescription,
                numBeds, numBathrooms, startBid, endDate);

        if (propertyAdded) {
            System.out.println("Property added successfully.");
            System.exit(0);
            
        } else {
            System.out.println("Failed to add property - does not exist.");
            System.exit(0);
        }
        
    }
    
    // to update a property, find the ID which is the primary key
    // the prompts to input new data is in the databasemanager (didn't work here for some reason)
    public void updateProperty() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ID of the property you want to update: ");
        int propertyID = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter the new property name: ");
        String propertyName = scanner.nextLine().trim();

        System.out.print("Enter the new property description: ");
        String propertyDescription = scanner.nextLine().trim();

        System.out.print("Enter the new number of beds: ");
        int numBeds = scanner.nextInt();

        System.out.print("Enter the new number of bathrooms: ");
        int numBathrooms = scanner.nextInt();

        System.out.print("Enter the new start bid: ");
        int startBid = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the new end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine().trim();

        boolean success = databaseManager.updateProperty(propertyID, propertyName, propertyDescription,
                numBeds, numBathrooms, startBid, endDate);

        if (success) {
            System.out.println("Property updated successfully!");
        } else {
            System.out.println("Failed to update property - does not exist or an error occurred.");
        }
    }
    
    // to delete property, find the id which is the primary key
    // confirm they want to delete account
    // the databasemanager will run sql query to delete the property using the id
    public void deleteProperty() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter the Property ID to delete: ");
        int propertyIDToDelete = scanner.nextInt();
        System.out.print("Are you sure you want to delete this property? (Y/N): ");
        String deleteConfirmation = scanner.next().trim().toUpperCase();

        if ("Y".equals(deleteConfirmation)) {
            boolean deletionSuccess = DatabaseManager.deleteProperty(propertyIDToDelete);
            if (deletionSuccess) {
                System.out.println("Property deleted successfully.");
            } else {
                System.out.println("Failed to delete property - does not exist");
            }
        } else {
            System.out.println("Deletion canceled.");
        }
    }

}
