package SellerViewPage;

public class Seller {
	private String username;
	private double rating;
	
	public Seller(String username, double rating) {
		this.username = username;
		this.rating = rating;
	}
	
	public String getUsername() {
		return username;
	}
	
	public double getRating() {
		return rating;
	}
	
}
