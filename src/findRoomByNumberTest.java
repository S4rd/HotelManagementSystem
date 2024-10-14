import dbdeneme.HotelManagementSystemMenu;
import dbdeneme.Room;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class findRoomByNumberTest {

    @Test
    void testFindRoomByNumberWhenRoomExists() {
        // Assuming roomNumber 1 exists and is occupied
        Room room = HotelManagementSystemMenu.findRoomByNumber(1);
        assertNotNull(room);
        assertEquals(1, room.getRoomNumber());
        assertEquals("occupied", room.getStatus());

    }

    @Test
    void testFindRoomByNumberWhenRoomDoesNotExist() {
        // Assuming roomNumber 2 does not exist or is not occupied
        Room room = HotelManagementSystemMenu.findRoomByNumber(2);
        assertNull(room);
    }



}
