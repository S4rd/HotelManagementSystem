import dbdeneme.DBConnection;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Before
    public void setUp() {
        // Her test çalıştırılmadan önce çalıştırılacak hazırlık kodları buraya eklenebilir
        // Örneğin, gerekli veritabanı bağlantısı veya mock nesneleri oluşturulabilir
    }

    @Test
    public void testGetConnectionSuccess() {
        try {
            Connection connection = DBConnection.getConnection();
            assertNotNull("Expected connection to be successfully established", connection);
        } catch (Exception e) {
            fail("Expected no exception to be thrown, but got: " + e.getMessage());
        }
    }

    @Test
    public void testGetConnectionFailure() {
        String invalidUrl = "jdbc:mysql://localhost:3306/InvalidDB";
        try {
            DriverManager.getConnection(invalidUrl, "root", ".Kwdwr&7898");
            fail("Expected an SQLException to be thrown for unknown database");
        } catch (SQLException e) {
            assertTrue("Expected SQL exception message to contain 'Unknown database', but got: " + e.getMessage(),
                    e.getMessage().contains("Unknown database"));
        }
    }
}
