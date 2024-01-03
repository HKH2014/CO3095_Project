package CP34;

import java.util.ArrayList;
import java.util.List;

class Buyer {
    private List<Review> reviews;

    public Buyer() {
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
