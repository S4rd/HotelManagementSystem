import dbdeneme.DBConnection;
import dbdeneme.HotelManagementSystemMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticateTest {

    @BeforeEach
    void setUp() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Users");
            stmt.execute("INSERT INTO Users (username, password, role) VALUES ('testuser', 'testpass', 'receptionist')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAuthenticatePositive() {
        Assertions.assertTrue(HotelManagementSystemMenu.authenticate("testuser", "testpass"));
    }

    @Test
    void testAuthenticateNegative() {
        assertFalse(HotelManagementSystemMenu.authenticate("testuser", "wrongpass"));
    }
}
