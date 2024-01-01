package auction;

public class AuctionRecord {
    private String item;
    private double price;

    // Constructor
    public AuctionRecord(String item, String string, double price, String string2) {
        this.item = item;
        this.price = price;
    }

    // Getters and setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}