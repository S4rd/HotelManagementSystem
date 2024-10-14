import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class loginTestPositive {

    private InputStream mockInputStream;

    @Before
    public void setUp() {
        // Create a custom InputStream that reads from predefined inputs
        String validCredentials = "reception\npassword\n"; // valid credentials
        mockInputStream = new ByteArrayInputStream(validCredentials.getBytes());
        System.setIn(mockInputStream);
    }

    @After
    public void tearDown() {
        // Reset System.in after each test
        System.setIn(System.in);
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Arrange
        HotelManagementSystemMenu.loggedIn = false; // log out

        // Act
        HotelManagementSystemMenu.login();

        // Assert
        assertTrue("Login should be successful with valid credentials", HotelManagementSystemMenu.loggedIn);
    }
}
