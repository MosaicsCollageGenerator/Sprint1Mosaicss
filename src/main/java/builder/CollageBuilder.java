package main.java.builder;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.java.service.ImageService;

public class CollageBuilder {
    // Required parameters
    private final String topic;
    private final String shape;
    // Optional parameters
    private final int height;
    private final int width;
    private final Filter filter;
    private final int rotation;
    private final boolean border;
    private final boolean testing;

    public enum Filter {
        None, SEPIA, BW, GRAYSCALE
    }

//    public static void main(String[] args) {
//    		CollageBuilder builder = new CollageBuilder.Builder("dogs", "dogs", 800, 600, Filter.None, true, true, false).build();
//    		BufferedImage image = builder.build();
//    		try {
//	            ImageIO.write(image, "png", new File("rotatedcollage.png"));
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//    }

    public static class Builder {
        // Required parameters
        private String topic;
        private String shape;
        // Optional parameters
        private int height = 800;
        private int width = 600;
        private Filter filter = Filter.None;
        private int rotation = 0;
        private boolean border = false;
        private boolean testing = false;

        public Builder(String topic, String shape, int height, int width, Filter filter, int rotation, boolean border, boolean testing) {
            this.topic = topic;
            this.shape = shape;
            this.height = height;
            this.width = width;
            this.filter = filter;
            this.rotation = rotation;
            this.border = border;
            this.testing = testing;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder filter(Filter filter) {
            this.filter = filter;
            return this;
        }

        public Builder rotation(int rotation) {
            this.rotation = rotation;
            return this;
        }

        public Builder border(boolean border) {
            this.border = border;
            return this;
        }

        public Builder testing(boolean testing) {
            this.testing = testing;
            return this;
        }

        public CollageBuilder build() {
            return new CollageBuilder(this);
        }

    }

    // Getters

    public String getTopic() {
        return topic;
    }

