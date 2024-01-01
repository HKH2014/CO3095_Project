import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class WatchlistServiceTest {
    @Test
    public void testAddProperty() {
        WatchlistService service = new WatchlistService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Property property = new Property("1", "123 Main St", "3 bedrooms, 2 bathrooms");
        service.addProperty(buyer, property);
        assertEquals(1, service.viewProperties(buyer).size());
    }
    @Test
    public void testUpdateProperty() {
        WatchlistService service = new WatchlistService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Property property = new Property("1", "123 Main St", "3 bedrooms, 2 bathrooms");
        service.addProperty(buyer, property);
        service.updateProperty(buyer, "1", "4 bedrooms, 3 bathrooms");
        Property updatedProperty = service.viewProperties(buyer).get(0);
        assertEquals("4 bedrooms, 3 bathrooms", updatedProperty.getDetails());
    }

    @Test
    public void testRemoveProperty() {
        WatchlistService service = new WatchlistService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Property property = new Property("1", "123 Main St", "3 bedrooms, 2 bathrooms");
        service.addProperty(buyer, property);
        service.removeProperty(buyer, "1");
        assertTrue(service.viewProperties(buyer).isEmpty());
    }

    @Test
    public void testViewProperties() {
        WatchlistService service = new WatchlistService();
        Buyer buyer = new Buyer("john_doe", "john.doe@example.com", "password");
        Property property1 = new Property("1", "123 Main St", "3 bedrooms, 2 bathrooms");
        Property property2 = new Property("2", "456 Oak St", "2 bedrooms, 1 bathroom");
        service.addProperty(buyer, property1);
        service.addProperty(buyer, property2);
        List<Property> properties = service.viewProperties(buyer);
        assertEquals(2, properties.size());
        assertTrue(properties.contains(property1));
        assertTrue(properties.contains(property2));
    }
}