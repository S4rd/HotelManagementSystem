import dbdeneme.HotelManagementSystemMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginTest {
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", ".Kwdwr&7898");
        String createUserTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "role ENUM('receptionist', 'manager', 'administrator') NOT NULL" +
                ")";
        String insertUser = "INSERT INTO Users (username, password, role) VALUES ('testuser', 'testpassword', 'administrator')";
        connection.prepareStatement(createUserTable).execute();
        connection.prepareStatement(insertUser).execute();
    }

    @After
    public void tearDown() throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE username = 'testuser'";
        connection.prepareStatement(deleteUser).execute();
        connection.close();
    }

    @Test
    public void testAuthenticatePositive() {
        boolean result = HotelManagementSystemMenu.authenticate("testuser", "testpassword");
        assertTrue(result);
    }

    @Test
    public void testAuthenticateNegative() {
        boolean result = HotelManagementSystemMenu.authenticate("testuser", "wrongpassword");
        assertFalse(result);
    }

    @Test
    public void testLoginPositive() {
        boolean authenticated = HotelManagementSystemMenu.authenticate("testuser", "testpassword");
        assertTrue(authenticated);
    }

    @Test
    public void testLoginNegative() {
        boolean authenticated = HotelManagementSystemMenu.authenticate("testuser", "wrongpassword");
        assertFalse(authenticated);
    }
}

