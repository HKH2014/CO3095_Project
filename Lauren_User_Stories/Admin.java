package auction;  // shows this class belongs to the auction package

import java.time.LocalDateTime;  //  LocalDateTime class is imported from the java.time package

public class Admin extends User {  // states the  Admin class that extends/inherits from the User class

    // shows private instance variables
    private boolean hasSupervisionPermission;  // shows whether the admin has supervision permission
    private String fullName;  // Stores the Admin's full name 
    private String email;  // Stores the Admin's email address 

    // Constructor for the creation of an Admin object with parameters specified
    public Admin(String username, String password, boolean hasSupervisionPermission, String fullName, String email) {
        super(username, password);  // Calling the constructor of the superclass (User) with username and password
        this.hasSupervisionPermission = hasSupervisionPermission;  // supervisor permission set 
        this.fullName = fullName;  // full name set
        this.email = email;  // email set 
    }

    // Getter method for hasSupervisionPermission shown
    public boolean hasSupervisionPermission() {
        return hasSupervisionPermission;
    }

    // Setter method for hasSupervisionPermission shown 
    public void setSupervisionPermission(boolean hasSupervisionPermission) {
        this.hasSupervisionPermission = hasSupervisionPermission;
    }

    // Getter method for fullName
    public String getFullName() {
        return fullName;
    }

    // Setter method for fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Setter method for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Overriding a method from the superclass (User)
    @Override
    public void displayWelcomeMessage() {
        System.out.println("Welcome, admin " + getUsername() + "!");
    }

    // A Method to ban a user with a reason specified 
    public void banUser(User user, String reason) {
        if (hasSupervisionPermission) {  // checks if admin has supervision permsission 
            if (!user.isBanned()) {  // Checks if the user is not banned already
                user.setBanned(true);  // user ban
                System.out.println("User " + user.getUsername() + " has been banned. Reason: " + reason);
                System.out.println("Timestamp: " + LocalDateTime.now());  //  timesamp added for auditing
            } else {
                System.out.println("User " + user.getUsername() + " is already banned.");
            }
        } else {
            System.out.println("Insufficient permissions to ban users.");
        }
    }

    // Method to unban a user
    public void unbanUser(User user) {
        if (hasSupervisionPermission) {  // Checks if admin has supervision permission
            if (user.isBanned()) {  // Checking if the user is banned currently 
                user.setBanned(false);  // Unbanning the user
                System.out.println("User " + user.getUsername() + " has been unbanned.");
            } else {
                System.out.println("User " + user.getUsername() + " is not banned.");
            }
        } else {
            System.out.println("Insufficient permissions to unban users.");
        }
    }

    // Method for user account creation 
    public void createUserAccount(String newUsername, String newPassword, String newFullName, String newEmail, boolean newBannedStatus) {
        if (hasSupervisionPermission) {  // Checking if the admin has supervision permission
            User newUser = new User(newUsername, newPassword);  // Creating a new User object
            newUser.setFullName(newFullName);  // Setting the full name
            newUser.setEmail(newEmail);  // Setting the email address
            newUser.setBanned(newBannedStatus);  // Setting the banned status

            System.out.println("User account created for " + newUsername + ".");
            System.out.println("Timestamp: " + LocalDateTime.now());  // Adding a timestamp for auditing
        } else {
            System.out.println("Insufficient permissions to create user accounts.");
        }
    }

    // A Static method to create an admin account
    public static Admin createAdminAccount(String adminUsername, String adminPassword, String adminFullName, String adminEmail) {
        Admin newAdmin = new Admin(adminUsername, adminPassword, false, adminFullName, adminEmail);  // Creating a new Admin object
        System.out.println("Admin account created for " + adminUsername + ".");
        return newAdmin;  // Returning the created Admin object
    }

	public static Admin getAdminAccount(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Admin getCreatedAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getBannedUsersCount() {
		// TODO Auto-generated method stub
		return null;
	}
}

