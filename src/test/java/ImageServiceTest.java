package test.java;

import main.java.service.ImageService;
import main.java.service.Tester;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ImageServiceTest {
    @Test(timeout = 60000)
    public void test_GetImages_GrabsSufficientImages() throws IOException {
        List<BufferedImage> images = ImageService.getImages("cats");
        int sufficientNumberOfImages = 30;
        //Size of images array
        int size = images.size();
        assertEquals(size, sufficientNumberOfImages);
    }

    @Test(timeout = 60000)
    public void test_GetTestImages_GrabsSufficientImages() throws IOException {
        List<BufferedImage> images = ImageService.getTestImages();
        int sufficientNumberOfImages = 30;
        //Size of images array
        int size = images.size();
        assertEquals(size, sufficientNumberOfImages);
    }
}
