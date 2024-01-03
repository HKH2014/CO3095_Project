package CP19;

public class Main {
    public static void main(String[] args) {
        // Original sellers
        Seller seller1 = new Seller(1, "John Doe", 3.0);
        Seller seller2 = new Seller(2, "Jane Dough", 1.0);
        Seller seller3 = new Seller(3, "Mike Holmes", 5.0);
        Seller seller4 = new Seller(4, "Georgia Martins", 2.5);


        Buyer buyer = new Buyer();

        // Buyer adds ratings
        buyer.addSellerRating(seller1, 4.5);
        buyer.addSellerRating(seller2, 3.0);
        buyer.addSellerRating(seller3, 4.8);
        buyer.addSellerRating(seller4, 2.0);

        // View all ratings
        System.out.println("All Seller Ratings:");
        buyer.viewAllSellerRatings();

        // Update a rating
        buyer.updateSellerRating(seller1, 4.0);

        // Delete a rating
        buyer.deleteSellerRating(seller2);

        // View all ratings after updates
        System.out.println("\nSeller Ratings After Updates:");
        buyer.viewAllSellerRatings();
    }
}

