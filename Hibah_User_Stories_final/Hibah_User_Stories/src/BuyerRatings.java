import java.sql.SQLException;
import java.util.Scanner;

// this is for the user story: rating a buyer as a seller/ admin
public class BuyerRatings {
    private DatabaseManager databaseManager;

    public BuyerRatings(DatabaseManager databaseManager) throws ClassNotFoundException, SQLException {
        this.databaseManager = databaseManager;
//        databaseManager.displayBuyerUsers();

     // Display everything in property table - this is so they ca see all the buyers of their properties
        // uses same method from PropertyHistory to save some time
        // then ask the user to find find the buyer by the username
        // as mentioned in Databasemanager, we need to actually make sure this is a valid buyer and user
        // so we check the user table called 'seller_details' and the property hisory table called 'property_history'
        // checks if the user is in both those tables using sql query from databaseManager
        // if user name is in both tables, the buyer's profile is retrieved ad outputted
        displayPropertyHistory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a username to view a previous buyer's details: ");
        String usernameToSearch = scanner.nextLine().trim();
        if (databaseManager.isUsernameValid(usernameToSearch)) {
        	System.out.print("Record for " + usernameToSearch + " found - " );
            databaseManager.displaySellerDetailsByUsername(usernameToSearch);
            System.out.println("----------------------------");
            
            // after viewing buyer's profile, user is asked if they want to rate user
            // if yes, get the rating from the user, then their current rating is retrieved from databasemanager
            // databasemanager also has a method that takes the user's inputted rating and the current rating in the DB
            // the method adds the two together and divides by 2 to get the new rating
            // that method finally updates the table with the new rating
            // and then coming back here, the user recieves a confirmation its done
            // the profile of the buyer is shown again but this time it has the buyer's new rating and what you rated it
            System.out.print("Would you like to rate this user? (Y/N): ");
            String rateChoice = scanner.nextLine().trim().toUpperCase();
            if (rateChoice.equals("Y")) {
            	float userRating = getUserRating(scanner);
                float averageRating = databaseManager.calculateAverageRating(usernameToSearch, userRating);
                System.out.print("Rating applied - ");
                System.out.print(" ");
                databaseManager.displaySellerDetailsByUsername(usernameToSearch);
                System.out.println("Your rating: " + userRating);
                System.out.println("----------------------------");
                MyApplication.displayMenu(scanner, null);
                
            } else if (rateChoice.equals("N")) {
                System.out.println("Have a good day!");
                MyApplication.displayMenu(scanner, null);
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("The user with the username '" + usernameToSearch + "' does not exist or has no auction history.");
            MyApplication.displayMenu(scanner, null);
        }
        
    }
    
    
// just using a method that already exists to output all the properties that have been sold 
    //to find the username of a legitimate buyer (see above)
    private void displayPropertyHistory() {
        try {
            System.out.println("Here are all your recent auctions:");
            System.out.println("----------------------------");
            databaseManager.displayPropertyHistory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // getting the new rating from the user. 
    // running checks to ensure that the rating is between 1-5
    // if rating is valid, exit the loop with the valid user rating
    static float getUserRating(Scanner scanner) {
        float userRating = 0;

        while (true) {
            try {
                System.out.print("Enter a rating between 1 and 5: ");
                userRating = Float.parseFloat(scanner.nextLine());

                if (userRating < 1 || userRating > 5) {
                    throw new IllegalArgumentException("Rating must be between 1 and 5.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid float between 1 and 5.");
            }
        }

        return userRating;
    }
}

