package auction; 

// User class showing a random user in the auction system
public class User {
    private String username;  // User's username
    private String password;  // User's password
    private String fullName;  // User's full name
    private String email;     // User's email address
    private boolean banned;   // Indicates if the user is banned

    // Constructor with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.fullName = "";   // Default full name is an empty string
        this.email = "";      // Default email is an empty string
        this.banned = false;  // Default status is not banned
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for full name
    public String getFullName() {
        return fullName;
    }

    // Setter for full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for banned status
    public boolean isBanned() {
        return banned;
    }

    // Setter for banned status
    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    // shows a welcome message for the user
    public void displayWelcomeMessage() {
        System.out.println("Welcome, user " + username + "!");
    }
}
