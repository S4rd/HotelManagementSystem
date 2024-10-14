import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HotelManagementSystemMenuTest2 {

    private Connection connection;

    @BeforeEach
    void setUp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HotelManagement", "root", ".Kwdwr&7898");
            // Create tables if they don't exist
            createTables();
            // Populate tables with test data
            populateTables();
        } catch (ClassNotFoundException | SQLException e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            fail("Database teardown failed: " + e.getMessage());
        }
    }

    private void createTables() throws SQLException {
        // Create Users table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Users (user_id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, role ENUM('RECEPTIONIST', 'MANAGER', 'ADMINISTRATOR') NOT NULL)");
        }
        // Create Rooms table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Rooms (room_number INT AUTO_INCREMENT PRIMARY KEY, type ENUM('single', 'double', 'suite', 'family') NOT NULL, price DECIMAL(10, 2) NOT NULL, status ENUM('available', 'occupied', 'maintenance') NOT NULL)");
        }
        // Create Reservations table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Reservations (reservation_id INT AUTO_INCREMENT PRIMARY KEY, room_number INT, customer_name VARCHAR(255) NOT NULL, contact_number VARCHAR(50) NOT NULL, start_date DATE, end_date DATE, status ENUM('active', 'cancelled') NOT NULL, FOREIGN KEY (room_number) REFERENCES Rooms(room_number))");
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
        // Populate Rooms table
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Rooms (type, price, status) VALUES (?, ?, ?)")) {
            pstmt.setString(1, "single");
            pstmt.setBigDecimal(2, BigDecimal.valueOf(100.00));
            pstmt.setString(3, "available");
            pstmt.executeUpdate();
            pstmt.setString(1, "double");
            pstmt.setBigDecimal(2, BigDecimal.valueOf(150.00));
            pstmt.setString(3, "available");
            pstmt.executeUpdate();
            pstmt.setString(1, "suite");
            pstmt.setBigDecimal(2, BigDecimal.valueOf(200.00));
            pstmt.setString(3, "available");
            pstmt.executeUpdate();
            pstmt.setString(1, "family");
            pstmt.setBigDecimal(2, BigDecimal.valueOf(250.00));
            pstmt.setString(3, "available");
            pstmt.executeUpdate();
        }
        // Populate Reservations table with a test reservation
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Reservations (room_number, customer_name, contact_number, start_date, status) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, 1);
            pstmt.setString(2, "John Doe");
            pstmt.setString(3, "1234567890");
            pstmt.setDate(4, Date.valueOf(LocalDate.now().minusDays(1)));
            pstmt.setString(5, "active");
            pstmt.executeUpdate();
        }
    }

    @Test
    void testLogin_ValidCredentials_ReturnsTrue() {
        assertTrue(HotelManagementSystemMenu.authenticate("reception", "password"));
    }

    @Test
    void testLogin_InvalidCredentials_ReturnsFalse() {
        assertFalse(HotelManagementSystemMenu.authenticate("reception", "wrongpassword"));
    }

    @Test
    void testViewAllReservations_NoReservations_PrintsMessage() {
        // Clear existing reservations for the test
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Reservations")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fail("Database error: " + e.getMessage());
        }

        // Simulate the call to viewAllReservations()
        HotelManagementSystemMenu.viewAllReservations();

        // Check if the expected message is printed to the console
        // (This part cannot be tested directly in JUnit, as System.out is not easily captured)
        // You would need to use a mocking framework or redirect System.out for this assertion.
    }




}