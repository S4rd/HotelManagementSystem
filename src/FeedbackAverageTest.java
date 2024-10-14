import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FeedbackAverageTest {

    @Before
    public void setUp() {
        Feedback.feedbacks.clear(); // Clear any existing feedback before each test

        // Adding sample feedbacks
        Feedback.feedbacks.add(new Feedback(5, 4, 3, 5, 4, 3, 5, 4, 3, 5, 4, 3));
        Feedback.feedbacks.add(new Feedback(4, 5, 4, 3, 5, 4, 3, 5, 4, 3, 5, 4));
    }

    @Test
    public void testCalculateAverageRatings_Positive() {
        Map<String, Double> averageRatings = Feedback.calculateAverageRatings();

        // Assert the averages are as expected
        assertEquals(4.5, averageRatings.get("Hygiene"), 0.01);
        assertEquals(4.5, averageRatings.get("Animation"), 0.01);
        assertEquals(3.5, averageRatings.get("Room Comfort"), 0.01);
        assertEquals(4.0, averageRatings.get("Staff Friendliness"), 0.01);
        assertEquals(4.5, averageRatings.get("Food Quality"), 0.01);
        assertEquals(3.5, averageRatings.get("Amenities"), 0.01);
        assertEquals(4.0, averageRatings.get("Cleanliness"), 0.01);
        assertEquals(4.5, averageRatings.get("WiFi Service"), 0.01);
        assertEquals(3.5, averageRatings.get("Location"), 0.01);
        assertEquals(4.0, averageRatings.get("Value for Money"), 0.01);
        assertEquals(4.5, averageRatings.get("Security"), 0.01);
        assertEquals(3.5, averageRatings.get("Environmental Practices"), 0.01);
    }

    @Test
    public void testCalculateAverageRatings_Negative() {
        Map<String, Double> averageRatings = Feedback.calculateAverageRatings();

        // Assert that the incorrect values are not returned
        assertNotEquals(5.0, averageRatings.get("Hygiene"), 0.01);
        assertNotEquals(4.0, averageRatings.get("Animation"), 0.01);
        assertNotEquals(4.0, averageRatings.get("Room Comfort"), 0.01);
        assertNotEquals(5.0, averageRatings.get("Staff Friendliness"), 0.01);
        assertNotEquals(3.0, averageRatings.get("Food Quality"), 0.01);
        assertNotEquals(4.0, averageRatings.get("Amenities"), 0.01);
        assertNotEquals(5.0, averageRatings.get("Cleanliness"), 0.01);
        assertNotEquals(3.0, averageRatings.get("WiFi Service"), 0.01);
        assertNotEquals(4.0, averageRatings.get("Location"), 0.01);
        assertNotEquals(3.0, averageRatings.get("Value for Money"), 0.01);
        assertNotEquals(5.0, averageRatings.get("Security"), 0.01);
        assertNotEquals(4.0, averageRatings.get("Environmental Practices"), 0.01);
    }
}