/*
package dbdeneme;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

public class cancelReservation {

    @Test
    public void testCancelReservation_NonExisting() {
        int roomNumberToCancel = 999; // Var olmayan bir oda numarası.

        // Rezervasyonun var olmadığını doğrulayın.
        boolean reservationExists = HotelManagementSystemMenu.isRoomReserved(roomNumberToCancel);
        assertFalse(reservationExists, "Rezervasyon zaten mevcut. Test başarısız oldu.");

        // Rezervasyonu iptal edin.
        HotelManagementSystemMenu.cancelReservation(); // Bu satırda sorun var!

        // Veritabanında rezervasyonun hala olmadığını doğrulayın.
        reservationExists = HotelManagementSystemMenu.isRoomReserved(roomNumberToCancel);
        assertFalse(reservationExists, "Rezervasyon iptal edilmesine rağmen, veritabanında hala mevcut.");

        // Odanın durumunun hala "available" olduğunu doğrulayın.
        String roomStatus = getRoomStatus(roomNumberToCancel);
        assertEquals("available", roomStatus, "Oda durumu, rezervasyon iptal edildikten sonra 'available' olarak güncellenmedi.");
    }

    @Test
    public void testCancelReservation_Existing() {
        int roomNumberToCancel = 6; // Var olan ve rezerve edilmiş bir oda numarası.

        // Rezervasyonun var olduğunu doğrulayın.
        boolean reservationExists = HotelManagementSystemMenu.isRoomReserved(roomNumberToCancel);
        assertTrue(reservationExists, "Rezervasyon mevcut değil. Test başarısız oldu.");

        // Rezervasyonu iptal edin.
        HotelManagementSystemMenu.cancelReservation(); // Bu satırda sorun var!

        // Veritabanında rezervasyonun artık olmadığını doğrulayın.
        reservationExists = HotelManagementSystemMenu.isRoomReserved(roomNumberToCancel);
        assertFalse(reservationExists, "Rezervasyon iptal edilmesine rağmen, veritabanında hala mevcut.");

        // Odanın durumunun "available" olarak güncellendiğini doğrulayın.
        String roomStatus = getRoomStatus(roomNumberToCancel);
        assertEquals("available", roomStatus, "Oda durumu, rezervasyon iptal edildikten sonra 'available' olarak güncellenmedi.");
    }

    private String getRoomStatus(int roomNumber) {
        String roomStatus = "";
        String sql = "SELECT status FROM Rooms WHERE room_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    roomStatus = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomStatus;
    }
}*/
