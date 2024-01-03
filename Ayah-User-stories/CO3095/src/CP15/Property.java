package CP15;

public class Property {
    private String name;
    private Bid winningBid;

    public Property(String name) {
        this.name = name;
    }

    // Getter and setter for winningBid
    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Other methods as needed
    // ...
}


