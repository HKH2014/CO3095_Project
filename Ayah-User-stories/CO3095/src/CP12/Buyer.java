package CP12;

public class Buyer {
    private String name;
    private Notification notification;

    public Buyer(String name, Notification notificationService) {
        this.name = name;
        this.notification = notificationService;
    }

    public String getName() {
        return name;
    }

    public void receiveNotification(String message) {
        notification.sendNotification(this, message);
    }
}
