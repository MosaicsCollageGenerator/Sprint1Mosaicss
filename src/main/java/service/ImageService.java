package main.java.service;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.params.CoreProtocolPNames.USER_AGENT;

public class ImageService {
    //  private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyCQbxRMKMxuyaIVmosCa_k2sIv5BeavGFs";
    //  private static final String GOOGLE_CX = "007628912923159165220:9e6kozm2iea";  // custom search engine identifier
    private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyADYi8Ob0jmPJbGEMCkJwrB31bOY80RtXs";
    private static final String GOOGLE_CX = "008543189839369971484:b8selplq7z8";
//	  private static final String GOOGLE_SEARCH_API_KEY = "AIzaSyB7_yytK04B7speZc4lXsHLr9ARmwPiUzw";
//	  private static final String GOOGLE_CX = "015527610641952349258:lx1x9pjo0ec";

    //generate url to make request to our Google custom search engine
    private static URL generateRequestURL(String topic, int resultNumber, String garbageString) throws MalformedURLException {
        URL requestURL = null;
        if(resultNumber > 0) {
            requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + topic + "&searchType=image&imgType=photo&imgSize=medium&start=" + resultNumber + "&num=10");
        }
        else {
            requestURL = new URL("http" + garbageString + "s://www.googleapis.com/customsearch/v1?key=" + GOOGLE_SEARCH_API_KEY + "&cx=" + GOOGLE_CX + "&q=" + topic + "&searchType=image&imgType=photo&imgSize=medium&num=10");
        }
        return requestURL;
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
//	                              saveImageToFile(images.size(), resultImage);
                                if (images.size() == 30) {
                                    break;
                                }
                            }
                        } catch (IIOException e) {
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

    public static List<BufferedImage> getTestImages() {
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        String fileExtension = "thirtyimages/";
        String imgExtension = "image";
        String jpgExtension = ".jpg";

        //File outputfile = new File("image" + i + ".jpg");
        int counter = 1;
        for (int i = 0; i < 30; i++) {
            String num = String.valueOf(counter);
            try {
                BufferedImage image = ImageIO.read(new File(fileExtension + imgExtension + num + jpgExtension));
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                counter++;
            }
        }
        return images;
    }

}

