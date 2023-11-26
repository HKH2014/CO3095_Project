import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class FilterProperties {
    private static List<Map<String, Object>> originalPropertiesList;
    private static List<Map<String, Object>> propertiesList;
    private static Stack<List<Map<String, Object>>> filterStack = new Stack<>();

    public static void main(String[] args) {
        String csvFile = "src//properties.csv"; // csv file path

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // This code allows skipping BOM characters if any of them are present, at the header (top row of the CSV file)
            String header = br.readLine();
            if (header.startsWith("\uFEFF")) {
                header = header.substring(1);
            }

            // Code to store each csv file row in a list
            originalPropertiesList = new ArrayList<>();
            propertiesList = new ArrayList<>();

            // Code to read and process each row in the csv file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Create Map for the current row in csv file
                Map<String, Object> rowMap = new HashMap<>();

                // Columns in the order of id, name, description, beds, bathrooms, postcode which corresponds to later output in the console
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String description = values[2];
                int beds = Integer.parseInt(values[3]);
                int bathrooms = Integer.parseInt(values[4]);
                String postcode = values[5];

                // Add the current property to the original list
                rowMap.put("ID", id);
                rowMap.put("Name", name);
                rowMap.put("Description", description);
                rowMap.put("Beds", beds);
                rowMap.put("Bathrooms", bathrooms);
                rowMap.put("Postcode", postcode);
                originalPropertiesList.add(rowMap);

                // Add current property to the working list
                propertiesList.add(rowMap);
            }

            // Code to get input choice from user
            // Add user input to view options, delete filters, undo filters, update properties based on the filters, check/view submitted choices and properties matching a filter
            Scanner scanner = new Scanner(System.in);
            char choice;
            do {
                System.out.println("Select Filter here:");
                System.out.println("A. Filter by Bathrooms");
                System.out.println("B. Filter by Beds");
                System.out.println("C. Find Specific Address");
                System.out.println("D. Delete All Filters");
                System.out.println("E. Undo Latest Filter");
                System.out.println("F. Exit");
                System.out.print("Enter your choice (A/B/C/D/E/F): ");
                choice = scanner.next().charAt(0);

                switch (choice) {
                    case 'A':
                    case 'a':
                        System.out.print("Enter the number of bathrooms: ");
                        int numBathrooms = scanner.nextInt();
                        applyFilter(filterByBathrooms(numBathrooms, propertiesList));
                        break;
                    case 'B':
                    case 'b':
                        System.out.print("Enter the number of beds: ");
                        int numBeds = scanner.nextInt();
                        applyFilter(filterByBeds(numBeds, propertiesList));
                        break;
                    case 'C':
                    case 'c':
                        System.out.print("Enter the address to find: ");
                        String address = scanner.next();
                        applyFilter(findSpecificAddress(address, propertiesList));
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
                        System.out.println("Leaving Propety Filter Menu!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter A, B, C, D, E, or F.");
                }
            } while (choice != 'F' && choice != 'f');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setter for originalPropertiesList
    public static void setOriginalPropertiesList(List<Map<String, Object>> originalPropertiesList) {
        FilterProperties.originalPropertiesList = originalPropertiesList;
    }

    // Setter for propertiesList
    public static void setPropertiesList(List<Map<String, Object>> propertiesList) {
        FilterProperties.propertiesList = propertiesList;
    }

    // Getter for originalPropertiesList
    public static List<Map<String, Object>> getOriginalPropertiesList() {
        return originalPropertiesList;
    }

    // Getter for propertiesList
    public static List<Map<String, Object>> getPropertiesList() {
        return propertiesList;
    }  
    
    // Allows user to check the filter they have chosen 
    // Loops through properties and checks which matches the user added/chosen the filter
   public static List<Map<String, Object>> filterByBathrooms(int numBathrooms, List<Map<String, Object>> properties) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("Filtering by " + numBathrooms + " bathrooms:");

        // Iterate through the list of bathrooms and print only those with the specified number of bathrooms (if the user chose A)
        for (Map<String, Object> property : properties) {
            int bathrooms = (int) property.get("Bathrooms");
            if (bathrooms == numBathrooms) {
                printPropertyDetails(property);
                filteredList.add(property);
            }
        }
        return filteredList;
    }
    
    // Allows user to check the filter they have chosen 
    // Loops through properties and checks which matches the user added/chosen the filter
   public  static List<Map<String, Object>> filterByBeds(int numBeds, List<Map<String, Object>> properties) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("\n Filtering by " + numBeds + " beds:");

        // Iterate through the list of beds and print only those with the specified number of beds (if the user chose B)
        for (Map<String, Object> property : properties) {
            int beds = (int) property.get("Beds");
            if (beds == numBeds) {
                printPropertyDetails(property);
                filteredList.add(property);
            }
        }
        return filteredList;
    }
    
    // Allows user to check the filter they have chosen 
    // Loops through properties and checks which matches the user added/chosen the filter
    public static List<Map<String, Object>> findSpecificAddress(String address, List<Map<String, Object>> properties) {
        List<Map<String, Object>> filteredList = new ArrayList<>();
        System.out.println("Finding property with address: " + address);

        // Iterate through the list of postcodes and print only those with the specified number of postcode (if the user chose C)
        for (Map<String, Object> property : properties) {
            String propertyAddress = (String) property.get("Postcode");
            if (propertyAddress.equalsIgnoreCase(address)) {
                printPropertyDetails(property);
                filteredList.add(property);
            }
        }
        return filteredList;
    }

    // Allows user to check if all filters have been cleared
    // Removes all filters, shows all the properties as no filter is applied currently
    public static List<Map<String, Object>> clearAllFilters() {
        System.out.println("Clearing all filters:");
        for (Map<String, Object> property : propertiesList) {
            printPropertyDetails(property);
        }
        return new ArrayList<>(originalPropertiesList);
    }

   public  static void applyFilter(List<Map<String, Object>> filteredList) {
        filterStack.push(propertiesList);
        propertiesList = new ArrayList<>(filteredList);
    }
    //  Allows user to check if the latest filter have been cleared
    // Removes latest filter, shows properties with only those with previous filters applied (other than latest filter)
    public static void undoLatestFilter() {
        if (!filterStack.isEmpty()) {
            propertiesList = filterStack.pop();
            System.out.println("Undo latest filter. Displaying the previous state:");
            for (Map<String, Object> property : propertiesList) {
                printPropertyDetails(property);
            }
        } else {
            System.out.println("Cannot undo. No filters applied yet.");
        }
    }
    
    // Allows user to view and check the properties, showing only those that the filter is apply to
    // Here then the user can choose to update their options: view all properties by choosing delete filter option, exit the application or delete latest filter
    private static void printPropertyDetails(Map<String, Object> property) {
        System.out.println("---------------------");
        System.out.println("Description: " + property.get("Description"));
        System.out.println("Bathrooms: " + property.get("Bathrooms"));
        System.out.println("Beds: " + property.get("Beds"));
        System.out.println("Postcode: " + property.get("Postcode"));
        System.out.println("Type: " + property.get("Name"));
        System.out.println("---------------------");
    }
}

