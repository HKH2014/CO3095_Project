import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

// this is for user story: Manage user details
public class ManageProfile {
	// the DatabaseManager saved all the user (who logged in) credentials to be outputted
    public static void displayUserCredentials(Map<String, String> authenticatedUserMap) throws ClassNotFoundException, SQLException {
        System.out.println("Your User Credentials:");
        System.out.println("----------------------------");

        // the dictionary, going through each row and outputting the key e.g. username and what the username is
        for (Map.Entry<String, String> entry : authenticatedUserMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + ": " + value);
        }
        
        // user has some options of what to do to manage their account
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProfile Menu:");
        System.out.println("A. Delete Account");
        System.out.println("B. Update Details");
        System.out.println("C. Return to Main Menu");
        System.out.print("Enter the letter corresponding to your choice: ");

        // gets rid of white spaces in input yk
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
                // Delete Account method activated
            	// passes through the user's username to find it in the DB and execute an sql query which deletes it from the DB
                String usernameToDelete = authenticatedUserMap.get("Username");
                if (DatabaseManager.deleteAccount(usernameToDelete)) {
                    System.out.println("Account deleted successfully.");
                    System.out.println("You no longer have access to the site");
                    // Clear user dictionary to indicate that the user is no longer authenticated
                    authenticatedUserMap.clear();
                    System.exit(0); //  as they deleted the account, they no longer have access to the site.
                } else {
                    System.out.println("Failed to delete account.");
                }
                break;
            case "B": // get the details to update account details
            	System.out.print("Enter your new first name: ");
                String newFirstName = scanner.nextLine().trim();

                System.out.print("Enter your new last name: ");
                String newLastName = scanner.nextLine().trim();

                System.out.print("Enter your new username: ");
                String newUsername = scanner.nextLine().trim();

                System.out.print("Enter your new password: ");
                String newunhashedPassword = scanner.nextLine().trim();
                String newPassword = hashPassword(newunhashedPassword);

                // Update details in DB
                String currentUsername = authenticatedUserMap.get("Username");
                if (DatabaseManager.updateDetails(currentUsername, newFirstName, newLastName, newUsername, newPassword)) {
                    System.out.println("Details updated successfully.");
                // Update the authenticatedUserMap dictionary with new details
                    authenticatedUserMap.put("FirstName", newFirstName);
                    authenticatedUserMap.put("LastName", newLastName);
                    authenticatedUserMap.put("Username", newUsername);
                    authenticatedUserMap.put("Password", newPassword);
                } else {
                    System.out.println("Failed to update details.");
                }
                break;
            case "C":
//            	System.out.print("Cancelled operation Successful");
            	break;
            	
            default:
                System.out.println("Invalid choice. ");
        }
        
        // once they have done what they need to, ask them to go to main menu
        System.out.print("Go back to main menu? Y/N: ");
        String userInput = scanner.nextLine();

        if ("Y".equalsIgnoreCase(userInput)) {
        	MyApplication.displayMenu(scanner, authenticatedUserMap);
        } else {
            System.out.println("Have a good day!");
        }
        
        scanner.close();
        
    }
    
    // when they change their password, we must ensure its stored in th DB as a hashed password
    // so implement the same techniques as earlier with the login and then saved the hashed password rather than the user input
    private static String hashPassword(String newunhashedPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(newunhashedPassword.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convert byte array to hexadecimal string - also conrtibutes towards password hashing
    private static String bytesToHex(byte[] hash) {
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
}
