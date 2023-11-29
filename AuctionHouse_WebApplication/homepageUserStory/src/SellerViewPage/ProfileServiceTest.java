package SellerViewPage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileServiceTest {
	
	@Test
	public void viewProfile() {
		Seller seller = new Seller("seller1", 4.5);
		ProfileService profileService = new ProfileService();
		String profile = profileService.viewProfile(seller);
		assertEquals("Username: seller1\nRating: 4.5", profile);
	}
	
}
