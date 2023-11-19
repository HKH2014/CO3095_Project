import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// This is the application, this is the class that we run as an application
public class MyApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    	// this message will appear but to be fancy we'll use a type writer form
    	String message = "CO3095 PROJECT: Auction-House Based Application";
        typewriterPrint(message);
        
//    	System.out.print("Auction-Based House Application");
    	
        /* create a new instance of database manager (which is responsible for all the database connecctions
         *  and data retrieval - specifically it gets user information
        */
        DatabaseManager credentialsManager = new DatabaseManager();
        credentialsManager.fetchUserCredentials();

                   
        // this part of the code gets the user to enter their username and password
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine().trim();
        
        /* as the database has the password hashed using the SHA256 algorithm (security purposes),
         * i am getting the entered Password, hashing the entered password
         * and then comparing the hashed entered Password with the hashed password
         * stored in the DB
         * */ 
         
        String hashedPassword = hashPassword(enteredPassword);

        // over here i am if the entered username and hashed password match
        UserCredentials userCredentials = credentialsManager.getUserCredentialsMap().get(enteredUsername);
        if (userCredentials != null && userCredentials.password.equals(hashedPassword)) {
        	
            // here i check if the account has the correct user role for access
        	// if the account belongs to a seller or admin, only then can they progress in the code
            String userRole = userCredentials.userRole;
            if ("seller".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
                System.out.println("Welcome, " + userCredentials.firstName + "!");

                // once the user has been authorised here we then save all their credentials into a dictionary
                // this will be used for later user stories
                Map<String, String> authenticatedUserMap = new HashMap<>();
                authenticatedUserMap.put("Username", enteredUsername);
                authenticatedUserMap.put("Password", enteredPassword);
                authenticatedUserMap.put("FirstName", userCredentials.firstName);
                authenticatedUserMap.put("LastName", userCredentials.lastName);
                authenticatedUserMap.put("UserRole", userRole);
                authenticatedUserMap.put("UserRating", userCredentials.userRating);

                // now that the user has beenn authorised, they can see the main menu with the user stories
                displayMenu(scanner, authenticatedUserMap);
            } else {
                System.out.println("As a buyer, you do not have the clearence to access this part of the site.");
            }
        } else { // if the account details aren't in the DB, irregardless of user role
            System.out.println("Login failed. Invalid username or password.");
        }
        scanner.close();
    }
    
    
    
    // this algorithm hashes the entered Password thagt the user put in 
    // used for the comparison with the DB hashed password
    // part of code retrieved from: https://www.baeldung.com/sha-256-hashing-java
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convert byte array to hexadecimal string - also conrtibutes towards password hashing
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // this code gives a type writer effect to the initial welcome message
    // splits message into indivisual characters and after every 50 millersecs it prints the next char
    private static void typewriterPrint(String message) {
        try {
            for (char c : message.toCharArray()) {
                System.out.print(c);
                Thread.sleep(50); 
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // this is the main menu upon logging in and being authorised
    // user chooses between the remaining 5 user stories (or exit code) which links to a different class
    public static void displayMenu(Scanner scanner, Map<String, String> authenticatedUserMap) throws ClassNotFoundException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        boolean validChoice = false;

        while (!validChoice) {
        	System.out.print("\n");
            System.out.println("----------------------------");
            System.out.println("Main Menu:");
            System.out.println("----------------------------");
            System.out.println("A. View your Profile");
            System.out.println("B. View Terms and Conditions");
            System.out.println("C. View your properties");
            System.out.println("D. See History of Sales");
            System.out.println("E. Rate your previous buyers");
            System.out.println("F. Exit Application");
            System.out.println("Enter the letter corresponding to your choice:");

            // get dat user input boi
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    // User story 4: View seller Profile
                    ManageProfile.displayUserCredentials(authenticatedUserMap);
                    validChoice = true;
                    break;
                case "B":
                    // User story 5: View Terms and Conditions, modify if ur an admin
                    TermCondition.main(null, authenticatedUserMap);
                    validChoice = true;
                    break;
                case "C":
                    // User story 6: View the seller properties
                    new SellerProperties(databaseManager, authenticatedUserMap);
                    validChoice = true;
                    break;
                case "D":
                    // User story 3: View the history of the seller's sales
                    PropertyHistory propertyHistory = new PropertyHistory(databaseManager, authenticatedUserMap);
                    validChoice = true;
                    break;
                case "E":
                    // User story 2: view ratings for previous buyers
                    BuyerRatings buyerRatings = new BuyerRatings(databaseManager);
                    validChoice = true;
                    break;
                case "F":
                    // exit program/software/applcation 
                    System.out.println("Goodbye for now!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter A, B, C, D, or E.");
            }
        }
    }



    // Class and constructoyr to represent user credentials
    // refers to the user who successfully logged in at beginning
    public static class UserCredentials {
        String password;
        String userRole;
        String firstName;
        String lastName;
        String userRating;

        public UserCredentials(String password, String userRole, String firstName, String lastName, String userRating) {
            this.password = password;
            this.userRole = userRole;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userRating = userRating;
        }
    }
}
