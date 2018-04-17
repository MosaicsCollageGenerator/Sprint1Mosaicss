package main.java.servlet;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.java.builder.CollageBuilder;
import main.java.model.Collage;
import main.java.repository.CollageRepository;

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
		int height = Integer.parseInt(request.getParameter("heightvalue"));
		int width = Integer.parseInt(request.getParameter("widthvalue"));
		
		
		CollageBuilder.Filter filter; 
		int filterInt = Integer.parseInt(request.getParameter("filter"));
		if (filterInt == 0 ) {
			filter = CollageBuilder.Filter.None;
		}else if(filterInt == 1) {
			filter = CollageBuilder.Filter.SEPIA;
		}else if(filterInt == 2) {
			filter = CollageBuilder.Filter.BW;
		}else {
			filter = CollageBuilder.Filter.GRAYSCALE;
		}
		
		int rotation = 0;
		Boolean border = false;
		if(request.getParameter("rotation")==null) {
			rotation = 0;
		}
		else if(request.getParameter("rotation").contains("on")){
			rotation = 365;
		}
		
		if(request.getParameter("border")==null) {
			border = false;
		}
		else if(request.getParameter("border").contains("on")){
			border = true;
		}

		Boolean testing = false;

		//int filter = 1;
//		Boolean rotation = true;
//		Boolean border = true;
		
		
		
		
		CollageBuilder collageBuilder = new CollageBuilder.Builder(topic, shape,height,width,filter,rotation,border,testing).build();
		
		BufferedImage image = collageBuilder.build();

//		BufferedImage image = CollageBuilder.build(topic, shape, height, width, filter, rotation, border);
		
		
		
		
		System.out.println(image);
		String imageString = convertBufferedImageToBase64(image);
		HttpSession session = request.getSession();
		session.setAttribute("collage", imageString);
		session.setAttribute("topic", topic);
		
		
//		String user = request.getParameter("user_id");
//		CollageRepository cr = new CollageRepository();
//		ArrayList<String> collages = (ArrayList<String>) cr.getAllCollageFromUser(user);
//		session.setAttribute("collages", collages);
		
		
	
		
		
		
		
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

