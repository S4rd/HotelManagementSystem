import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest2 {

    @Test
    void testEvent_ValidDateAndTime_ConstructorSucceeds() {
        assertDoesNotThrow(() -> new Event("Test Event", "2024-05-31", "10:00", "Conference Room", "Test Event Description"));
    }

    @Test
    void testEvent_InvalidDateFormat_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event("Test Event", "2024-05-31A", "10:00", "Conference Room", "Test Event Description"));
    }
}