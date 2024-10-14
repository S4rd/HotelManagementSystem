import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class allReservations {

    @Before
    public void setUp() {
        // Test öncesi gerekli düzenlemeler
        HotelManagementSystemMenu.rooms = new ArrayList<>();
        HotelManagementSystemMenu.reservations = new HashMap<>();
    }

    @Test
    public void isThereReservationPositive() {
        // Arrange
        int roomId = 1;
        String type = "single";
        String status = "available";
        String reservationDate = "2024-04-15";
        Room room = new Room(roomId, type, status); // Simulated addReservation at enterCustomerData()
        HotelManagementSystemMenu.rooms.add(room);
        HotelManagementSystemMenu.reservations.put(String.valueOf(roomId), reservationDate);

        // Act
        HotelManagementSystemMenu.viewAllReservations();

        // Assert
        assertTrue(HotelManagementSystemMenu.reservations.containsKey(String.valueOf(roomId))); // Check if the room ID exists in reservations
        assertEquals(reservationDate, HotelManagementSystemMenu.reservations.get(String.valueOf(roomId))); // Check if the reservation date matches
    }

    @Test
    public void isThereReservationNegative() {
        // Arrange
        int roomId = 1;
        String type = "single";
        String status = "available";
        String nonExistingRoomId = "111"; // HotelManagement.Room ID that does not have a reservation
        String reservationDate = "2024-04-15";
        Room room = new Room(roomId, type, status); // Simulated addReservation at enterCustomerData()
        HotelManagementSystemMenu.rooms.add(room);
        HotelManagementSystemMenu.reservations.put(String.valueOf(roomId), reservationDate);

        // Act
        HotelManagementSystemMenu.viewAllReservations();

        // Assert
        assertTrue(HotelManagementSystemMenu.reservations.containsKey(String.valueOf(roomId))); // Check if the existing room ID has a reservation
        assertFalse(HotelManagementSystemMenu.reservations.containsKey(nonExistingRoomId)); // Check if the non-existing room ID does not have a reservation
        assertNull(HotelManagementSystemMenu.reservations.get(nonExistingRoomId)); // Check if the reservation for the non-existing room ID is null
    }
}
