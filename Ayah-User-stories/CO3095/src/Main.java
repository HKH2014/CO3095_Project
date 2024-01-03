
public class Main {
    public static void main(String[] args) {
        // Sellers with their initial credibility 
        Seller seller1 = new Seller(1, "John Doe", 3.0);
        Seller seller2 = new Seller(1, "Jane Dough", 1.0);
        Seller seller3 = new Seller(1, "Mike Holmes", 5.0);
        Seller seller4 = new Seller(1, "Georgia Martins", 2.5);

        // Create an Administrator instance
        Administrator admin = new Administrator();

        // Seller identification for sellers
        admin.viewSellerIdentification(seller1);
        admin.viewSellerIdentification(seller2);
        admin.viewSellerIdentification(seller3);
        admin.viewSellerIdentification(seller4);

        // Simulate a buyer giving a rating to seller1 
        double buyerRating1 = 4.5;
        seller1.updateCredibility(buyerRating1);

        // New buyer giving a rating to seller1
        double buyerRating2 = 3.8;
        seller1.updateCredibility(buyerRating2);
        
        // New buyer giving a rating to seller2
        double buyerRating3 = 1.0;
        seller2.updateCredibility(buyerRating3);
        
        // New buyer giving a rating to seller3
        double buyerRating4 = 3.1;
        seller3.updateCredibility(buyerRating4);
        
        // New buyer giving a rating to seller4
        double buyerRating5 = 4.0;
        seller4.updateCredibility(buyerRating5);

        // Administrator views and verifies seller identification again for sellers
        admin.viewSellerIdentification(seller1);
        admin.viewSellerIdentification(seller2);
        admin.viewSellerIdentification(seller3);
        admin.viewSellerIdentification(seller4);

        // Administrator verifies seller reliability for sellers
        admin.verifySellerReliability(seller1);
        admin.verifySellerReliability(seller2);
        admin.verifySellerReliability(seller3);
        admin.verifySellerReliability(seller4);
    }
}
