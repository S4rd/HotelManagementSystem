import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class EventDeleteTest {
// AYRI AYRI GEÇİYOR
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        // Simulate user input for testing
        String input = "Event Name"; // Provide the name of the event to be deleted
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        // Restore original System.in
        System.setIn(systemIn);
    }

    @Test
    public void positiveDeleteEvent() {
        // Add a sample event to the list
        Event.events.add(new Event("Event Name", "2024-04-29", "12:00", "Location", "Notes"));

        // Call the method to be tested
        Event.deleteEvent();

        // Verify that the event has been deleted
        assertEquals(0, Event.events.size()); // The events list should be empty after deletion
    }
    @Test
    public void deleteNegativeEvent() {
        // Add some sample events to the list
        Event.events.add(new Event("Event1", "2024-04-30", "14:30", "Location1", "Notes1"));
        Event.events.add(new Event("Event2", "2024-05-01", "15:00", "Location2", "Notes2"));

        // Get the initial size of the events list
        int initialSize = Event.events.size();

        // Call the method to be tested
        Event.deleteEvent();

        // Verify that the events list remains unchanged
        assertEquals(initialSize, Event.events.size()); // The size of the list should remain the same
    }
}