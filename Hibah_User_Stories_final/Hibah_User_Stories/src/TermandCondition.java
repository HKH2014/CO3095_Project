import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

// this is for user story: view terms and conditions
public class TermandCondition {
	// terms and conditions is saved as a text file to be read and manipulated if you have admin rights (admin role is checked in the user credentials dictioanry)
    public static void main(String[] args, Map<String, String> authenticatedUserMap) throws ClassNotFoundException, SQLException {
        String filePath = "src//TermsandConditions.txt";

        // create a new file object and check if file exists create file reader to actually read text file, using buffered reader to read line alltogetehr
        // reads every line from the file and displays it in da console
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
// now if you are an admin (user roles from db) only then are you given the option to edit terms and conditions if file can't be read - exception handling since i encountered a problem earlier 
            String userRole = authenticatedUserMap.get("UserRole");
            if ("admin".equalsIgnoreCase(userRole)) {
            	TandT(filePath);
            }else {
                System.out.println("You do not have permission to edit the file, logging out now...");
                MyApplication.main(null);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    // this is the method that lets a user make changes to the terms and conditins when invoked
    private static void TandT(String filePath) throws ClassNotFoundException, SQLException {
    	System.out.print("\n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to modify the terms and conditions? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if ("Y".equals(choice)) { //  if they want to make changes then they can
            String fileContent = readFile(filePath);
            String modifiedContent = editContent(fileContent); // save modifications to termcondition file
            try {
                writeFile(filePath, modifiedContent);
                System.out.println("File has been successfully updated.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to update the file.");
//                MyApplication.main(null);
            }
        } else if ("N".equals(choice)) {
            System.out.println("No worries. Logging out now...");
            
        } else {
            System.out.println("Invalid choice. Please enter Y or N.");
        }

        System.out.print("Have a good day!");
//        System.exit(0);
//        MyApplication.main(null);
        scanner.close();
    }
    
    // method to read file - obtained from YouTube video: https://www.youtube.com/watch?v=YNvro8ZEGQk
    static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read the file.");
        }

        return content.toString();
    }
    
    // some code came from >> https://www.youtube.com/watch?v=U28eKSLI7pw i did modify much of it though
    private static String editContent(String currentContent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your modifications:");

        StringBuilder modifiedContent = new StringBuilder(currentContent);
        String line;

        String[] lines = modifiedContent.toString().split("\n");

        while (true) {
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            // Replace lines/content from the termcondition text fille with what the user put in
         // multiple chances to edit lines from the termsconditions file and keep editiding
            System.out.print("Enter the line number to replace: ");
            int lineNumber = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (lineNumber > 0 && lineNumber <= lines.length) {
                lines[lineNumber - 1] = line;
            } else {
                System.out.println("Invalid line number. No modifications made lol.");
            }
            System.out.print("Do you want to continue editing? (Y/N): ");
            String continueEditing = scanner.nextLine().trim().toUpperCase();
            if (!"Y".equals(continueEditing)) {
                break;
            }
        }

        return String.join("\n", lines);
    }

// writing to the text file
    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}

