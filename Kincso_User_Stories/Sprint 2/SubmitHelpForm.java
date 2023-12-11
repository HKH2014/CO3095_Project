import java.io.*;
import java.util.Scanner;

public class SubmitHelpForm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Add user input to view options, remove/delete form, update question/details in the form, check/view submitted form
            System.out.println("Menu Options:");
            System.out.println("1. Submit a new form");
            System.out.println("2. Delete a form");
            System.out.println("3. View submitted forms");
            System.out.println("4. Update a form");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1, 2, 3, 4, or 5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    submitNewForm();
                    break;
                case 2:
                    deleteForm();
                    break;
                case 3:
                    viewSubmittedForms();
                    break;
                case 4:
                    updateForm();
                    break;
                case 5:
                    System.out.println("Exiting the Form Management System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }
    // Code to get input from the user: name, email, question which gets submitted and sent to the admin to manage
    static void submitNewForm() {
        String name = getUserInput("Enter your name: ");
        String email = getUserInput("Enter your email: ");
        String question = getUserInput("Enter your question: ");

        // Display the information the user entered 
        System.out.println("\nEntered Information:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Question: " + question);

        // Ask user to confirm if all details are correct, if not take a step back and allow user to edit and re-enter information
        if (confirmWithUser()) {
            // Save the user given details to CSV file after confirmation that it is all correct
            saveToCSV(name, email, question);
            // Message output in console to show users that their details have been successfully submitted 
            System.out.println("Details saved successfully!");
        } else {
            System.out.println("Submission canceled.");
        }
    }
    
    private static String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
     // Allow user to delete their help form by adding the form ID associated with their details
   static void deleteForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Request ID to delete: ");
        int requestIdToDelete = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        // Message output in console to show users that their form deletion have been successful, failed or cancelled 
        if (confirmDelete(requestIdToDelete)) {
            if (removeFromCSV(requestIdToDelete)) {
                System.out.println("Form with Request ID " + requestIdToDelete + " deleted successfully.");
            } else {
                System.out.println("Error deleting form. Please try again.");
            }
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    // Allow user to check and view their submitted form to make sure all details are correct, if not they can update the form or delete it and start again
    static void viewSubmittedForms() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src//helpForm.csv"))) {
            String line;

            System.out.println("\nSubmitted Forms:");
            System.out.println("Request ID | Name | Email | Question");
            System.out.println("-----------------------------------");

            // Skip  header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                System.out.println(fields[0] + " | " + fields[1] + " | " + fields[2] + " | " + fields[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Allows user to update an existing form by searching for it by ID, if the ID exists and checked if associated with the correct details they can check and update the form
    static void updateForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Request ID to update: ");
        int requestIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        try (BufferedReader reader = new BufferedReader(new FileReader("src//helpForm.csv"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src//helpFormTemp.csv", true))) {

            String line;

            // Copy the header line to the temporary file
            writer.write(reader.readLine() + System.getProperty("line.separator"));

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentRequestId = Integer.parseInt(fields[0]);

                if (currentRequestId == requestIdToUpdate) {
                    // If a form is found with the Request ID, allow the user to update the information
                    System.out.println("Current Information:");
                    System.out.println("Name: " + fields[1]);
                    System.out.println("Email: " + fields[2]);
                    System.out.println("Question: " + fields[3]);

                    // Get updated information from user, if unchanged press enter to skip
                    String updatedName = getUserInput("Enter updated name (press Enter to keep current): ");
                    String updatedEmail = getUserInput("Enter updated email (press Enter to keep current): ");
                    String updatedQuestion = getUserInput("Enter updated question (press Enter to keep current): ");

                    // Add/Write updated information to the temporary file, until user confirms it is correct
                    writer.write(currentRequestId + "," +
                            (updatedName.isEmpty() ? fields[1] : updatedName) + "," +
                            (updatedEmail.isEmpty() ? fields[2] : updatedEmail) + "," +
                            (updatedQuestion.isEmpty() ? fields[3] : updatedQuestion) +
                            System.getProperty("line.separator"));

                    System.out.println("Form updated successfully.");
                } else {
                    // Add/Copy the line to the temp file so format does not change
                    writer.write(line + System.getProperty("line.separator"));
                }
            }

            // Rename the temporary file helpFormTemp to the original file helpForm, if there is an error display error message to user 
            File inputFile = new File("src//helpForm.csv");
            File tempFile = new File("src//helpFormTemp.csv");

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error renaming temporary file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Allows user to delete their help form and check if it has been successful
    private static boolean removeFromCSV(int requestIdToDelete) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src//helpForm.csv"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src//helpFormTemp.csv"))) {

            String line;

            // Copy header line to the temporary file
            writer.write(reader.readLine() + System.getProperty("line.separator"));

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int currentRequestId = Integer.parseInt(fields[0]);

                if (currentRequestId != requestIdToDelete) {
                    // Copy the line to the temp file
                    writer.write(line + System.getProperty("line.separator"));
                }
            }

            // Rename the temporary file helpFormTemp to the original file helpForm, if unsuccessful display error message to user 
            File inputFile = new File("src//helpForm.csv");
            File tempFile = new File("src//helpFormTemp.csv");

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error renaming temporary file.");
                return false;
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Allow the user to check and confirm deletion of their form
    private static boolean confirmDelete(int requestIdToDelete) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to delete the form with Request ID " + requestIdToDelete + "? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }

    private static String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
    // Ask user to check if all information is correct before performing action 
    private static boolean confirmWithUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is this information correct? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }
    
    // Allow user to save their updated/modified details in the form for the admin to check
    private static void saveToCSV(String name, String email, String question) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src//helpForm.csv", true))) {
            int requestId = generateRequestId();
            writer.println(requestId + "," + name + "," + email + "," + question);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate request ID which the user needs to perform action on the form, so other people will not be able to add/delete/update someone else's form, Request ID is unique and is only given to the person adding a new form to store and use if needed
    private static int generateRequestId() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src//helpForm.csv"))) {
            reader.readLine();

            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                String[] fields = lastLine.split(",");
                return Integer.parseInt(fields[0]) + 1;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return requestId++;
    }

    private static int requestId = 1;
}
