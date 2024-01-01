import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.management.Notification;

public class NotificationServiceTest {
    @Test
    public void testAddNotification() {
        NotificationService service = new NotificationService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Notification notification = new Notification("New house available", null, 0); 
        service.addNotification(buyer, notification);
        assertEquals(1, service.viewNotifications(buyer).size());
    }

    @Test
    public void testUpdateNotificationSettings() {
        NotificationService service = new NotificationService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        NotificationSettings settings = new NotificationSettings("Daily", "Email");
        service.updateNotificationSettings(buyer, settings);
        assertEquals("Daily", ((NotificationSettings) buyer.getNotificationSettings()).getFrequency());
        assertEquals("Email", ((NotificationSettings) buyer.getNotificationSettings()).getType());
    }

    @Test
    public void testDeleteNotifications() {
        NotificationService service = new NotificationService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Notification notification = null; 
        service.addNotification(buyer, notification);
        service.deleteNotifications(buyer);
        assertTrue(service.viewNotifications(buyer).isEmpty());
    }

    @Test
    public void testViewNotifications() {
        NotificationService service = new NotificationService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Notification notification1 = new Notification("New house available", null, 0);
        Notification notification2 = new Notification("Price drop on a house you might like", null, 0);
        service.addNotification(buyer, notification1);
        service.addNotification(buyer, notification2);
        List<Notification> notifications = service.viewNotifications(buyer);
        assertEquals(2, notifications.size());
        assertTrue(notifications.contains(notification1));
        assertTrue(notifications.contains(notification2));
    }
}
