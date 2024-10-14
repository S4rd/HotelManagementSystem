import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HousekeeperTest {

    @Before
    public void setUp() throws Exception {
        // Method for initializing
    }

    @After
    public void tearDown() throws Exception {
        // Method for clearing after test cases
    }


    @Test
    public void testConstructorAndGetName() {
        // Positive test case to verify the constructor

        Housekeeper housekeeper = new Housekeeper("Alice", true);

        assertEquals("Alice", housekeeper.getName());
    }
    @Test
    public void negativeTestConstructorAndGetName() {
        // Negative test case to verify the constructor
        Housekeeper housekeeper = new Housekeeper("Alice", true);

        assertNotEquals("Ali", housekeeper.getName());
    }


    @Test
    public void testIsAvailableForTimeSpan_Positive() {
        // Positive test case to verify the isAvailableForTimeSpan method when the housekeeper is available
        Housekeeper housekeeper = new Housekeeper("Alice", true);
        housekeeper.setAvailableForTimeSpan("08:00-11:00", true);
        assertTrue(housekeeper.isAvailableForTimeSpan("08:00-11:00"));
    }

    @Test
    public void testSetAvailableForTimeSpan_Negative() {
        // Negative test case to verify the setAvailableForTimeSpan method when the housekeeper is not available
        Housekeeper housekeeper = new Housekeeper("Bob", true);
        housekeeper.setAvailableForTimeSpan("15:00-18:00", false);
        assertFalse(housekeeper.isAvailableForTimeSpan("15:00-18:00"));
    }

}