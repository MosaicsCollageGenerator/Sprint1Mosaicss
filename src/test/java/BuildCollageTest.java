package test.java;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;

import main.java.service.Tester;

import static org.junit.Assert.*;
public class BuildCollageTest {

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test(timeout = 60000)
	public void test() throws IOException {
		Tester.doWork();
		//byte[] byteArray = ((DataBufferByte) bufferedImage.getData().getDataBuffer()).getData();
		BufferedImage testBufferedImage = ImageIO.read(new File("CorrectCollage/correctCollage.png"));
		byte[]  byteTestArray = ((DataBufferByte) testBufferedImage.getData().getDataBuffer()).getData();
		BufferedImage trialBufferedImage = ImageIO.read(new File("correctCollage.png"));
		byte[]  byteTrialArray = ((DataBufferByte) trialBufferedImage.getData().getDataBuffer()).getData();
	
		assertArrayEquals(byteTestArray, byteTrialArray);
	}
	@Test(timeout = 60000)
	public void TestForSufficientImages() throws IOException {
		//Tester.doWork();
		List<BufferedImage> images = Tester.getImages("cats");
		int sufficientNumberOfImages = 30;
		//Size of images array
		int size = images.size();
		assertEquals(size, sufficientNumberOfImages);
	}

}
