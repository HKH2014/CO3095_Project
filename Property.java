package auction;


public class Property {
    private String propertyName;
    private String propertyAddress;

    // Constructors
    public Property() {
        // Default constructor
    }

    public Property(String propertyName, String propertyAddress) {
        this.propertyName = propertyName;
        this.propertyAddress = propertyAddress;
    }

    // Getters and setters
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}

