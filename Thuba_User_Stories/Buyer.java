package auction;  // 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buyer {  // class buyer declaration

    // Private instance variables 
    private String username;
    private String email;
    private String password;
    private List<Notification> notifications;
    private List<Property> favorites;

    // this is a Constructor for creating a Buyer object with parameters specified 
    public Buyer(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.notifications = new ArrayList<>();  // this Initialises the notifications list
    }

    // Method to add a property to the favourites list
    public void addToFavorites(Property property) {
        favorites.add(property);
        System.out.println("Property '" + property.getPropertyName() + "' added to favorites.");
    }

    // shows favourite properties
    public void displayFavoriteProperties() {
        // shows favorite properties if available, otherwise a message indicating there are none 
    }

    // Getters and setters for private variables

    // Display a welcome message for the buyer
    public void displayWelcomeMessage() {
        System.out.println("Welcome, buyer " + username + "! You can now bid on houses.");
    }

    // Method to reset buyer's password
    public void resetPassword(Scanner scanner) {
        // Prompting the user for current and new password, performing reset if current password is correct
    }

    // Method to receive and show a notification
    public void receiveNotification(Notification notification) {
        notifications.add(notification);
        System.out.println("Notification received: " + notification.getMessage());
    }

    // Getter for notifications list
    public List<Notification> getNotifications() {
        return notifications;
    }

    private NotificationSettings notificationSettings;

    public void setNotificationSettings(NotificationSettings settings) {
        this.notificationSettings = settings;
    }

    public void addSavedSearch(SavedSearchesService service, SavedSearch search) {
        service.addSavedSearch(this, search);
    }

    public void updateSavedSearch(SavedSearchesService service, int index, SavedSearch updatedSearch) {
        service.updateSavedSearch(this, index, updatedSearch);
    }

    public void deleteSavedSearch(SavedSearchesService service, int index) {
        service.deleteSavedSearch(this, index);
    }

    public List<SavedSearch> viewSavedSearches(SavedSearchesService service) {
        return service.viewSavedSearches(this);
    }

    public void addBid(BidHistoryService service, Bid bid) {
        service.addBid(this, bid);
    }

    public void updateBid(BidHistoryService service, int index, Bid updatedBid) {
        service.updateBid(this, index, updatedBid);
    }

    public void deleteBid(BidHistoryService service, int index) {
        service.deleteBid(this, index);
    }

    public List<Bid> viewBidHistory(BidHistoryService service) {
        return service.viewBidHistory(this);
    }
}

    // Example usage in the main method
    public static void main(String[] args) {
        // Creating a Buyer object and displaying a welcome message
        Buyer buyer = new Buyer("buyer1", "buyer@example.com", "password");
        buyer.displayWelcomeMessage();

        // notifcation creation 
        Notification notification = new Notification("New house available for bidding!");

        // Receiving and showing the notification
        buyer.receiveNotification(notification);

        // showing all current notifications
        List<Notification> allNotifications = buyer.getNotifications();
        System.out.println("All Notifications:");
        for (Notification n : allNotifications) {
            System.out.println(n.getMessage());
        }
    }
}
