package CP19;


public class Seller {
    private int id;
    private String name;
    private double credibility;
    private int numberOfRatings;  // New field to track the number of ratings

    // Constructor
    public Seller(int id, String name, double credibility) {
        this.id = id;
        this.name = name;
        this.credibility = credibility;
        this.numberOfRatings = 0;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCredibility() {
        return credibility;
    }
    
    public int getNumberOfRatings() {
        return numberOfRatings;
    }
    // Method to update credibility based on buyer rating (between 1 and 5)
    public void updateCredibility(double buyerRating) {
        if (buyerRating >= 1.0 && buyerRating <= 5.0) {
            // Calculate the weighted average based on existing credibility, number of ratings, and buyer rating
            double weightedAverage = ((credibility * numberOfRatings) + buyerRating) / (numberOfRatings + 1);
            credibility = weightedAverage;
            numberOfRatings++;
        } else {
            System.out.println("Invalid entry. Ratings must be between 1 and 5.");
        }
    }
}

