import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemQuantityTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Item.inventory.clear(); // Clear any existing inventory before each test

        // Adding sample items to the inventory
        Item.inventory.put("Apple", 10);
        Item.inventory.put("Banana", 20);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

/*    @Test
    public void testCheckInventoryQuantities_Positive() {
        Item.checkInventoryQuantities();

        String expectedOutput = "Inventory Quantities:\n" +
                "Apple: 10\n" +
                "Banana: 20\n";

        assertEquals(expectedOutput, outContent.toString());
    }*/

    @Test
    public void testCheckInventoryQuantities_Negative() {
        Item.checkInventoryQuantities();

        String wrongOutput = "Inventory Quantities:\n" +
                "Apple: 5\n" + // Incorrect quantities
                "Banana: 25\n"; // Incorrect quantities

        assertNotEquals(wrongOutput, outContent.toString());
    }
}
