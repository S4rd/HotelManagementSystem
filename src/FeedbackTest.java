import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FeedbackTest {

    @Test
    public void testSetAndGetHygiene() {
        // Positive test case to verify setting and getting hygiene rating
        Feedback feedback = new Feedback(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        feedback.setHygiene(5);
        assertEquals(5, feedback.getHygiene());
    }



    @Test
    public void testSetOutOfRangeRating() {
        // Negative test case to verify setting a hygiene rating out of range
        Feedback feedback = new Feedback(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        feedback.setHygiene(-1);
        assertEquals(-1, feedback.getHygiene());
    }
}