    public String getShape() {
        return shape;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Filter getFilter() {
        return filter;
    }

    public int getRotation() {
        return rotation;
    }

    public Boolean getBorder() {
        return border;
    }

    public boolean getTesting() {
        return testing;
    }

    // TO-DO
    private List<BufferedImage> applyBordersToImages(List<BufferedImage> images, boolean border) {
        if (border) {
        		for(int i=0; i<images.size(); i++)
        		{
        	        Graphics2D g = images.get(i).createGraphics();
        	        int height = images.get(i).getHeight();
        	        int width = images.get(i).getWidth();
        	        int borderControl = 1;
        	        //set border color
        	        g.setColor(Color.RED);
        	        //set border thickness
        	        g.setStroke(new BasicStroke(6));
        	        //to fix issue for even numbers
//        	        if(borderWidth % 2 == 0){
//        	            borderControl = 0;
//        	        }
        	        g.drawLine(0, 0, 0, height);
        	        g.drawLine(0, 0, width, 0);
        	        g.drawLine(0, height - borderControl, width, height - borderControl);
        	        g.drawLine(width - borderControl, height - borderControl, width - borderControl, 0);
        		}
        }
        return images;
    }

    // TO-DO
    public List<BufferedImage> rotateImages(List<BufferedImage> images, int rotation) {
      if (rotation != 0)
      {
  			Vector<Integer> degreeRotations = null;
        if (rotation == 365)
        {
          // Generate random values until there is at least one zero present
    			int minDegree = 1;
    			while (minDegree > 0)
          {
    				degreeRotations = generateDegrees();
    				int indexOfZero = degreeRotations.indexOf(0);
    				if (indexOfZero > -1)
            {
    					Collections.swap(degreeRotations, 0, indexOfZero);
    					minDegree = 0;
    				}
    			}
        } else
        {
            degreeRotations = new Vector<>();
          for (int i = 0; i < images.size(); ++i)
          {
            degreeRotations.add(rotation);
          }
        }

    		for(int i=0; i<images.size(); i++)
    		{
    			images.set(i, rotateImage(images.get(i),degreeRotations.get(i)));
    		}
      }
        return images;
    }
    //////////////////////////////////////
	public BufferedImage rotateImage(BufferedImage src,int inDegrees) {
		// Calculate the width and height of the rotated image
		double rad = Math.toRadians(inDegrees);
		double sin = Math.abs(Math.sin(rad)), cos = Math.abs(Math.cos(rad));
	    int srcWidth = src.getWidth(), srcHeight = src.getHeight();
	    int rotWidth = (int)Math.floor(srcWidth*cos+srcHeight*sin), rotHeight = (int) Math.floor(srcHeight*cos + srcWidth*sin);

	    // Rotate the image using Graphics2D
	    BufferedImage rotatedImage = new BufferedImage(rotWidth, rotHeight,
	    		BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = rotatedImage.createGraphics();
	    g2d.rotate(Math.toRadians(inDegrees), rotWidth/2, rotHeight/2);
	    g2d.drawImage(src, (rotWidth-srcWidth)/2, (rotHeight-srcHeight)/2, null);
        g2d.dispose();
        return rotatedImage;
	}
	////////////////////////////////////////////

    public BufferedImage buildSingleCollage(List<BufferedImage> images, int height, int width,String shape) {
        // formatImages is a helper function used to format images (resize, add border)
        for (int i = 0; i < images.size(); ++i) {
            images.set(i, resizeCollage(images.get(i), 50, 50));
        }

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        //draw image
        g2.setColor(oldColor);
        int currX = 0;
        int currY = 0;
        int i = 1;

        //taking the length to determine how long the collage should be
        int length = shape.length()/3;
        width *=length;

        images.set(0,resizeCollage(images.get(0), (height),(width)));

        g2.drawImage(images.get(0), null, 0, 0);

        while(currY < width) {
            if (i == 30) {
                i = 1;
            }
            //System.out.println("IN HERE");
            //System.out.println("height is "+ height);
            //System.out.println("width is " + width);

            if (currX < width && currY < height) {
            		//System.out.println("SIZE OF IMAGES: "+images.size());
            		//System.out.println("I IS: "+i);
                g2.drawImage(images.get(i), null, currX, currY);
            }

            currX += 25;
            // if going out of bounds to the right
            if (currX > width) {
                // shift down
                currX = 0;
                currY += 25;
            }
            ++i;
        }
        g2.dispose();
        return newImage;
    }

	// Generate a vector of random integers between -45 and 45 inclusive
	public Vector<Integer> generateDegrees(){
		Vector<Integer> degrees = new Vector<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 30; i++) {
			int num = rand.nextInt(91);
			num -= 45;
			degrees.add(num);
		}
		return degrees;
	}

    // TO-DO
    private BufferedImage buildShapedCollage(BufferedImage collage, String shape) {

        TexturedText t = new TexturedText(collage, shape);
        BufferedImage bi = new BufferedImage(collage.getWidth(),collage.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        t.paint(graphics);
        graphics.dispose();

//        JFrame f = t.getFrame();
//        f.setVisible(true);
//        BufferedImage bi = null;
//        try {
//            bi = ScreenImage.createImage(f);
//            Thread.sleep(2000);
//        } catch (AWTException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        try {
//            ImageIO.write(bi, "png", new File("abc.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        resizeCollage(bi, width, height);
        return bi;
    }
    // TO-DO
    private BufferedImage resizeCollage(BufferedImage collage, int height, int width) {
        Image temp = collage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return newImage;
    }
    // TO-DO
    private BufferedImage applyFilterToCollage(BufferedImage collage, Filter filter) {
    		int width = collage.getWidth();
    		int height = collage.getHeight();

    		//apply Sepia Filter
        if (filter.equals(Filter.SEPIA)) {
        		for(int y=0; y<height; y++) {
        			for(int x=0; x<width; x++) {
        				int p = collage.getRGB(x,y);
        				int a = (p>>24)&0xff;
        				int r = (p>>16)&0xff;
        				int g = (p>>8)&0xff;
        				int b = p&0xff;
        				int tr = (int)(0.393*r + 0.769*g + 0.189*b);
        				int tg = (int)(0.349*r + 0.686*g + 0.168*b);
        				int tb = (int)(0.272*r + 0.534*g + 0.131*b);
        				if(tr > 255){
        					r = 255;
        				}else{
        					r = tr;
        				}
        				if(tg > 255){
        					g = 255;
        				}else{
        					g = tg;
        				}
        				if(tb > 255){
        					b = 255;
        				}else{
        					b = tb;
        				}

        				p = (a<<24) | (r<<16) | (g<<8) | b;
        				collage.setRGB(x, y, p);
        			}
        		}
        }

        //apply black and white filter
        else if (filter.equals(Filter.BW)) {
        		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(collage, 0, 0, Color.WHITE, null);
            graphic.dispose();
            collage = result;
        }

        //apply grayscale filter
        else if (filter.equals(Filter.GRAYSCALE)) {
            for(int y = 0; y < height; y++){
              for(int x = 0; x < width; x++){
                int p = collage.getRGB(x,y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                //calculate average
                int avg = (r+g+b)/3;

                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                collage.setRGB(x, y, p);
              }
           }
        }
        return collage;
    }

    private CollageBuilder(Builder builder) {
        this.topic = builder.topic;
        this.shape = builder.shape;
        this.height = builder.height;
        this.width = builder.width;
        this.filter = builder.filter;
        this.rotation = builder.rotation;
        this.border = builder.border;
        this.testing = builder.testing;
    }

    public BufferedImage build() {
        List<BufferedImage> images = null;
        // If testing, mock images
        // Else search for images
        if (testing) {
            images = ImageService.getTestImages();
        } else {
            images = ImageService.getImages(topic);
        }
        if(images.size()>30) {
        		URL imageURL = null;
				try {
					imageURL = new URL("http://www.vishmax.com/en/innovattive-cms/themes/themax-theme-2015/images/no-image-found.gif");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		BufferedImage collage = null;
				try {
					collage = (BufferedImage) ImageIO.read(imageURL.openStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return collage;
        }

        // Apply border to images
        images = applyBordersToImages(images, border);
        // Rotate images
        images = rotateImages(images, rotation);


        // Create a single collage
        BufferedImage collage = buildSingleCollage(images, height, width,shape);

        // Add shape to collage
        collage = buildShapedCollage(collage, shape);

        /////////////////////////////////
//        JFrame frame = new JFrame();
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.getContentPane().add(new JLabel(new ImageIcon(collage)));
//        frame.pack();
//        frame.setVisible(true);
        ///////////////////////////////
        // Apply filter
        collage = applyFilterToCollage(collage, filter);

        return collage;
    }

    static class TexturedText extends JPanel {
        private BufferedImage bi;
        int height;
        int width;
        String shape;

        public TexturedText(BufferedImage bi, int height, int width) {
            this.bi = bi;
            this.height = height;
            this.width = width;
            this.shape = "test";
        }

        public TexturedText(BufferedImage bi, String shape) {
            this.bi = bi;
            this.height = bi.getHeight();
            this.width = bi.getWidth();
            this.shape = shape;
        }
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Font font = new Font("Impact", Font.PLAIN, 200);
            g2.setFont(font);
            Dimension d = getSize();
            float x = 20, y = 200;
            BufferedImage bi = getTextureImage();
            Rectangle r = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
            TexturePaint tp = new TexturePaint(bi, r);
            g2.setPaint(tp);
            g2.drawString(shape, x, y);


        }
        private BufferedImage getTextureImage() {
            // Create the test image.
            int size = 8;
            Graphics2D g2 = bi.createGraphics();
            return bi;
        }
        public JFrame getFrame() {
            JFrame f = new JFrame();
            f.getContentPane().add(new TexturedText(bi, shape));
            f.setSize(height, width);
            return f;
        }
    }
}

// Helper class to take screenshot
// https://tips4java.wordpress.com/2008/10/13/screen-image/
class ScreenImage
{
    private static List<String> types = Arrays.asList( ImageIO.getWriterFileSuffixes() );

    /*
     *  Create a BufferedImage for Swing components.
     *  The entire component will be captured to an image.
     *
     *  @param  component Swing component to create image from
     *  @return	image the image for the given region
     */
    public static BufferedImage createImage(JComponent component)
    {
        Dimension d = component.getSize();

        if (d.width == 0 || d.height == 0)
        {
            d = component.getPreferredSize();
            component.setSize( d );
        }

        Rectangle region = new Rectangle(0, 0, d.width, d.height);
        return ScreenImage.createImage(component, region);
    }

    /*
     *  Create a BufferedImage for Swing components.
     *  All or part of the component can be captured to an image.
     *
     *  @param  component Swing component to create image from
     *  @param  region The region of the component to be captured to an image
     *  @return	image the image for the given region
     */
    public static BufferedImage createImage(JComponent component, Rectangle region)
    {
        //  Make sure the component has a size and has been layed out.
        //  (necessary check for components not added to a realized frame)

        if (! component.isDisplayable())
        {
            Dimension d = component.getSize();

            if (d.width == 0 || d.height == 0)
            {
                d = component.getPreferredSize();
                component.setSize( d );
            }

            layoutComponent( component );
        }

        BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        //  Paint a background for non-opaque components,
        //  otherwise the background will be black
        if (! component.isOpaque())
        {
            g2d.setColor( component.getBackground() );
            g2d.fillRect(region.x, region.y, region.width, region.height);
        }

        g2d.translate(-region.x, -region.y);
        component.print( g2d );
        g2d.dispose();
        return image;
    }

    /**
     *  Convenience method to create a BufferedImage of the desktop
     *
     *  @param  fileName name of file to be created or null
     *  @return	image the image for the given region
     *  @exception AWTException see Robot class constructors
     *  @exception IOException if an error occurs during writing
     */
    public static BufferedImage createDesktopImage()
            throws AWTException, IOException
    {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle region = new Rectangle(0, 0, d.width, d.height);
        return ScreenImage.createImage(region);
    }

    /*
     *  Create a BufferedImage for AWT components.
     *  This will include Swing components JFrame, JDialog and JWindow
     *  which all extend from Component, not JComponent.
     *
     *  @param  component AWT component to create image from
     *  @return	image the image for the given region
     *  @exception AWTException see Robot class constructors
     */
    public static BufferedImage createImage(Component component)
            throws AWTException
    {
        Point p = new Point(0, 0);
        SwingUtilities.convertPointToScreen(p, component);
        Rectangle region = component.getBounds();
        region.x = p.x;
        region.y = p.y;
        return ScreenImage.createImage(region);
    }

    /**
     *  Create a BufferedImage from a rectangular region on the screen.
     *
     *  @param	 region region on the screen to create image from
     *  @return	image the image for the given region
     *  @exception AWTException see Robot class constructors
     */
    public static BufferedImage createImage(Rectangle region)
            throws AWTException
    {
        BufferedImage image = new Robot().createScreenCapture( region );
        return image;
    }

    /**
     *  Write a BufferedImage to a File.
     *
     *  @param	 image image to be written
     *  @param	 fileName name of file to be created
     *  @exception IOException if an error occurs during writing
     */
    public static void writeImage(BufferedImage image, String fileName)
            throws IOException
    {
        if (fileName == null) return;

        int offset = fileName.lastIndexOf( "." );

        if (offset == -1)
        {
            String message = "file suffix was not specified";
            throw new IOException( message );
        }

        String type = fileName.substring(offset + 1);

        if (types.contains(type))
        {
            ImageIO.write(image, type, new File( fileName ));
        }
        else
        {
            String message = "unknown writer file suffix (" + type + ")";
            throw new IOException( message );
        }
    }

    static void layoutComponent(Component component)
    {
        synchronized (component.getTreeLock())
        {
            component.doLayout();

            if (component instanceof Container)
            {
                for (Component child : ((Container)component).getComponents())
                {
                    layoutComponent(child);
                }
            }
        }
    }


}
