package SellerViewPage;

public class ProfileService {
	public String viewProfile(Seller seller) {
		return "Username: " + seller.getUsername() + "\nRating: " + seller.getRating();
	}
}
