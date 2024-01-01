package auction;   

public class Notification {  // class declaration of Notification

    // Private instance variable
    private String message;

    // Constructor to create a Notification object with a specific message 
    public Notification(String message) {
        this.message = message;  // Sets the message for the notification
    }

    // Getter method to regain the message of the notification
    public String getMessage() {
        return message;
    }
}
