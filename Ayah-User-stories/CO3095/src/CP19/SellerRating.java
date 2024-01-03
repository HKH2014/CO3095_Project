package CP19;

public class SellerRating {
    private Seller seller;
    private double rating;

    public SellerRating(Seller seller, double rating) {
        this.seller = seller;
        this.rating = rating;
    }

    public Seller getSeller() {
        return seller;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
