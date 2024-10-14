/*
package dbdeneme;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CancelReservationPositiveTest {

    @Test
    void testCancelReservationSuccess() throws SQLException {
        // Simulate input
        String simulatedInput = "101\n"; // Enter room number 101
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            // Perform the test
            int roomNumber = 101;

            try (Connection conn = DBConnection.getConnection()) {
                String insertReservationSQL = "INSERT INTO Reservations (room_number) VALUES (?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertReservationSQL)) {
                    pstmt.setInt(1, roomNumber);
                    pstmt.executeUpdate();
                }
            }

            HotelManagementSystemMenu.cancelReservation();

            try (Connection conn = DBConnection.getConnection()) {
                String selectReservationSQL = "SELECT * FROM Reservations WHERE room_number = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(selectReservationSQL)) {
                    pstmt.setInt(1, roomNumber);
                    assertFalse(pstmt.executeQuery().next(), "Reservation should be canceled successfully.");
                }
            }
        } finally {
            // Restore original standard input
            System.setIn(savedStandardInputStream);
        }
    }
}*/
