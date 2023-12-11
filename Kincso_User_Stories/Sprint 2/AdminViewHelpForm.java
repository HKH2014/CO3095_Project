import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminViewHelpForm {

    static String HELP_FORM_CSV = "src//helpForm.csv";
    private static final String UPDATED_HELP_FORM_CSV = "src//helpFormAdmin.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Help Form Admin Panel");

        boolean exitRequested = false;
        // Code to get input from the admin to choose which operation they want to perform 
        // Add user input to view options, remove/delete form, update status of the form, check if form status is updated
        while (!exitRequested) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View Help Form Entries");
            System.out.println("2. Update Help Form Entry Status");
            System.out.println("3. Delete Help Form Entry");
            System.out.println("4. Admin Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    viewHelpFormEntries();
                    break;
                case 2:
                    updateHelpFormEntryStatus(scanner);
                    break;
                case 3:
                    deleteHelpFormEntry(scanner);
                    break;
                case 4:
                    exitRequested = true;
                    System.out.println("Exiting the Help Form Admin Panel!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

        scanner.close();
    }

    static void viewHelpFormEntries() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(HELP_FORM_CSV));

            String line;
            int entryNumber = 1;
            // Display the information the user entered in the help form to the admin, (line 61 allows spacing to be adjusted for fields so output displays correctly)
            System.out.println("\nHelp Form Entries:");
            System.out.printf("%-5s %-20s %-30s %-30s %-50s %-7s\n", "ID", "Name", "Email", "Question", "Status", "Pending");
            System.out.println("-----------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                String email = fields[1].trim();
                String question = fields[2].trim();
                String status = fields[3].trim();
                String pending = fields.length > 4 ? fields[4].trim() : ""; // Allows the handling of the case where the "Pending" field is missing

                System.out.printf("%-5d %-20s %-30s %-30s %-50s %-7s\n", entryNumber++, name, email, question, status, pending);
            }

            reader.close();
            // Ouput error message for admin if cannot read csv file, or error with entries
        } catch (IOException e) {
            System.err.println("Error reading help form entries: " + e.getMessage());
        }
    }
    
    // Allows admin to update the status of the form (pending, done or deleted) which the user can check in the SubmitHelpForm class to see if their issue has been solved
    static void updateHelpFormEntryStatus(Scanner scanner) {
        System.out.print("Enter the entry number to update status: ");
        int entryNumberToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Output to check and confirm if update has been saved
        System.out.print("Enter the new status (pending/done/deleted): ");
        String newStatus = scanner.nextLine().toLowerCase();
        
        // Output to check if admin entered wrong status, if so error message is displayed
        if (!newStatus.equals("pending") && !newStatus.equals("done") && !newStatus.equals("deleted")) {
            System.out.println("Invalid status. Status should be pending, done, or deleted.");
            return;
        }
        
        // Gets user input to confirm if status is pending
        System.out.print("Is this entry pending? (yes/no): ");
        String isPending = scanner.nextLine().toLowerCase();

        if (!isPending.equals("yes") && !isPending.equals("no")) {
            System.out.println("Invalid input. Please enter yes or no for pending status.");
            return;
        }
        
        // If no errors above with status, add updated status to the csv file
        try {
            List<String> entries = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(HELP_FORM_CSV));

            String line;
            int currentEntryNumber = 1;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                String email = fields[1].trim();
                String question = fields[2].trim();
                String status = fields[3].trim();
                String pending = fields.length > 4 ? fields[4].trim() : ""; // Handle the case where "Pending" field is missing

                if (currentEntryNumber == entryNumberToUpdate) {
                    entries.add(name + "," + email + "," + question + "," + newStatus + "," + (isPending.equals("yes") ? "yes" : "no"));
                } else {
                    entries.add(line);
                }

                currentEntryNumber++;
            }

            reader.close();

            // Write and save the updated entries to a new csv file if all updated fields have been added successfully, if operation failed display error message to user
            FileWriter writer = new FileWriter(UPDATED_HELP_FORM_CSV);
            for (String entry : entries) {
                writer.write(entry + "\n");
            }
            writer.close();

            System.out.println("Help form entry status updated successfully.");
            System.out.println("Updated entries saved to " + UPDATED_HELP_FORM_CSV);

        } catch (IOException e) {
            System.err.println("Error updating help form entry status: " + e.getMessage());
        }
    }

    // Allows admin to delete a submit help form record from the csv file is any detail is considered inappropriate or uninformative
    static void deleteHelpFormEntry(Scanner scanner) {
        System.out.print("Enter the entry number to delete: ");
        int entryNumberToDelete = scanner.nextInt();

        try {
            List<String> entries = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(HELP_FORM_CSV));

            String line;
            int currentEntryNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (currentEntryNumber != entryNumberToDelete) {
                    entries.add(line);
                }
                currentEntryNumber++;
            }

            reader.close();

            // Write the updated entries from helpFormAdmin back to the original csv file helpForm so users can view updates made to their forms
            FileWriter writer = new FileWriter(HELP_FORM_CSV);
            for (String entry : entries) {
                writer.write(entry + "\n");
            }
            writer.close();
            
            // Allows admin to check if the deletion is performed and removed correctly, if not error message shows 
            System.out.println("Help form entry deleted successfully.");

        } catch (IOException e) {
            System.err.println("Error deleting help form entry: " + e.getMessage());
        }
    }
}
