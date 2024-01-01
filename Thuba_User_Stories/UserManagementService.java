import java.util.*;
import auction.*;

public class UserManagementService {
    private List<User> users;

    public UserManagementService() {
        this.users = new ArrayList<>();
    }

    public void addUser(Administrator admin, User user1) {
        users.add(user1);
    }

    public void updateUser(Administrator admin, int index, User updatedUser) {
        users.set(index, updatedUser);
    }

    public void deleteUser(Administrator admin, int index) {
        users.remove(index);
    }

    public List<User> viewUsers(Administrator admin) {
        return new ArrayList<>(users);
    }
}
