/*
package dbdeneme;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class displayRatingsTest {

    private ByteArrayOutputStream outContent;

    public static void main(String[] args) throws Exception {
        displayRatingsTest test = new displayRatingsTest();
        test.setUp();
        test.testDisplayRatingsWithValidData();
        test.setUp();
        test.testDisplayRatingsWithNoData();
        test.setUp();
        test.testDisplayRatingsWithNegativeData();
    }

    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    // Utility method to set the return value of calculateAverageRatings
    private void setCalculateAverageRatingsReturnValue(Map<String, Double> returnValue) throws Exception {
        Method method = HotelManagementSystemMenu.class.getDeclaredMethod("calculateAverageRatings");
        method.setAccessible(true);
        Field field = method.getDeclaringClass().getDeclaredField("calculateAverageRatings");
        field.setAccessible(true);
        field.set(null, returnValue);
    }

    // Positive Test Case: Valid data
    public void testDisplayRatingsWithValidData() throws Exception {
        Map<String, Double> mockData = new HashMap<>();
        mockData.put("Service", 4.5);
        mockData.put("Cleanliness", 4.2);
        mockData.put("Location", 4.8);
        setCalculateAverageRatingsReturnValue(mockData);

        HotelManagementSystemMenu.displayRatings();

        // Verification
        String expectedOutput = "\nHotel Ratings:\nService: 4.50/5\nCleanliness: 4.20/5\nLocation: 4.80/5\n";
        if (outContent.toString().equals(expectedOutput)) {
            System.out.println("Positive test passed");
        } else {
            System.out.println("Positive test failed");
        }
    }

    // Negative Test Case: No data
    public void testDisplayRatingsWithNoData() throws Exception {
        Map<String, Double> mockData = new HashMap<>();
        setCalculateAverageRatingsReturnValue(mockData);

        HotelManagementSystemMenu.displayRatings();

        // Verification
        String expectedOutput = "\nHotel Ratings:\n";
        if (outContent.toString().equals(expectedOutput)) {
            System.out.println("Negative test 1 passed");
        } else {
            System.out.println("Negative test 1 failed");
        }
    }

    // Negative Test Case: Negative data
    public void testDisplayRatingsWithNegativeData() throws Exception {
        Map<String, Double> mockData = new HashMap<>();
        mockData.put("Service", -1.0);
        mockData.put("Cleanliness", -2.0);
        setCalculateAverageRatingsReturnValue(mockData);

        HotelManagementSystemMenu.displayRatings();

        // Verification
        String expectedOutput = "\nHotel Ratings:\nService: -1.00/5\nCleanliness: -2.00/5\n";
        if (outContent.toString().equals(expectedOutput)) {
            System.out.println("Negative test 2 passed");
        } else {
            System.out.println("Negative test 2 failed");
        }
    }
}
*/
