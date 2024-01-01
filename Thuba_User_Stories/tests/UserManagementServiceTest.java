import org.junit.jupiter.api.Test;
import auction.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserManagementServiceTest {
    @Test
    public void testAddUser() {
        UserManagementService service = new UserManagementService();
        Administrator admin = new Administrator("admin", "password");
        User user = new User("john_doe", "john@example.com");
        admin.addUser(service, user);
        assertEquals(1, service.viewUsers(admin).size());
    }

    @Test
    public void testUpdateUser() {
        UserManagementService service = new UserManagementService();
        Administrator admin = new Administrator("admin", "password");
        User user = new User("john_doe", "john@example.com");
        admin.addUser(service, user);
        Seller updatedUser = new Seller("john_doe_updated", "john_updated@example.com");
        admin.updateUser(service, 0, updatedUser);
        assertEquals(updatedUser, service.viewUsers(admin).get(0));
    }

    @Test
    public void testDeleteUser() {
        UserManagementService service = new UserManagementService();
        Administrator admin = new Administrator("admin", "password");
        User user = new User("john_doe", "john@example.com");
        admin.addUser(service, user);
        admin.deleteUser(service, 0);
        assertTrue(service.viewUsers(admin).isEmpty());
    }

    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void testViewUsers() {
        UserManagementService service = new UserManagementService();
        Administrator admin = new Administrator("admin", "password");
        User user1 = new User("john_doe", "john@example.com");
        Seller user2 = new Seller("jane_doe", "jane@example.com");
        admin.addUser(service, user1);
        admin.addUser(service, user2);
        List<User> users = service.viewUsers(admin);
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
}
