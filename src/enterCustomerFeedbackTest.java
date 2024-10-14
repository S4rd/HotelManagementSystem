/*
package dbdeneme;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class enterCustomerFeedbackTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private static List<Feedback> feedbacks = new ArrayList<>();
    private static Scanner scanner;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        feedbacks.clear();
        scanner = new Scanner(System.in);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    @Test
    public void testEnterCustomerFeedback_Positive() throws Exception {
        String simulatedInput = "5\n4\n3\n5\n5\n2\n4\n5\n4\n4\n4\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerFeedback");
        method.setAccessible(true);
        method.invoke(null);

        assertEquals(1, feedbacks.size());
        Feedback feedback = feedbacks.get(0);
        assertEquals(5, feedback.getHygiene());
        assertEquals(4, feedback.getAnimation());
        assertEquals(3, feedback.getRoomComfort());
        assertEquals(5, feedback.getStaffFriendliness());
        assertEquals(4, feedback.getFoodQuality());
        assertEquals(3, feedback.getAmenities());
        assertEquals(5, feedback.getCleanliness());
        assertEquals(4, feedback.getWifiService());
        assertEquals(3, feedback.getLocation());
        assertEquals(5, feedback.getValueForMoney());
        assertEquals(4, feedback.getSecurity());
        assertEquals(3, feedback.getEnvironmentalPractices());
        assertTrue(outputStream.toString().contains("HotelManagment.Feedback entered successfully."));
    }
    @Test
    public void testEnterCustomerFeedback_InvalidRatingTooLow() throws Exception {
        String simulatedInput = "0\n2\n3\n4\n1\n5\n5\n4\n3\n3\n4\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerFeedback");
        method.setAccessible(true);
        method.invoke(null);

        assertTrue(outputStream.toString().contains("Invalid rating. Please enter a number between 1 and 5."));
        assertTrue(feedbacks.isEmpty());
    }

    @Test
    public void testEnterCustomerFeedback_InvalidRatingTooHigh() throws Exception {
        String simulatedInput = "4\n4\n3\n6\n4\n5\n5\n2\n3\n5\n5\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerFeedback");
        method.setAccessible(true);
        method.invoke(null);

        assertTrue(outputStream.toString().contains("Invalid rating. Please enter a number between 1 and 5."));
        assertTrue(feedbacks.isEmpty());
    }
}
*/
