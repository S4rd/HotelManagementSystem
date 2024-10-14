import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BillingTest {
    private Billing billing;

    // Method to set up the test environment before each test case
    @Before
    public void setUp() throws Exception {
        billing = new Billing("101");
    }

    // Method to clean up the test environment after each test case
    @After
    public void tearDown() throws Exception {
        // No tear-down required in this case
    }

    // Test case to verify the addItem method
    @Test
    public void testAddItem() {
        // Creating a sample item
        Item item = new Item("Soap", 1.5);
        // Adding two instances of the item
        billing.addItem(item, 2);
        // Asserting that the total cost is as expected
        assertEquals(3.0, billing.getTotalCost(), 0.001);
    }

    // Test case to verify the displayItemizedCosts method
    @Test
    public void testDisplayItemizedCosts() {
        // Creating sample items
        Item item1 = new Item("Shampoo", 5.0);
        Item item2 = new Item("Towel", 3.0);
        // Adding items to the billing
        billing.addItem(item1, 1);
        billing.addItem(item2, 2);
        // Asserting that billing object is not null
        assertNotNull(billing);
        // Displaying itemized costs
        billing.displayItemizedCosts();
    }

    // Test case to verify handling of negative quantity in addItem method
    @Test
    public void testAddNegativeQuantity() {
        // Creating a sample item
        Item item = new Item("Soap", 1.5);
        // Adding a negative quantity of the item
        billing.addItem(item, -2);
        // Asserting that total cost remains 0
        assertEquals(0.0, billing.getTotalCost(), 0.001);
    }
}