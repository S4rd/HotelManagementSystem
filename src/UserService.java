import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserService {
   public void addUser(String username, String password) {
       String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
      try (Connection conn = DBConnection.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, username);
           pstmt.setString(2, password);
           int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
               System.out.println("Kullanıcı başarıyla eklendi.");
         }
       } catch (SQLException e) {           System.out.println("Kullanıcı eklenirken bir hata oluştu: " + e.getMessage());
           e.printStackTrace();
       }
  }
}
