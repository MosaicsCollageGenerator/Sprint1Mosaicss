import com.mysql.jdbc.PreparedStatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {

    public UserRepository() {

    }

    public UserRepository(Boolean testing) {
        resetDatabase();
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost/Mosaicss?user=root&password=root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return connection;
    }

    public User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM User WHERE username='" + username + "'";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String userId = rs.getString("id");
                String password = rs.getString("password");
                user = new User(username, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User findById(String id) {
        User user = null;
        Double notifyP = -1.0;
        String sql = "SELECT * FROM User WHERE id='" + id + "'";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String userId = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(userId, username, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String userId = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                allUsers.add(new User(userId, username, password));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO User(username,password) VALUES(?,?)";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetDatabase() {
		String sql = "DELETE FROM `Collage` WHERE id > 1;";
        String sql2 = "DELETE FROM `User` WHERE id >= 1;";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement())
              {
                  stmt.execute(sql);
                  stmt.execute(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

