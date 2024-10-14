import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("Conference", "2023-10-01", "10:00", "Conference Hall 1", "Annual tech conference");
        assertEquals("Conference", event.getEventName());
        assertEquals("2023-10-01", event.getDate());
        assertEquals("10:00", event.getTime());
        assertEquals("Conference Hall 1", event.getLocation());
        assertEquals("Annual tech conference", event.getNotes());
    }

    @Test
    public void testEventCreationWithInvalidTime() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Event event = new Event("Conference", "2023-10-01", "invalid-time", "Conference Hall 1", "Annual tech conference");
        });
        assertEquals("Invalid time format", exception.getMessage());
    }


    @Test
    public void testSetValidDate() {
        Event event = new Event("Workshop", "2023-05-15", "14:00", "HotelManagment.Room 402", "Coding workshop");
        event.setDate("2023-06-10");
        assertEquals("2023-06-10", event.getDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDate() {
        Event event = new Event("Workshop", "2023-05-15", "14:00", "HotelManagment.Room 402", "Coding workshop");
        event.setDate("2023-02-30"); // Invalid date (February 30th doesn't exist)
    }


}