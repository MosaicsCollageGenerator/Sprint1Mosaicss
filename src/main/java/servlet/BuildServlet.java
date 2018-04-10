package main.java.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.builder.CollageBuilder;

/**
 * Servlet implementation class BuildServlet
 */
@WebServlet("/build")
public class BuildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("search_text");
		System.out.println(request.getParameter("heightvalue"));
		String shape = request.getParameter("shape_text");
//		int height = Integer.parseInt(request.getParameter("height_value"));
//		int width = Integer.parseInt(request.getParameter("width_value"));
//		int filter = Integer.parseInt(request.getParameter("filter"));
//		Boolean rotation = Boolean.parseBoolean(request.getParameter("rotation"));
//		Boolean border = Boolean.parseBoolean(request.getParameter("border"));
//		
		int height = 1;
		int width = 1;
		int filter = 1;
		Boolean rotation = true;
		Boolean border = true;
		CollageBuilder collageBuilder = new CollageBuilder.Builder(topic, shape).build();
		BufferedImage image = collageBuilder.build();
//		BufferedImage image = CollageBuilder.build(topic, shape, height, width, filter, rotation, border);
		System.out.println(image);
		String imageString = convertBufferedImageToBase64(image);
		HttpSession session = request.getSession();
		session.setAttribute("collage", imageString);
		session.setAttribute("topic", topic);
		request.getRequestDispatcher("/display.jsp").forward(request, response);
	}
	
	//converts BufferedImage paramater image into returned base64 string
		public String convertBufferedImageToBase64(BufferedImage image){
			System.out.println("=================>" + image);
			String type = "png";
			String imageString = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				// writes bufferedImage to a string
				ImageIO.write(image, type, Base64.getEncoder().wrap(bos));
				imageString = bos.toString();

				bos.close();
			} catch (IOException e) {
				System.out.println("IOException while encoding collage image as a string");
			}
			return imageString;
		}
	
}

