package main.java.service;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.http.params.CoreProtocolPNames.USER_AGENT;

public class Tester {
//    private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyCQbxRMKMxuyaIVmosCa_k2sIv5BeavGFs";
//    private static final String GOOGLE_CX = "007628912923159165220:9e6kozm2iea";  // custom search engine identifier
//    private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyADYi8Ob0jmPJbGEMCkJwrB31bOY80RtXs";
//    private static final String GOOGLE_CX = "008543189839369971484:b8selplq7z8";
    private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyB7_yytK04B7speZc4lXsHLr9ARmwPiUzw";
    private static final String GOOGLE_CX = "015527610641952349258:lx1x9pjo0ec";
    public static void main(String[] args) {
        doWork();
    }
    
    public static void doWork() {
    		long startTime = System.currentTimeMillis();
        
        List<BufferedImage> images = getImages("yellow");
        //GETS IMAGES FOR TESTING PURPOSES
        //List<BufferedImage> images = getImagesTest();
        
        System.out.println("IMAGES SIZE " + images.size());
        BufferedImage i = buildCollage(images); //COMMENTED OUT DURING TESTING
           i = resizeImage(300, 300, i); //DID THIS DURING IMPLEMENTATION
        
        //COMMENTED REST OF MAIN TO TEST
        TexturedText t = new TexturedText(i);
        JFrame f = t.getFrame();
        f.setVisible(true);
        BufferedImage bi = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
        try {
        		Thread.sleep(2000);
                int w = f.getWidth();
                int h = f.getHeight();
                BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = im.createGraphics();
                f.paint(g);
                ImageIO.write(im, "png", new File("correctCollage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        		e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
//        System.out.println("Retrieved " + images.size() + " images");
        try {
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(f.getLocationOnScreen().x, f.getLocationOnScreen().y, f.getWidth(), f.getHeight()));
            ImageIO.write(image, "png", new File("correctCollage.png"));

		} catch (AWTException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Duration: " + duration / 1000.0 + " seconds");
    }
    static class TexturedText extends JPanel {
        private BufferedImage bi;
        public TexturedText(BufferedImage bi) {
            this.bi = bi;
        }
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Font font = new Font("Impact", Font.PLAIN, 200);
            g2.setFont(font);
            String s = "super";
            Dimension d = getSize();
            float x = 20, y = 200;
            BufferedImage bi = getTextureImage();
            Rectangle r = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
            TexturePaint tp = new TexturePaint(bi, r);
            g2.setPaint(tp);
            g2.drawString(s, x, y);
        }
        private BufferedImage getTextureImage() {
            // Create the test image.
            int size = 8;
            Graphics2D g2 = bi.createGraphics();
//            g2.setPaint(Color.red);
//            g2.fillRect(0, 0, size / 2, size / 2);
//            g2.setPaint(Color.yellow);
//            g2.fillRect(size / 2, 0, size, size / 2);
//            g2.setPaint(Color.green);
//            g2.fillRect(0, size / 2, size / 2, size);
//            g2.setPaint(Color.blue);
//            g2.fillRect(size / 2, size / 2, size, size);
            return bi;
        }
        public JFrame getFrame() {
            JFrame f = new JFrame();
            f.getContentPane().add(new TexturedText(bi));
            f.setSize(800, 250);
            return f;
        }
    }
    // Services
    // generate url to make request to our Google custom search engine
    public static URL generateRequestURL(String topic, int resultNumber, String garbageString) throws MalformedURLException {
        URL requestURL = null;
        if(resultNumber > 0) {
            requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + topic + "&searchType=image&imgType=photo&imgSize=medium&start=" + resultNumber + "&num=10");
        }
        else {
            requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + topic + "&searchType=image&imgType=photo&imgSize=medium&num=10");
        }
        return requestURL;
    }
    
