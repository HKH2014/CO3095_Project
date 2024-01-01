package auction; 

import java.util.Scanner;  

public class AdminRegistration {  //  class declaration named AdminRegistration

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Creating a Scanner object for the user input

        System.out.println("Welcome to Admin Registration!");  // welcome message displayed

        // Getting the user input for  the creation of the admin account 
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Creates an admin account by using the static method from the Admin class
        Admin admin = Admin.createAdminAccount(username, password, email, password);

        // imitation which allows the storing of admin information in a database 
        boolean accountCreated = storeAdminInDatabase(admin);

        if (accountCreated) {
            admin.displayWelcomeMessage();  // Depicts a welcome message for the newly created admin
            // the admin now has supervision permission
            admin.setSupervisionPermission(true);
            System.out.println("Admin now has supervision permission.");
        } else {
            System.out.println("Account creation failed. Please try again.");
        }

        // Admin  bans a user for breaching TOS
        User userToBan = new User("userToBan", "userPassword");  // Creating a user to be banned
        admin.banUser(userToBan, "Breach of terms and conditions");  // Admin banning the user

        scanner.close();  // Closing the scanner to release system resources
    }

    //  Method to allow admin information to be stored in a database
    private static boolean storeAdminInDatabase(Admin admin) {
       
        return true;
    }
}


