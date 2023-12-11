import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class FAQ {
    static final int INITIAL_STATE = -1;
    private static Stack<List<Map<String, Object>>> filterStack = new Stack<>();
    private static int currentState = INITIAL_STATE;
    static List<Map<String, Object>> qanda;

    public static void main(String[] args) {
        String csvFile = "src//faq.csv"; // csv file path

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // This code allows skipping BOM characters if any of them are present, at the header (top row of the CSV file)
            String header = br.readLine();
            if (header.startsWith("\uFEFF")) {
                header = header.substring(1);
            }
            
            // Code to store each csv file row in a list
            qanda = new ArrayList<>();

            // Code to read and process each row in the csv file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String question = values[1];
                String answer = values[2];
                int topic = Integer.parseInt(values[3]);
                int priority = Integer.parseInt(values[4]);
                int helpfulness = Integer.parseInt(values[5]);

                // Create Map for the current row in csv file
                Map<String, Object> rowMap = new HashMap<>();
                rowMap.put("ID", id);
                rowMap.put("Question", question);
                rowMap.put("Answer", answer);
                rowMap.put("Topic", topic);
                rowMap.put("Priority", priority);
                rowMap.put("Helpfulness", helpfulness);

                qanda.add(rowMap);
            }

            // Code to get input choice from user
            // Add user input to view options, delete filters, undo filters, update faq based on the filters, check/view submitted choices and faq matching a filter
            Scanner scanner = new Scanner(System.in);

            char choice;
            do {
                System.out.println("Select Filter here:");
                System.out.println("A. Filter by Topic");
                System.out.println("B. Filter by Priority");
                System.out.println("C. Filter by Helpfulness");
                System.out.println("D. Delete all filters");
                System.out.println("E. Undo latest filter");
                System.out.println("F. Exit");
                System.out.print("Enter your choice (A/B/C/D/E/F): ");
                choice = scanner.next().charAt(0);

                switch (choice) {
                    case 'A':
                    case 'a':
                        System.out.print("Enter the topic number: ");
                        int numTopic = scanner.nextInt();
                        applyFilter(filterByTopic(numTopic));
                        break;
                    case 'B':
                    case 'b':
                        System.out.print("Enter the priority level: ");
                        int numPriority = scanner.nextInt();
                        applyFilter(filterByPriority(numPriority));
                        break;
                    case 'C':
                    case 'c':
                        System.out.print("Enter the Helpfulness Rating: ");
                        int numHelpfulness = scanner.nextInt();
                        applyFilter(filterByHelpfulness(numHelpfulness));
                        break;
                    case 'D':
                    case 'd':
                        applyFilter(clearAllFilters());
                        break;
                    case 'E':
                    case 'e':
                        undoLatestFilter();
                        break;
                    case 'F':
                    case 'f':
                        System.out.println("Leaving the FAQ page!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter A, B, C, D, E, or F.");
                }
            } while (choice != 'F' && choice != 'f');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Getter method for Qanda used in testing
    public static List<Map<String, Object>> getQanda() {
        return qanda;
    }
    // Getter method for Current state used in testing
    public static int getCurrentState() {
        return currentState;
    }
    // Allows user to check the filter they have chosen 
    // Loops through faq and checks which matches the user added/chosen the filter
   public static List<Map<String, Object>> filterByTopic(int numTopic) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("Filtering by " + numTopic + " topic:");
        for (Map<String, Object> faq : qanda) {
            int topic = (int) faq.get("Topic");
            if (topic == numTopic) {
                printFAQDetails(faq);
                filteredList.add(faq);
            }
        }
        return filteredList;
    }
    
    // Allows user to check the filter they have chosen 
    // Loops through faq and checks which matches the user added/chosen the filter
    public static List<Map<String, Object>> filterByPriority(int numPriority) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("\n Filtering by " + numPriority + " priority:");
        for (Map<String, Object> faq : qanda) {
            int priority = (int) faq.get("Priority");
            if (priority == numPriority) {
                printFAQDetails(faq);
                filteredList.add(faq);
            }
        }
        return filteredList;
    }
    
    // Allows user to check the filter they have chosen 
    // Loops through faq and checks which matches the user added/chosen the filter
   public static List<Map<String, Object>> filterByHelpfulness(int numHelpfulness) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("\n Filtering by " + numHelpfulness + " helpfulness:");
        for (Map<String, Object> faq : qanda) {
            int helpfulness = (int) faq.get("Helpfulness");
            if (helpfulness == numHelpfulness) {
                printFAQDetails(faq);
                filteredList.add(faq);
            }
        }
        return filteredList;
    }
    
    // Allows user to check if all filters have been cleared
    // Removes all filters, shows all the faq as no filter is applied currently
   public static List<Map<String, Object>> clearAllFilters() {
        System.out.println("Clearing all filters:");
        for (Map<String, Object> faq : qanda) {
            printFAQDetails(faq);
        }
        return new ArrayList<>(qanda);
    }

    static void applyFilter(List<Map<String, Object>> filteredList) {
        filterStack.push(qanda);
        currentState++;
        qanda = new ArrayList<>(filteredList);
    }
    
    //  Allows user to check if the latest filter have been cleared
    // Removes latest filter, shows faq with only those with previous filters applied (other than latest filter)
    static void undoLatestFilter() {
        if (currentState > INITIAL_STATE) {
            qanda = filterStack.pop();
            currentState--;
            System.out.println("Undo latest filter. Displaying the previous state:");
            for (Map<String, Object> faq : qanda) {
                printFAQDetails(faq);
            }
        } else {
            System.out.println("Cannot undo. No filters applied yet.");
        }
    }
    // Allows user to view and check the faq, showing only those that the filter is apply to
    // Here then the user can choose to update their options: view all faq by choosing delete filter option, exit the application or delete latest filter
    static void printFAQDetails(Map<String, Object> faq) {
        System.out.println("---------------------");
        System.out.println("Question: " + faq.get("Question"));
        System.out.println("Answer: " + faq.get("Answer"));
        System.out.println("Topic: " + faq.get("Topic"));
        System.out.println("Priority: " + faq.get("Priority"));
        System.out.println("Helpfulness Rating: " + faq.get("Helpfulness"));
        System.out.println("---------------------");
    }
}
