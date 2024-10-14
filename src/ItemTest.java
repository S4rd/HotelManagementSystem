import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ItemTest {
    @Test
    public void testGetName() {
        // Create an item with name "Soap" and price 1.5
        Item item = new Item("Soap", 1.5);
        // Assert that the getName() method returns "Soap"
        assertEquals("Soap", item.getName());
    }

    @Test
    public void testGetPrice() {
        // Create an item with name "Shampoo" and price 5.0
        Item item = new Item("Shampoo", 5.0);
        // Assert that the getPrice() method returns 5.0
        assertEquals(5.0, item.getPrice(), 0.001);
    }
    @Test
    public void testNullName() {
        // Create an item with null name and price 1.5
        Item item = new Item(null, 1.5);
        // Assert that the getName() method returns null
        assertEquals(null, item.getName());
    }

}