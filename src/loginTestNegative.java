import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;

public class loginTestNegative {

    private InputStream mockInputStream;

    @Before
    public void setUp() {
        // Create a custom InputStream that reads from predefined inputs
        String invalidCredentials = "invalid\ninvalid\n"; // invalid credentials
        mockInputStream = new ByteArrayInputStream(invalidCredentials.getBytes());
        System.setIn(mockInputStream);
    }

    @After
    public void tearDown() {
        // Reset System.in after each test
        System.setIn(System.in);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Arrange
        HotelManagementSystemMenu.loggedIn = false; // log out

        // Act
        HotelManagementSystemMenu.login();

        // Assert
        assertFalse("Login should fail with invalid username and password", HotelManagementSystemMenu.loggedIn);
    }

}
