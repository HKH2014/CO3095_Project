import java.io.*;
import java.util.Scanner;

public class CreateSellerAccount {
	
	static final String CSV_FILE_PATH = "src//createAccount.csv";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Automatic output to the user to check if they are in the correct page 
        System.out.println("Welcome to the Seller Create Account");
        
        
        // Code to get input from the user to choose which operation they want to perform 
        // Add user input to view options, add/create new account, delete account, update account, check if all their details are correct before performing add, delete and update functions
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Update Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Allows user to input their choice, check if choice is a valid number, if not output error message 
            boolean operationSuccess;

            switch (choice) {
                case 1:
                    operationSuccess = createAccount(scanner);
                    break;
                case 2:
                    operationSuccess = updateAccount(scanner);
                    break;
                case 3:
                    operationSuccess = deleteAccount(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the page!");
                    scanner.close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    continue;
            }

            if (operationSuccess) {
                System.out.println("Operation completed successfully!");
            } else {
                System.out.println("Operation failed. Please try again.");
            }
        }
    }

    // Code to get input from the user: username, email, password which gets stored in the csv file createAccount so the  user can manage their passwords in the SellerForgotPassword class
    static boolean createAccount(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        
        // Allows user to check if their details are correct, if not then an error message is displayed that input cannot be written in csv file
        try (FileWriter csvWriter = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(csvWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            File file = new File(CSV_FILE_PATH);
            if (file.length() == 0) {
                printWriter.println("Username,Password,Email");
            }

            printWriter.println(username + "," + password + "," + email);
            return true; // Account creation successful message output to user
        
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            return false; // Account creation failed message output to user
        }
    }
    // Update account allows user to check and update their username and/or email if they have input one that they dont like, or if username already exists
    // Update new username and/or email in createAccount csv file
    static boolean updateAccount(Scanner scanner) {
        System.out.print("Enter the username to update: ");
        String usernameToUpdate = scanner.nextLine();

        try {
            File inputFile = new File(CSV_FILE_PATH);
            File tempFile = new File("tempCreateAccount.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // Read the header from the csv createAccount file and allow user to check if their account has been created successfully
            String header = reader.readLine();
            writer.write(header + "\n");

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0].trim();

                if (!username.equals(usernameToUpdate)) {
                    writer.write(line + "\n");
                } else {
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();

                    System.out.println("Enter new email address: ");
                    String newEmail = scanner.nextLine();

                    writer.write(username + "," + newPassword + "," + newEmail + "\n");
                    return true; // Account update successful message output to user
                }
            }

            writer.close();
            reader.close();

            // Rename the temporary file to the original file, new updated details are stored in temp file until user confirms that all details are correct
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            System.err.println("Error updating account: " + e.getMessage());
        }
        // Allows user to check update status and show if there was an error with updating their account 
        return false; // Account update failed output shown to user
    }
    
    // Allows user to delete their account by adding user input to temp file and checking if all fields are correct (to the csv file)
    static boolean deleteAccount(Scanner scanner) {
        System.out.print("Enter the username to delete: ");
        String usernameToDelete = scanner.nextLine();

        try {
            File inputFile = new File(CSV_FILE_PATH);
            File tempFile = new File("tempCreateAccount.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            
            // Read the header
            // Output to allow user to check if their account has been deleted successfully
            String header = reader.readLine();
            writer.write(header + "\n");

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0].trim();

                if (!username.equals(usernameToDelete)) {
                    writer.write(line + "\n");
                } else {
                    System.out.println("Account deleted successfully!");
                    writer.close();
                    reader.close();
                    
                    // Rename the temporary file to the original file
                    tempFile.renameTo(inputFile);
                    return true; // Account deletion successful
                }
            }

            writer.close();
            reader.close();

         // Error message to show user that their account deletion has failed when they check the account status
        } catch (IOException e) {
            System.err.println("Error deleting account: " + e.getMessage());
        }
        
        return false; // Account deletion failed
    }
}
