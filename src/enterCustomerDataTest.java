/*
package dbdeneme;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class enterCustomerDataTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private static Map<String, String> reservations = new HashMap<>();
    private static Scanner scanner;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        reservations.clear();
        scanner = new Scanner(System.in);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testEnterCustomerData_Positive() throws Exception {
        String simulatedInput = "Mehmet Izmirli\n1234567890\n101\n2024-05-11\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerData");
        method.setAccessible(true);
        method.invoke(null);

        assertTrue(outputStream.toString().contains("Customer data for Mehmet Izmirli with contact number 1234567890 entered, and room 101 reserved successfully."));
        assertEquals("2024-05-11", reservations.get("101"));
    }

    @Test
    public void testEnterCustomerData_RoomAlreadyReserved() throws Exception {
        reservations.put("101", "2024-05-11");
        String simulatedInput = "Melis İzmirli\n0987654321\n101\n2024-05-11\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerData");
        method.setAccessible(true);
        method.invoke(null);

        assertTrue(outputStream.toString().contains("Room 101 is already reserved."));
        assertEquals("2024-05-11", reservations.get("101"));
    }

    @Test
    public void testEnterCustomerData_InvalidDateFormat() throws Exception {
        String simulatedInput = "Melisa İzmirli\n5551234567\n102\n11-05-2024\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("enterCustomerData");
        method.setAccessible(true);
        method.invoke(null);

        // Assuming the implementation should validate the date format and print an error message.
        assertTrue(outputStream.toString().contains("Invalid date format. Please enter date in YYYY-MM-DD format."));
        assertTrue(reservations.isEmpty());
    }
}
*/
