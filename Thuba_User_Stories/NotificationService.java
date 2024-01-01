import java.util.*;

public class NotificationService {
    private Map<String, List<Notification>> notifications;

    public NotificationService() {
        this.notifications = new HashMap<>();
    }

    public void addNotification(Buyer buyer, Notification notification) {
        getNotifications(buyer.getUsername()).add(notification);
    }

    public void updateNotificationSettings(Buyer buyer, NotificationSettings settings) {
        buyer.setNotificationSettings(settings);
    }

    public void deleteNotifications(Buyer buyer) {
        getNotifications(buyer.getUsername()).clear();
    }

    public List<Notification> viewNotifications(Buyer buyer) {
        return getNotifications(buyer.getUsername());
    }

    private List<Notification> getNotifications(String username) {
        return notifications.computeIfAbsent(username, k -> new ArrayList<>());
    }
}