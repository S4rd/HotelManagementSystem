//package dbdeneme;
//
//import org.junit.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//public class RoomTest {
//
//    @Test
//    // This test case checks the positive scenario of the room billing system.
//    public void positiveTestRoomBillingSystem() {
//        // Initialize the inventory with some items and their quantities.
//        Map<String, Integer> inventory = new HashMap<>();
//        inventory.put("Cola", 5);
//        inventory.put("Alcohol", 3);
//        inventory.put("Red Bull", 2);
//        inventory.put("Chocolate Bar", 10);
//        inventory.put("Chips", 7);
//
//        // Simulate user input to order no items for room "101".
//        String simulatedInput = "1\n0\n";
//        InputStream savedStandardInputStream = System.in;
//        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
//        int roomId = 101;
//        String type = "single";
//        String status = "available";
//        // Create a room instance for testing.
//        Room room = new Room(roomId,type,status);
//        // Create a map to store room bills.
//        Map<String, Billing> roomBills = new HashMap<>();
//        // Order room service and update roomBills map.
//        room.orderRoomService(roomBills, inventory);
//
//        // Assert that the room bill for room "101" exists.
//        assertTrue(roomBills.containsKey("101"));
//        // Assert that the total cost for room "101" is 0.0 with a delta of 50 (for double comparison).
//        assertEquals(0.0, roomBills.get("101").getTotalCost(), 50);
//
//        // Restore the standard input stream.
//        System.setIn(savedStandardInputStream);
//    }
//
//    @Test
//    // This test case checks the negative scenario of the room billing system.
//    public void negativeTestRoomBillingSystem() {
//        // Initialize the inventory with some items, setting the quantity of "Cola" to 0.
//        Map<String, Integer> inventory = new HashMap<>();
//        inventory.put("Cola", 0);
//        inventory.put("Alcohol", 3);
//        inventory.put("Red Bull", 2);
//        inventory.put("Chocolate Bar", 10);
//        inventory.put("Chips", 7);
//
//        // Simulate user input to order "Cola" for room "102", which is out of stock.
//        String simulatedInput = "3\n1\n0\n";
//        InputStream savedStandardInputStream = System.in;
//        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
//        int roomid=102;
//        String type = "single";
//        String status = "available";
//        // Create a room instance for testing.
//        Room room = new Room(roomid,type,status);
//        // Create a map to store room bills.
//        Map<String, Billing> roomBills = new HashMap<>();
//        // Order room service and update roomBills map.
//        room.orderRoomService(roomBills, inventory);
//
//        // Assert that the room bill for room "102" exists.
//        assertTrue(roomBills.containsKey("102"));
//        // Assert that the total cost for room "102" is not equal to a non-zero value.
//        assertNotEquals(0.1, roomBills.get("102").getTotalCost());
//
//        // Restore the standard input stream.
//        System.setIn(savedStandardInputStream);
//    }
//
//}