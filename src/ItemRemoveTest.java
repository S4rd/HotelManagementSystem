import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;

public class ItemRemoveTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        // Simulate user input for testing
        String input = "Test Item\n6\n";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    @Test
    public void positiveTestRemoveItemFromInventory() {
        // Add the item to the inventory for testing
        Item.inventory.put("Test Item", 6);

        // Get the initial size of the inventory map
        int initialSize = Item.inventory.size();

        // Call the method to be tested
        Item.removeItemFromInventory();

        // Verify that the item is removed from the inventory map
        assertEquals(initialSize - 1, Item.inventory.size());
        assertFalse(Item.inventory.containsKey("Test Item"));
    }


    @Test
    public void negativeTestRemoveNonExistingItemFromInventory() {
        int initialSize = Item.inventory.size();

        // Simulate user providing non-existing item name
        String nonExistingInput = "NonExistingItem\n5\n";
        testIn = new ByteArrayInputStream(nonExistingInput.getBytes());
        System.setIn(testIn);

        // Call the method to be tested with non-existing item name
        Item.removeItemFromInventory();

        // Verify that no item is removed from the inventory map
        assertEquals(initialSize, Item.inventory.size());
    }
}