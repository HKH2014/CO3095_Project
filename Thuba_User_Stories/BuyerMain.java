package auction; 

import java.util.Scanner;

// BuyerMain class for the Auction System
public class BuyerMain {
    private static boolean accountCreated;

    // Main method to show where the program execution begins
    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the House Bidding System!");

        // Getting user input for buyer account creation
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Creates a Buyer object with the information provided
        Buyer buyer = new Buyer(username, email, password);

        // Attempts to save the buyer in the database
        accountCreated = storeBuyerInDatabase(buyer);

        // Checks to see if account creation was successful
        if (accountCreated) {
            // Display a welcome message
            buyer.displayWelcomeMessage();

            // option to reset password
            System.out.println("1. Reset Password");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // If the user chooses 1 as their option ,they can reset their password
            if (option == 1) {
                buyer.resetPassword(scanner);
            }
        } else {
            System.out.println("Account creation failed. Please try again.");
        }

        // Close the Scanner 
        scanner.close();
    }

    // Method to store a Buyer in the database 
    private static boolean storeBuyerInDatabase(Buyer buyer) {
     
        System.out.println("Account created successfully!");
        return true;
    }
}


