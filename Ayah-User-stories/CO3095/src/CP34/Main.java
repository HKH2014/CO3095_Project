package CP34;

public class Main {
    public static void main(String[] args) {
        // Create instances of Buyer, Review, and Administrator
        Buyer buyer = new Buyer();
        Review review1 = new Review("This is a good service, very happy with it!");
        Review review2 = new Review("Terrible experience! Avoid at all costs.");
        Review review3 = new Review("Impressed with the quality.");

        buyer.addReview(review1);
        buyer.addReview(review2);
        buyer.addReview(review3);

        Administrator admin = new Administrator();

        // Administrator views buyer reviews
        admin.viewBuyerReviews(buyer);

        // Administrator removes abusive reviews
        admin.removeAbusiveReviews(buyer);

        // Administrator adds comments and marks reviews for update
        admin.addCommentAndMarkForUpdate(buyer, 7);

        // Administrator views updated buyer reviews
        admin.viewBuyerReviews(buyer);
    }
}
