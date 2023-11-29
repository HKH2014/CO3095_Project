package UserLogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginServiceTest {
	
	@Test
	public void login() {
		User user = new User("Buyer", "buyer1");
		LoginService loginService = new LoginService(user);
		Buyer buyer = new Buyer(loginService);
		
		assertTrue(buyer.login("Buyer", "buyer1"));
		assertFalse(buyer.login("username", "onetwothree"));
		assertFalse(buyer.login("Buyer", "password"));
		assertFalse(buyer.login("Buyer", " "));
	}
}
