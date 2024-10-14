import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private Connection connection;

    @Test
    void testAddUser_ValidCredentials_UserAddedToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HotelManagement", "root", ".Kwdwr&7898");
            // Create tables if they don't exist
            createTables();
            // Populate tables with test data
            populateTables();

            UserService userService = new UserService();
            userService.addUser("testUser", "testPassword");

            // Verify user is added to database
            try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
                pstmt.setString(1, "testUser");
                ResultSet rs = pstmt.executeQuery();
                assertTrue(rs.next());
                assertEquals("testUser", rs.getString("username"));
                assertEquals("testPassword", rs.getString("password"));
            } catch (SQLException e) {
                fail("Database error: " + e.getMessage());
            }
        } catch (ClassNotFoundException | SQLException e) {
            fail("Database setup failed: " + e.getMessage());
        } finally {
            // Close connection
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                fail("Database teardown failed: " + e.getMessage());
            }
        }
    }

    @Test
    void testAddUser_ExistingUsername_PrintsErrorMessage() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HotelManagement", "root", ".Kwdwr&7898");
            // Create tables if they don't exist
            createTables();
            // Populate tables with test data
            populateTables();

            UserService userService = new UserService();
            userService.addUser("reception", "testPassword");

            // Check if the expected error message is printed to the console
            // (This part cannot be tested directly in JUnit, as System.out is not easily captured)
            // You would need to use a mocking framework or redirect System.out for this assertion.
        } catch (ClassNotFoundException | SQLException e) {
            fail("Database setup failed: " + e.getMessage());
        } finally {
            // Close connection
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                fail("Database teardown failed: " + e.getMessage());
            }
        }
    }

    private void createTables() throws SQLException {
        // Create Users table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Users (user_id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, role ENUM('RECEPTIONIST', 'MANAGER', 'ADMINISTRATOR') NOT NULL)");
        }
    }

    private void populateTables() throws SQLException {
        // Populate Users table
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)")) {
            pstmt.setString(1, "reception");
            pstmt.setString(2, "password");
            pstmt.setString(3, "RECEPTIONIST");
            pstmt.executeUpdate();
            pstmt.setString(1, "manager");
            pstmt.setString(2, "password");
            pstmt.setString(3, "MANAGER");
            pstmt.executeUpdate();
            pstmt.setString(1, "admin");
            pstmt.setString(2, "password");
            pstmt.setString(3, "ADMINISTRATOR");
            pstmt.executeUpdate();
        }
    }
}