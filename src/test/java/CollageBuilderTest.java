package test.java;

import main.java.builder.CollageBuilder;
import main.java.service.Tester;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CollageBuilderTest {

    @Test
    public void test_Initialization_StartsWithDefaultParameter() {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow").testing(true).build();
        assertNotNull(collageBuilder.getTopic());
        assertNotNull(collageBuilder.getShape());
        assertNotNull(collageBuilder.getFilter());
        assertNotNull(collageBuilder.getHeight());
        assertNotNull(collageBuilder.getWidth());
        assertNotNull(collageBuilder.getRotation());
        assertNotNull(collageBuilder.getBorder());
    }

    @Test
    public void test_Build_CreatesCorrectCollage() throws IOException {
        CollageBuilder collageBuilder =  new CollageBuilder.Builder("cat", "meow").testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/correctCollage.png"));
        assertEquals(correctCollage.getHeight(), collage.getHeight());
        assertEquals(correctCollage.getWidth(), collage.getWidth());
        for (int x = 0; x < collage.getWidth(); x++) {
            for (int y = 0; y < collage.getHeight(); y++) {
                assertEquals(correctCollage.getRGB(x, y), collage.getRGB(x, y));
            }
        }
    }

    @Test
    public void test_Filter_CreatesGreyscaleImage() {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow")
                .filter(CollageBuilder.Filter.GRAYSCALE).testing(true).build();
        BufferedImage collage = collageBuilder.build();

        int width = collage.getWidth();
        int height = collage.getHeight();

        int pixel,red, green, blue;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                //scan through each pixel
                pixel = collage.getRGB(i, j);
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = (pixel) & 0xff;

                //check if R=G=B
                assertEquals(red, green);
                assertEquals(green, blue);
            }
    }

    // TO COPY:
    // TO-DO: GENERATE A CORRECT SEPIA IMAGE AND PASTE IT INTO CORRECTCOLLAGE
    @Test
    public void test_Filter_CreatesSepiaImage() throws IOException {
        CollageBuilder collageBuilder =  new CollageBuilder.Builder("cat", "meow")
                .filter(CollageBuilder.Filter.SEPIA).testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/sepiaCollage.png"));
        assertEquals(correctCollage.getHeight(), collage.getHeight());
        assertEquals(correctCollage.getWidth(), collage.getWidth());
        for (int x = 0; x < collage.getWidth(); x++) {
            for (int y = 0; y < collage.getHeight(); y++) {
                assertEquals(correctCollage.getRGB(x, y), collage.getRGB(x, y));
            }
        }
    }

    @Test
    public void test_Filter_CreatesBWImage() {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow")
                .filter(CollageBuilder.Filter.BW).testing(true).build();
        BufferedImage collage = collageBuilder.build();

        int width = collage.getWidth();
        int height = collage.getHeight();

        int pixel,red, green, blue;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                //scan through each pixel
                pixel = collage.getRGB(i, j);
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = (pixel) & 0xff;

                //check if R=G=B=0 || R=G=B=255
                assertTrue((red == 0xFFFFFF && red == green && green == blue) || (red == 0x0 && red == green && green == blue));
            }
    }

    @Test
    public void test_ResizeCollage_CorrectlyResizeCollageDimension() {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow")
                .height(1000).width(1000).testing(true).build();
        BufferedImage collage = collageBuilder.build();
        int expectedHeight = 1000;
        int expectedWidth = 1000;
        assertEquals(expectedHeight, collage.getHeight());
        assertEquals(expectedWidth, collage.getWidth());
    }

    // TO-DO: ADD A COLLAGE WITH BORDER IN CORRECTCOLLAGE FOLDER
    @Test
    public void test_Borders_CorrectlyAppliesBordersToCollage() throws IOException {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow")
                .border(true).testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/borderCollage.png"));
        assertEquals(correctCollage.getHeight(), collage.getHeight());
        assertEquals(correctCollage.getWidth(), collage.getWidth());
        for (int x = 0; x < collage.getWidth(); x++) {
            for (int y = 0; y < collage.getHeight(); y++) {
                assertEquals(correctCollage.getRGB(x, y), collage.getRGB(x, y));
            }
        }
    }

    // TO-DO: REFACTOR ROTATION TO INT
    // ROTATION OF 365 MEANS RANDOM, ELSE ROTATE TO THAT DEGREE
    // ADD AN IMAGE 90 degree rotation
    @Test
    public void test_Rotations_CorrectAppliesRotationToImage() throws IOException {
        CollageBuilder collageBuilder = new CollageBuilder.Builder("cat", "meow")
                .rotation(90).testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/90DegreeRotation.png"));
        assertEquals(correctCollage.getHeight(), collage.getHeight());
        assertEquals(correctCollage.getWidth(), collage.getWidth());
        for (int x = 0; x < collage.getWidth(); x++) {
            for (int y = 0; y < collage.getHeight(); y++) {
                assertEquals(correctCollage.getRGB(x, y), collage.getRGB(x, y));
            }
        }
    }

    @Test
    public void test_Rotations_ZeroDegreeReturnsCollageWithNoRotation() throws IOException {
        CollageBuilder collageBuilder =  new CollageBuilder.Builder("cat", "meow")
                .rotation(0).testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/correctCollage.png"));
        assertEquals(correctCollage.getHeight(), collage.getHeight());
        assertEquals(correctCollage.getWidth(), collage.getWidth());
        for (int x = 0; x < collage.getWidth(); x++) {
            for (int y = 0; y < collage.getHeight(); y++) {
                assertEquals(correctCollage.getRGB(x, y), collage.getRGB(x, y));
            }
        }
    }
}