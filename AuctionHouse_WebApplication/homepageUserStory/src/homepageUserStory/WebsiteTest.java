package homepageUserStory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebsiteTest {

	@Test
	public void testShowHomepage() {
		Website website = new Website();
		String expected = "Welcome to the Auction Website!";
		String actual = website.showHomepage();
		assertEquals(expected, actual);
	}
}
