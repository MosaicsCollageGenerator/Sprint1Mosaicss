package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.model.Collage;
import main.java.repository.CollageRepository;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		String title = (String) session.getAttribute("topic");
		String src = (String) session.getAttribute("collage");
		String user_id = (String) session.getAttribute("userID");
		System.out.println("user id in saveservlet is: " +  user_id);
		//System.out.println("THIS IS SESSION: " +title + " " + src + " " + user_id);
		CollageRepository users = new CollageRepository();
		System.out.println("saving the collage");
		System.out.println(src);
		Collage c = new Collage(title,src,user_id);
		
		users.saveCollage(c);
		System.out.println("FINISHED SAVING");
		CollageRepository cr = new CollageRepository();
		ArrayList<String> collages = (ArrayList<String>) cr.getAllCollageFromUser(user_id);
		session.setAttribute("collages", collages);
		System.out.println(collages.get(0));
		request.getRequestDispatcher("/display.jsp").forward(request, response);
	}




}
