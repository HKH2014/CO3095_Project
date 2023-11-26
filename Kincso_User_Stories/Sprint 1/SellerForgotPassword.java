import java.io.*;
import java.util.Scanner;

public class SellerForgotPassword {
	static final String CSV_FILE_PATH = "src//updatedPassword.csv";

	static final String BACKUP_FILE_PATH = "src//backupPassword.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        // Automatic output to the user to check if they are in the correct page 
        System.out.println("Welcome to the Seller Password Management System");

        
        // Code to get input from the user to choose which operation they want to perform 
        // Add user input to view options, check if account exists, delete/undo changes, update password by adding new password to the tempPassword temp file then updating the updatePassword file with new details when user confirms password change.
        while (!exitRequested) {
        	System.out.println("\nSelect an option:");
            System.out.println("1. Check if Account exists before requesting new password");
            System.out.println("2. Update Password");
            System.out.println("3. Undo Changes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    checkPassword(scanner);
                    break;
                case 2:
                    updatePassword(scanner);
                    break;
                case 3:
                    undoChanges();
                    break;
                case 4:
                    exitRequested = true;
                    System.out.println("Exiting the Seller Forgot Password page!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

        scanner.close();
}
    // Allows user to check if they have an account with the username and email input, if the combination is incorrect and error message outputs telling the user this
    static void checkPassword(Scanner scanner) {
        System.out.print("Enter your username: ");
        String usernameToReset = scanner.nextLine();

        System.out.print("Enter your email address: ");
        String emailToReset = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));

            // Read the header to find username and email fields in csv file
            String header = reader.readLine();

            String line;
            boolean accountFound = false;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0].trim();
                String password = fields[1].trim();
                String email = fields[2].trim();

                if (username.equals(usernameToReset) && email.equals(emailToReset)) {
                    accountFound = true;

                    System.out.println("Account Found!");
                    break;
                }
            }

            if (!accountFound) {
                System.out.println("Account not found. Please check your username and email address.");
            }

            reader.close();

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
    // Allows user to update their password if the username and email combination is correct.
    // Allows user to add a new password, check if its different to the old password by comparing updatedPassword csv and tempPassword csv then update the password 
    static boolean updatePassword(Scanner scanner) {
        System.out.print("Enter your username: ");
        String usernameToUpdate = scanner.nextLine();

        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));

            // Read the header
            String header = reader.readLine();

            String line;
            boolean accountFound = false;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0].trim();
                String password = fields[1].trim();
                String email = fields[2].trim();

                if (username.equals(usernameToUpdate) && password.equals(currentPassword)) {
                    accountFound = true;

                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine();

                    // Update the password in the updatedPassword csv file and allow user to check if this update has been performed correctly by outputting a success or fail message 
                    if (updatePassword(usernameToUpdate, email, newPassword)) {
                        System.out.println("Password updated successfully!");
                    } else {
                        System.out.println("Password update failed. User not found or invalid current password.");
                    }
                    break;
                }
            }

            if (!accountFound) {
                System.out.println("Invalid username or current password. Password update failed.");
            }

            reader.close();

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return false; // Password update failed
    }

    private static boolean updatePassword(String username, String email, String newPassword) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            FileWriter writer = new FileWriter("tempPassword.csv", true);

            // Read the header
            String header = reader.readLine();
            writer.write(header + "\n");

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String currentUsername = fields[0].trim();
                String currentPassword = fields[1].trim();
                String currentEmail = fields[2].trim();

                if (currentUsername.equals(username) && currentEmail.equals(email)) {
                    // Update the password
                    writer.write(username + "," + newPassword + "," + email + "\n");
                    writer.close();
                    reader.close();

                    // Rename the temporary file to the original file
                    new java.io.File("tempPassword.csv").renameTo(new java.io.File(CSV_FILE_PATH));

                    return true; // Password updated successfully
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();

        } catch (IOException e) {
            System.err.println("Error updating password: " + e.getMessage());
        }

        return false; // Password update failed
    }

    // Allow user to undo changes while the new password is saved in the tempPassword csv
    // Compare the two csv files and revert to original password if user confirms this
    static void undoChanges() {
        try {
            File originalFile = new File(CSV_FILE_PATH);
            File backupFile = new File(BACKUP_FILE_PATH);

            // Check if a backup file exists
            if (backupFile.exists()) {
            	
                // Allow user to check and confirm if they want to revert the changes
                // Restore the original file from the backup
                copyFile(backupFile, originalFile);
                System.out.println("Changes undone. Restored to the previous state.");
            } else {
                System.out.println("No changes to undo. Backup file not found.");
            }
        } catch (IOException e) {
            System.err.println("Error undoing changes: " + e.getMessage());
        }
    }
    // Delete the new password from the tempPassword csv file, and only keep the original password if user confirmed undo (update to old password) above
    static void copyFile(File source, File dest) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        }
    }
}
