import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HousekeeperTest2 {

    @Test
    void testIsAvailableForTimeSpan_InitiallyAvailable_ReturnsTrue() {
        Housekeeper housekeeper = new Housekeeper("John Doe", true);
        assertTrue(housekeeper.isAvailableForTimeSpan("10:00 AM - 11:00 AM"));
    }

    @Test
    void testSetAvailableForTimeSpan_SetToUnavailable_ReturnsFalse() {
        Housekeeper housekeeper = new Housekeeper("John Doe", true);
        housekeeper.setAvailableForTimeSpan("10:00 AM - 11:00 AM", false);
        assertFalse(housekeeper.isAvailableForTimeSpan("10:00 AM - 11:00 AM"));
    }
}