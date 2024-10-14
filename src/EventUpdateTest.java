import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;

public class EventUpdateTest {
// AYRI AYRI GEÇİYOR
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        // Simulate user input for testing
        String input = "1\nNew Event Name\n2024-04-30\n14:30\nNew Location\nNew Notes";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        // Restore original System.in
        System.setIn(systemIn);
    }

    @Test
    public void positiveTestUpdateEvent() {
        // Add a sample event to the list
        Event.events.add(new Event("Event Name", "2024-04-29", "12:00", "Location", "Notes"));

        // Call the method to be tested
        Event.updateEvent();

        // Get the updated event from the list
        Event updatedEvent = Event.events.get(0);

        // Assert that the event has been updated correctly
        assertEquals("New Event Name", updatedEvent.getEventName());
        assertEquals("2024-04-30", updatedEvent.getDate());
        assertEquals("14:30", updatedEvent.getTime());
        assertEquals("New Location", updatedEvent.getLocation());
        assertEquals("New Notes", updatedEvent.getNotes());
    }
    @Test
    public void negativeTestUpdateEvent() {
        // Add a sample event to the list
        Event.events.add(new Event("Event Name", "2024-04-29", "12:00", "Location", "Notes"));

        // Call the method to be tested
        Event.updateEvent();

        // Get the updated event from the list
        Event updatedEvent = Event.events.get(0);

        // Assert that the event has been updated correctly
        assertNotEquals("Event Name", updatedEvent.getEventName());
        assertNotEquals("2024-04-29", updatedEvent.getDate());
        assertNotEquals("12:00", updatedEvent.getTime());
        assertNotEquals("Location", updatedEvent.getLocation());
        assertNotEquals("Notes", updatedEvent.getNotes());
    }


}