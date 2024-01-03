package CP19;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private List<SellerRating> sellerRatings;

    public Buyer() {
        this.sellerRatings = new ArrayList<>();
    }

    // Method to add a seller rating
    public void addSellerRating(Seller seller, double rating) {
        SellerRating sellerRating = new SellerRating(seller, rating);
        sellerRatings.add(sellerRating);
    }

    // Method to update a seller rating
    public void updateSellerRating(Seller seller, double newRating) {
        // Assume the seller has only one rating for simplicity
        SellerRating sellerRating = getSellerRating(seller);
        if (sellerRating != null) {
            sellerRating.setRating(newRating);
        }
    }

    // Method to delete a seller rating
    public void deleteSellerRating(Seller seller) {
        SellerRating sellerRating = getSellerRating(seller);
        if (sellerRating != null) {
            sellerRatings.remove(sellerRating);
        }
    }

    // Method to view all seller ratings
    public void viewAllSellerRatings() {
        for (SellerRating rating : sellerRatings) {
            System.out.println("Buyer rated Seller " + rating.getSeller().getName() +
                    " with a rating of " + rating.getRating());
        }
    }

    // Helper method to get a seller rating
    private SellerRating getSellerRating(Seller seller) {
        for (SellerRating rating : sellerRatings) {
            if (rating.getSeller().getId() == seller.getId()) {
                return rating;
            }
        }
        return null;
    }

    // Getter for seller ratings
    public List<SellerRating> getSellerRatings() {
        return sellerRatings;
    }
}

