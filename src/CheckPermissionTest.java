import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckPermissionTest {

    @Before
    public void setUp() {
        HotelManagementSystemMenu.loggedIn = true;
        HotelManagementSystemMenu.loggedInRole = "ADMINISTRATOR";
    }

    @Test
    public void testCheckPermission() {
        // Test with allowed role
        assertTrue(HotelManagementSystemMenu.checkPermission("ADMINISTRATOR"));

        // Test with multiple allowed roles
        assertTrue(HotelManagementSystemMenu.checkPermission("RECEPTIONIST", "ADMINISTRATOR"));
    }

    @Test
    public void testCheckPermissionNegative() {
        // Test with disallowed role
        assertFalse(HotelManagementSystemMenu.checkPermission("MANAGER"));

        // Test with no allowed roles
        assertFalse(HotelManagementSystemMenu.checkPermission("RECEPTIONIST"));
    }
}
