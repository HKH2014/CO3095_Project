
public class Administrator {
    public void viewSellerIdentification(Seller seller) {
        System.out.println("Viewing seller identification:");
        System.out.println("ID: " + seller.getId());  
        System.out.println("Name: " + seller.getName());  
        System.out.println("Credibility: " + seller.getCredibility());
    }

    public void verifySellerReliability(Seller seller) {
        System.out.println("Verifying seller reliability:");
        // Add your verification logic here
        int numberOfRatings = seller.getNumberOfRatings();

        if (numberOfRatings >= 2) {
            System.out.println("Reliable seller with " + numberOfRatings + " ratings.");
        } else {
            System.out.println("Not enough ratings.");
        }
    }
}
