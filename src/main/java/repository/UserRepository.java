package main.java.repository;
import java.sql.PreparedStatement;

import main.java.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {

    public UserRepository() {
        dbUrl = "jdbc:mysql://localhost/Mosaicss?user=root&password=root";
    }

    private String dbUrl;
    public UserRepository(Boolean testing) {
        dbUrl = "jdbc:mysql://localhost/MosaicssTest?user=root&password=root";
        resetDatabase();
    }

    
    private Connection connect() {
        Connection connection = null;
        
        try {
        		Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } catch (IllegalAccessException e) {
        		e.printStackTrace();
        } catch (InstantiationException e) {
        		e.printStackTrace();
        } catch (ClassNotFoundException e) {
        		e.printStackTrace();
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
                user = new User(userId,username, password);
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

    public int getNumUsers() {
    		return getAllUsers().size();
    	
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
        String sql2 = "DELETE FROM `User` WHERE id > 1;";
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


