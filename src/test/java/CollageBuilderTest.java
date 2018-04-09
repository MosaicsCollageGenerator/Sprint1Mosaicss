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

    @Test(timeout = 60000)
    public void test_Build_CreatesCorrectCollage() throws IOException {
        CollageBuilder collageBuilder =  new CollageBuilder.Builder("cat", "meow").testing(true).build();
        BufferedImage collage = collageBuilder.build();
        BufferedImage correctCollage = ImageIO.read(new File("CorrectCollage/correctCollage.png"));
        byte[]  byteTestArray = ((DataBufferByte) correctCollage.getData().getDataBuffer()).getData();
        byte[]  byteCollageArray = ((DataBufferByte) collage.getData().getDataBuffer()).getData();

        assertArrayEquals(byteTestArray, byteCollageArray);
    }

}
