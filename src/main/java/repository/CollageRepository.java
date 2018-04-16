package main.java.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.Collage;


public class CollageRepository {

    private String dbUrl;

    public CollageRepository() {
        dbUrl = "jdbc:mysql://localhost/Mosaicss?user=root&password=root";
    }

    // for testing
    public CollageRepository(Boolean testing) {
        dbUrl = "jdbc:mysql://localhost/MosaicssTest?user=root&password=root";
        resetDatabase();
    }

    private Connection connect() {
    	
        String url = "jdbc:mysql://localhost/Mosaicss?user=root&password=root";
        Connection connection = null;
        try {
        		Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection;
    }

    public Collage findByTitle(String title) {
        Collage collage = null;
        String sql = "SELECT * FROM Collage WHERE title='" + title + "'";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("id");
                String src = rs.getString("src");
                String user_id = rs.getString("user_id");
                collage = new Collage(id, title, src, user_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return collage;
    }

    public Collage findById(String id) {
        Collage collage = null;
        String sql = "SELECT * FROM Collage WHERE id='" + id + "'";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String title = rs.getString("username");
                String src = rs.getString("password");
                String user_id = rs.getString("user_id");
                collage = new Collage(title, src, user_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return collage;
    }

    public List<Collage> getAllCollageFromUser(String user_id) {
        List<Collage> allCollage = new ArrayList<>();
        String sql = "SELECT * FROM Collage WHERE user_id='" + user_id+ "'";;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String src = rs.getString("src");
                allCollage.add(new Collage(id, title, src, user_id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCollage;
    }

    public void saveCollage(Collage collage) {
        String sql = "INSERT INTO Collage(title,src,user_id) VALUES(?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql)) {
            pstmt.setString(1, collage.getTitle());
            pstmt.setString(2, collage.getSrc());
            pstmt.setString(3, collage.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetDatabase() {
        String sql = "DELETE FROM `Collage` WHERE id >= 1;";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement())
              {
                  stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

