import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HotelManagementSystemMenuTest {

    // Tests if the method correctly identifies when a room is reserved.
    @Test
    public void testIsRoomReservedWhenRoomIsReserved() {
        int roomid;
        String type;
        String status;
        Room room = new Room(roomid=1,type = "single",status="available");
        HotelManagementSystemMenu.rooms.add(room);
        int reservedRoomId = 1;

        assertTrue(HotelManagementSystemMenu.isRoomReserved(roomid));
    }

    // Verifies that the method accurately reports when a room is not reserved.
    @Test
    public void testIsRoomReservedWhenRoomIsNotReserved() {
        assertFalse(HotelManagementSystemMenu.isRoomReserved(Integer.parseInt("102")));
    }


}



