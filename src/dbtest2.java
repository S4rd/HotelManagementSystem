import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class dbtest2 {


    @Test
    public void testAddUser() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String insertSql = "INSERT INTO Users (username, password, role) VALUES (?, ?, 'receptionist')";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
            pstmt.setString(1, "newuser");
            pstmt.setString(2, "password123");
            int affectedRows = pstmt.executeUpdate();
            assertEquals(1, affectedRows);
        }

        String selectSql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSql)) {
            pstmt.setString(1, "newuser");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("newuser", rs.getString("username"));
            assertEquals("password123", rs.getString("password"));
            assertEquals("receptionist", rs.getString("role"));
        }
    }
    @Test
    public void testUpdateRoomStatus() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String updateSql = "UPDATE Rooms SET status = 'occupied' WHERE room_number = 1";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSql)) {
            int affectedRows = pstmt.executeUpdate();
            assertEquals(1, affectedRows);
        }

        String selectSql = "SELECT status FROM Rooms WHERE room_number = 1";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSql)) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("occupied", rs.getString("status"));
        }
    }
    @Test
    public void testLoginWithInvalidUsername() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String selectSql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSql)) {
            pstmt.setString(1, "invaliduser");
            ResultSet rs = pstmt.executeQuery();
            assertFalse(rs.next()); // Hiçbir sonuç bulunmamalı
        }
    }

    @Test
    public void testCheckRoomStatusAvailable() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String selectSql = "SELECT status FROM Rooms WHERE room_number = 2";

        try (PreparedStatement pstmt = connection.prepareStatement(selectSql)) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("available", rs.getString("status"));
        }
    }
    @Test
    public void testCheckRoomStatusNotAvailable() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String selectSql = "SELECT status FROM Rooms WHERE room_number = 6";

        try (PreparedStatement pstmt = connection.prepareStatement(selectSql)) {
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next()); // Oda bulunmalı
            assertNotEquals("available", rs.getString("status")); // Durumu "available" olmamalı
        }
    }

}
