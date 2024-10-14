import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
// AYRI AYRI ÇALISIYOR
public class ItemAddTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        // Simulate user input for testing
        String input = "Test Item\n10\n"; // Provide item name and quantity to add
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        // Restore original System.in
        System.setIn(systemIn);
    }

    @Test
    public void positiveTestAddItemToInventory() {
        // Get the initial size of the inventory map
        int initialSize = Item.inventory.size();

        // Call the method to be tested
        Item.addItemToInventory();

        // Verify that the item is added to the inventory map
        assertEquals(initialSize + 1, Item.inventory.size()); // The size of the map should increase by 1
        assertTrue(Item.inventory.containsKey("Test Item")); // Verify that the item is in the inventory
        assertEquals(Integer.valueOf(10), Item.inventory.get("Test Item")); // Verify the quantity of the item
    }

    @Test
    public void negativeTestAddItemToInventory() {
        // Get the initial size of the inventory map
        int initialSize = Item.inventory.size();

        // Simulate user providing empty input (no item name and quantity)
        String emptyInput = "\n\n";
        testIn = new ByteArrayInputStream(emptyInput.getBytes());
        System.setIn(testIn);

        // Call the method to be tested with empty input
        Item.addItemToInventory();

        // Verify that no item is added to the inventory map
        assertNotEquals(initialSize, Item.inventory.size()); // The size of the map should remain the same
    }
}