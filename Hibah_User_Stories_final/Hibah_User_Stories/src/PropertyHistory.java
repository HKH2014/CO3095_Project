import java.io.IOException;
import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;

// this is for user story: view sales histroy 
public class PropertyHistory {
    private DatabaseManager databaseManager;
    private Map<String, String> authenticatedUserMap;

    public PropertyHistory(DatabaseManager databaseManager, Map<String, String> authenticatedUserMap) throws ClassNotFoundException, SQLException {
        this.databaseManager = databaseManager;
        this.authenticatedUserMap = authenticatedUserMap;
        String username = authenticatedUserMap.get("Username");
        System.out.println("Here are all the sales of the seller: " + username);
        System.out.println("----------------------------");

        
        // get the username of tbe current user and introduce the sales
        // display the property history table in the DB in a good and readable way
        // invoke the method in the databasemanager that will retrieve all the winning_bids
        // it will add all the bids together the total 'profit'or amount earnt and display that
        try {
            databaseManager.displayPropertyHistory();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.print("Hello");
        calculateAndDisplayTotalAmount();
        
        
        // user can choose what to do to best see their sales history
        System.out.println("Menu:");
        System.out.println("A. Export sales data as external file");
        System.out.println("B. Filter Properties by date/final price");
        System.out.println("C. Return to Main Menu");
        System.out.println("Enter the letter corresponding to your choice:");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
            	writePropertiesToCSV();
            	mainMenuReturn(scanner);
                break;
            case "B":
            	displayFilterMenu();
//            	mainMenuReturn(scanner);
                break;
            case "C":
            	MyApplication.displayMenu(scanner, authenticatedUserMap);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }



    // once user is finished they can go back to the main menu and select a different feature
     private void mainMenuReturn(Scanner scanner) throws ClassNotFoundException, SQLException {
	        System.out.print("Go back to main menu? Y/N: ");
	        String userInput = scanner.nextLine();
	
	        if ("Y".equalsIgnoreCase(userInput)) {
	        	MyApplication.displayMenu(scanner, authenticatedUserMap);
	        } else {
	            System.out.println("Have a good day!");
     }
        scanner.close();
    }
    
     // as mentioned previously, all the bid amounts are retrieved and added together in databasemanager
     // (got a few errors when i tried to calculate it here)
     // this method applies the method from databasemanager and displays the result in a readable fashion
    private void calculateAndDisplayTotalAmount() {
        try {
            int totalAmount = databaseManager.getTotalFinalBidAmount();
            System.out.println("Total Amount for Final Bid Prices: $" + totalAmount);
            System.out.println("----------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // to generate a report of sales as a csv file
    // database manager also performs the method
    // in a while loop, each property is read from the DB and written into a csv file
    // confirm that sales report has been generated.
    void writePropertiesToCSV() {
        try {
            databaseManager.writePropertiesToCSV();
            System.out.println("Properties exported to CSV sales report successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // if they want to view specific properties, they may do so by selecting which filter to apply
    private void displayFilterMenu() throws ClassNotFoundException, SQLException {
        System.out.println("Filter Choices:");
        System.out.println("A. Filter by Date");
        System.out.println("B. Order by Final Sale Price (Highest to Lowest)");
        System.out.println("C. Order by Final Sale Price (Lowest to Highest)");
        System.out.println("Enter the letter corresponding to your choice:");

        Scanner scanner = new Scanner(System.in);
        String filterChoice = scanner.nextLine().trim().toUpperCase();

        switch (filterChoice) {
            case "A":
                filterByDate();
                break;
            case "B":
//            	System.out.print("hello");
                orderByFinalSalePrice();
                mainMenuReturn(scanner);
                break;
            case "C":
//            	System.out.print("hellooo");
                orderByFinalSalePriceHigher();
                mainMenuReturn(scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }

// if they want a specific date, call  the databasemanager method to filter by date using sql query
    void filterByDate() {
        System.out.print("Enter the date (yyyy-MM-dd): ");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine().trim();
        try {
            databaseManager.filterPropertiesByDate(date);
            mainMenuReturn(scanner);
        } catch (Exception e) {
        	System.out.print("No properties found");
            e.printStackTrace();
        }
    }


    // Call the DatabaseManager to order properties by final sale price using sql query (see databasemanager for more details)
    // order sales prices from high to low
    public void orderByFinalSalePrice() {
        try {
            databaseManager.orderByFinalSalePrice();
//            mainMenuReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // Call the DatabaseManager to order properties by final sale price using sql query (see databasemanager for more details)
    // works pretty much the exact same as the above method just low to high this time
    public void orderByFinalSalePriceHigher() {
        // Call the DatabaseManager to order properties by final sale price
        try {
            databaseManager.orderByFinalSalePriceHigh();
//            mainMenuReturn(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