    //Used for testing
   public static List<BufferedImage> getImagesTest(){
	   List<BufferedImage> images = new ArrayList<BufferedImage>();
	   String fileExtension = "thirtyimages/";
	   String imgExtension = "image";
	   String jpgExtension = ".jpg";
	   
	   //File outputfile = new File("image" + i + ".jpg");
	   int counter = 1;
	   for(int i = 0;i < 30;i++) {
		   String num = String.valueOf(counter);
		   try {
			BufferedImage image = ImageIO.read(new File(fileExtension + imgExtension + num + jpgExtension ));
			images.add(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   finally {
			   counter++;
		   }
	   }
	 
	   
	   
	   return images;
   }
    // The public interface exposed. Returns a list of images given a topic name (query) 
    public static List<BufferedImage> getImages(String query) {
        List<BufferedImage> images = new ArrayList<>();
        try {
            // maintain count of number of results fetched from API
            int resultNum = 0;
            while(images.size() < 30) {
                URL requestURL = generateRequestURL(query, resultNum, "");
                URL obj = null;
                HttpURLConnection con = null;
                StringBuffer response = new StringBuffer();
                con = (HttpURLConnection) requestURL.openConnection();
                con.setRequestMethod("GET");
                //add request header
                con.setRequestProperty("User-Agent",  USER_AGENT);
                con.addRequestProperty("Cookie", "name1=Denim");
                int responseCode = con.getResponseCode();
                System.out.println("\nSending ‘GET’ request to URL : " + requestURL.toString());
                System.out.println("Response Code : " + responseCode);
                while (responseCode != 200) {
                    System.out.println("Sending");
                    if (obj != null) {
                        con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        //add request header
                        con.setRequestProperty("User-Agent",  USER_AGENT);
                        con.addRequestProperty("Cookie", "name1=Denim");
                        responseCode = con.getResponseCode();
                    }
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String output;
                // parse through JSON response for image links, line by line
                while((output = reader.readLine()) != null) {
                    if(output.contains("\"link\": \"")) {
                        String link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(), output.indexOf("\","));
                        URL imageURL = new URL(link);
                        try {
                            BufferedImage resultImage = (BufferedImage) ImageIO.read(imageURL);
                            if (resultImage != null) {
                                images.add(resultImage);
                                //CALL FUNCTION TO SAVE PICTURES ONE BY ONE
//                                saveImageToFile(images.size(), resultImage);
                                if (images.size() == 30) {
                                    break;
                                }
                            }
                        } catch (IIOException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                con.disconnect();
                resultNum += 10;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }
    
    //HELPER FUNCTION TO SAVE DOWNLOADED IMAGES, WILL ONLY BE USED TO SAVE THE PICTURES LOCALLY
    public static void saveImageToFile(Integer i, BufferedImage bi) {
    		//File outputfile = new File("image.jpg");
    		File outputfile = new File("image" + i + ".jpg");
    		try {
				ImageIO.write(bi, "jpg", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    // Builder
    public static BufferedImage buildCollage(List<BufferedImage> images) {
        // formatImages is a helper function used to format images (resize, add border)
        images = formatImages(images);
        int wid = 800;
        int height = 600;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        int currX = -50;
        int currY = -50;
        Random random = new Random();
        int i = 0;
        while(currY < 600) {
            // random number between -45 to 45
//             int number = random.nextInt(max + 1 -min) + min;
            int rotation = random.nextInt(45 + 1 - (-45)) - 45;
            double rotationRadians = rotation * Math.PI / 180.0;
            if (currX < 800 && currY < 600) {
                g2.drawImage(rotateImage(images.get(i), rotationRadians), null, currX, currY);
//                 g2.drawImage(images.get(i), null, currX, currY);
            }

            currX += 10;
            // if going out of bounds to the right
            if (currX > 790) {
                // if going out of bound to the bottom
                if (currY > 590) {
                    // reset
                    currX = -50;
                    currY = -50;
                } else {
                    // shift down
                    currX = -50;
                    currY += 10;
                }
            }
            ++i;
            if (i == 30) {
                i = 0;
            }
        }
        g2.dispose();
        return newImage;
    }
    // Helper function to apply resizing and border to image
    private static List<BufferedImage> formatImages(List<BufferedImage> images) {
        for (int i = 0; i < images.size(); ++i) {
            images.set(i, resizeImage(200, 200, images.get(i)));
            images.set(i, addBorderToImage(images.get(i), 3));
        }
        return images;
    }
    // Helper function to resize image
    private static BufferedImage resizeImage(int width, int height, BufferedImage image) {
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return newImage;
    }
    // Helper function to add a white border to image// COMMENTED OUT TO TEST
    private static BufferedImage addBorderToImage(BufferedImage image, int borderWidth) {
//        Graphics2D g = image.createGraphics();
//        int height = image.getHeight();
//        int width = image.getWidth();
//        int borderControl = 1;
//        //set border color
//        g.setColor(Color.RED);
//        //set border thickness
//        g.setStroke(new BasicStroke(borderWidth));
//        //to fix issue for even numbers
//        if(borderWidth % 2 == 0){
//            borderControl = 0;
//        }
//        g.drawLine(0, 0, 0, height);
//        g.drawLine(0, 0, width, 0);
//        g.drawLine(0, height - borderControl, width, height - borderControl);
//        g.drawLine(width - borderControl, height - borderControl, width - borderControl, 0);
        return image;
    }
    // Helper function to rotate an image //COMMENTED OUT TO TEST
    private static BufferedImage rotateImage(BufferedImage image, double rotation) {
//        AffineTransform at = new AffineTransform();
//        // 4. translate it to the center of the component
//        at.translate(image.getWidth() / 2, image.getHeight() / 2);
//        // 3. do the actual rotation
//        at.rotate(rotation * Math.PI/4.0);
//        // 2. just a scale because this image is big
//        at.scale(0.5, 0.5);
//        // 1. translate the object so that you rotate it around the
//        //    center (easier :))
//        at.translate(-image.getWidth()/2, -image.getHeight()/2);
//        // draw the image
//        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
//        Graphics2D g = (Graphics2D) newImage.getGraphics();
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(image, at, null);
//        File outputfile = new File("rotated.png");
//        try {
//            ImageIO.write(newImage, "png", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        return newImage;
    		return image;
    }
 
}
