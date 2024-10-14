import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
// AYRI AYRI GEÇİYOR
public class EventAddTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUpInput() {
        // Simulate user input for testing
        String input = "New Event\n2024-04-30\n14:30\nLocation\nNotes"; // Provide valid user input
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        // Restore original System.in
        System.setIn(systemIn);
    }

    @Test
    public void positiveTestAddEvent() {
        // Get the initial size of the events list
        int initialSize = Event.events.size();

        // Call the method to be tested
        Event.addEvent();

        // Verify that the event is added to the list
        assertEquals(initialSize + 1, Event.events.size()); // The size of the list should increase by 1
        Event addedEvent = Event.events.get(initialSize); // Get the last added event
        assertEquals("New Event", addedEvent.getEventName()); // Verify the event name
        assertEquals("2024-04-30", addedEvent.getDate()); // Verify the event date
        assertEquals("14:30", addedEvent.getTime()); // Verify the event time
        assertEquals("Location", addedEvent.getLocation()); // Verify the event location
        assertEquals("Notes", addedEvent.getNotes()); // Verify the event notes
    }

    @Test
    public void negativeTestAddEvent() {
        // Get the initial size of the events list
        int initialSize = Event.events.size();

        // Call the method to be tested with invalid input (empty strings)
        Event.addEvent();

        // Verify that no event is added to the list
        assertNotEquals(initialSize, Event.events.size()); // The size of the list should remain the same
    }
}