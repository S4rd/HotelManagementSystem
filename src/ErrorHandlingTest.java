import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorHandlingTest {

    @Test
    void testErrorHandlingForClassNotFound() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // If class is found, no exception is thrown
        } catch (ClassNotFoundException e) {
            fail("MySQL JDBC driver should be found");
        }
    }

    @Test
    void testErrorHandlingForClassNotFound2() {
        try {
            Class.forName("com.nonexistent.Driver");
            fail("Exception should be thrown for non-existent driver class");
        } catch (ClassNotFoundException e) {
            assertTrue(e.getMessage().contains("com.nonexistent.Driver"), "Expected ClassNotFoundException for nonexistent driver");
        }
    }
}