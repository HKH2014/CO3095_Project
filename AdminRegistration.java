package auction;

import java.util.Scanner;

public class AdminRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Admin Registration!");

        // Get user input for admin account creation
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Create an admin account
        Admin admin = Admin.createAdminAccount(username, password, email, password);

        // Simulate storing the admin information in a database (this is just a simulation)
        boolean accountCreated = storeAdminInDatabase(admin);

        if (accountCreated) {
            admin.displayWelcomeMessage();
            // Admin now has supervision permission
            admin.setSupervisionPermission(true);
            System.out.println("Admin now has supervision permission.");
        } else {
            System.out.println("Account creation failed. Please try again.");
        }

        // Admin bans a user for breaching terms and conditions
        User userToBan = new User("userToBan", "userPassword");
        admin.banUser(userToBan, "Breach of terms and conditions");

        scanner.close();
    }

    private static boolean storeAdminInDatabase(Admin admin) {
        // Simulate storing the admin information in a database
        // You would typically connect to a database and insert the user information
        // Here, we just simulate a successful account creation
        return true;
    }
}

