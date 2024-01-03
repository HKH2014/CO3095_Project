package CP34;

class Administrator {
    public void viewBuyerReviews(Buyer buyer) {
        System.out.println("Buyer Reviews:");
        for (Review review : buyer.getReviews()) {
            System.out.println("Content: " + review.getContent() +
                    ", Abusive: " + review.isAbusive() +
                    ", Removed: " + review.isRemoved());
        }
    }

    public void removeAbusiveReviews(Buyer buyer) {
        System.out.println("Removing Abusive Reviews:");
        for (Review review : buyer.getReviews()) {
            if (review.isAbusive()) {
                review.markAsRemoved();
                System.out.println("Review removed: " + review.getContent());
            }
        }
    }

    public void addCommentAndMarkForUpdate(Buyer buyer, int days) {
        System.out.println("Adding Comments and Marking Reviews for Update:");
        for (Review review : buyer.getReviews()) {
            if (!review.isRemoved()) {
                // Add logic to check if the review needs a comment and marking for update
                System.out.println("Comment added to review: " + review.getContent());
                System.out.println("Review marked for update in " + days + " days.");
            }
        }
    }
}
