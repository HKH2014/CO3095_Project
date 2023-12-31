package auction; 


import java.util.Scanner; 

// BuyerRegistration class for Buyer account registration
public class BuyerRegistration {
    // Main method where the program execution starts
    public static void main(String[] args) {
        // Initialising  Scanner to allow user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to Buyer Registration!");

        // Get user input for buyer account creation
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Create a buyer account
        Buyer buyer = new Buyer(username, email, password);

        // imitation of storing the buyer information in a database 
        boolean accountCreated = storeBuyerInDatabase(buyer);

        // Check if  account creation was successful
        if (accountCreated) {
            // show  a welcome message
            buyer.displayWelcomeMessage();
        } else {
            System.out.println("Account creation failed. Please try again.");
        }

        // Close the Scanner
        scanner.close();
    }

    // Method to imitate storing a Buyer in a database
    private static boolean storeBuyerInDatabase(Buyer buyer) {
       
        return true;
    }
}

