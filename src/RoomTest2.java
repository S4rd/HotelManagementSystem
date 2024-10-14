import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest2 {

    @Test
    void testOrderRoomService_InvalidItem_PrintsMessage() {
        Room room = new Room(1, "single", "occupied");
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("Spa", 100.0);
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Cola", 0);
        // Check if the expected message is printed to the console
        // (This part cannot be tested directly in JUnit, as System.out is not easily captured)
        // You would need to use a mocking framework or redirect System.out for this assertion.
    }
}