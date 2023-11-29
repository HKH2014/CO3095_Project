package SellerViewPage;

public class Buyer {
	private ProfileService profileService;
	
	public Buyer(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	public String viewSellerProfile(Seller seller) {
		return profileService.viewProfile(seller);
	}
}
