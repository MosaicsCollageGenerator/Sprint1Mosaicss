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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		String title = (String) session.getAttribute("topic");
		String user_id = (String) session.getAttribute("userID");
		System.out.println("user id in saveservlet is: " +  user_id);
		System.out.println("title: " + title);
		//System.out.println("THIS IS SESSION: " +title + " " + src + " " + user_id);
		CollageRepository users = new CollageRepository();
		System.out.println("deleting the collage");
//		Collage c = new Collage(title,src,user_id);
//		public Collage(String id, String title, String src, String user_id) {
//	        this.id = id;
//	        this.title = title;
//	        this.src = src;
//	        this.user_id = user_id;
//	    }
		Collage c = users.findByIdAndTitle(user_id, title);
		System.out.println("IN DELETE SERVLET: COLLAGE IS " + c);
		System.out.println("collage is: "+ c.getUserId()+ " "+ c.getTitle() );
		users.deleteCollage(c);
		System.out.println("FINISHED Deleting");
		CollageRepository cr = new CollageRepository();
		ArrayList<Collage> collages = (ArrayList<Collage>) cr.getAllCollageFromUser(user_id);
		ArrayList<String> collages_src = new ArrayList<>();
		ArrayList<String> titles = new ArrayList<>();
		for (Collage co : collages) {
			collages_src.add(co.getSrc());
			titles.add(co.getTitle());
			System.out.println("titles are: "+ co.getTitle());
		}
		
		session.setAttribute("collages", collages_src);
		session.setAttribute("titles", titles);
		if(!collages_src.isEmpty()) {
			session.setAttribute("collage", collages_src.get(collages_src.size()-1));
			session.setAttribute("topic", titles.get(titles.size()-1));
			System.out.println("this is the topic: "+titles.get(titles.size()-1));
		}
		
		request.getRequestDispatcher("/display.jsp").forward(request, response);
	}


}
