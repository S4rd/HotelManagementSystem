import dbdeneme.HotelManagementSystemMenu;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isRoomReservedTest {

    @Test
    void testIsRoomReservedWhenRoomIsReserved() {
        // Assuming roomNumber 1 is reserved
        assertTrue(HotelManagementSystemMenu.isRoomReserved(1));
    }

    @Test
    void testIsRoomReservedWhenRoomIsNotReserved() {
        // Assuming roomNumber 2 is not reserved
        assertFalse(HotelManagementSystemMenu.isRoomReserved(2));
    }


}
