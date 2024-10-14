import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import static org.junit.Assert.*;

public class BackToMenuTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        // Restore original System.in and System.out after the test completes
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testBackToMenu_Positive() throws IOException {
        // Set up input stream with a newline character (simulate "Enter" key press)
        ByteArrayInputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        System.setIn(inputStream);

        // Call the method under test
        HotelManagementSystemMenu.backToMenu();

        // Verify the behavior by checking the console output
        assertTrue(outputStream.toString().contains("Press Enter to go back to the main menu..."));
    }



}