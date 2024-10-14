import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomCleaningTest2 {

    @Test
    void testGetHousekeeper_ValidHousekeeper_ReturnsCorrectHousekeeper() {
        Housekeeper housekeeper = new Housekeeper("John Doe", true);
        RoomCleaning cleaning = new RoomCleaning(housekeeper, "1", "10:00 AM - 11:00 AM");
        assertEquals(housekeeper, cleaning.getHousekeeper());
    }


}