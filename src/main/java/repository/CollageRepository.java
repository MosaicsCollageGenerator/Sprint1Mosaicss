package main.java.repository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;


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

    public List<String> getAllCollageFromUser(String user_id) {
        List<String> allCollage = new ArrayList<>();
        String sql = "SELECT * FROM Collage WHERE user_id='" + user_id+ "'";;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String src = null;
               // Blob b = rs.getBlob("src");//cast with (Blob) if required. Blob from resultSet as rs.getBlob(index).
                //InputStream bis = b.getBinaryStream();
                InputStream binaryStream = rs.getBinaryStream("src");
                Scanner s = new Scanner(binaryStream);
                src = s.hasNext() ? s.next() : "";
                //ObjectInputStream ois = new ObjectInputStream(binaryStream);
                //src= (String) ois.readObject();

                allCollage.add(src);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCollage;
    }

    public void saveCollage(Collage collage) {
        String sql = "INSERT INTO Collage(title,src,user_id) VALUES(?,?,?)";
        FileInputStream fis = null;
        try (Connection connection = this.connect();
             PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sql)) {
//        		File file = new File(collage.getSrc());
//        		fis = new FileInputStream(file);
//        		System.out.println("THIS IS THE SOURCE");
//        		System.out.println(collage.getSrc());
//        		String base64Image = collage.getSrc().split(",")[1];
        		String base64Image = collage.getSrc();
        		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

        		File outputfile = new File("collage.jpg");
        		ImageIO.write(img, "jpg", outputfile);
        		fis = new FileInputStream(outputfile);
        		System.out.println("THIS IS COLLAGE USER IS: "+collage.getUserId());
        		pstmt.setString(1, collage.getTitle());
            pstmt.setBinaryStream(2,fis,(int) outputfile.length());
            pstmt.setString(3, collage.getUserId());
            pstmt.executeUpdate();
            System.out.println("FINISHED SAVING");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
        	 System.out.println(e.getMessage());
        } catch (IOException e) {
			e.printStackTrace();
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
