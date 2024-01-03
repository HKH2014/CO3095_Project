package CP12;

public class EmailNotification implements Notification {
    @Override
    public void sendNotification(Buyer buyer, String message) {
        // Implement email notification logic
        System.out.println("Sending email to " + buyer.getName() + ": " + message);
    }
}
