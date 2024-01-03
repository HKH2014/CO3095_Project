package CP14;

public class Property {
    private String name;
    private double currentBid;
    private Bid winningBid;

    public Property(String name) {
        this.name = name;
        this.currentBid = 0.0;
        this.winningBid = null;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }
}

