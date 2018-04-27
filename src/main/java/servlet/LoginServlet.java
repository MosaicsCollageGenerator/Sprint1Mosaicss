package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.model.Collage;
import main.java.model.User;
import main.java.repository.CollageRepository;
import main.java.repository.UserRepository;
import main.java.service.AuthenticationService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(username.equals("")) {
			session.setAttribute("errorMessage", "Please enter a username");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		UserRepository users = new UserRepository();
		User currentUser = users.findByUsername(username);
		
		//HttpSession session = request.getSession();
		if(currentUser != null) {
			if(AuthenticationService.checkPassword(currentUser, password)) {
				System.out.println("I AM SETTING ATTRIBUTES " + currentUser.getId());
				session.setAttribute("userID", currentUser.getId());
				session.setAttribute("username", currentUser.getUsername());
				session.setAttribute("errorMessage", "");
				CollageRepository collageRepo = new CollageRepository();
				List<Collage> collages = collageRepo.getAllCollageFromUser(currentUser.getId());
				List<String> collages_src = new ArrayList<>();
				List<String> titles = new ArrayList<>();
				for (Collage co: collages) {
					collages_src.add(co.getSrc());
					titles.add(co.getTitle());
				}
				System.out.println("I HAVE " + collages_src.size() + " COLLAGES SAVED in loginservlet");
				session.setAttribute("collages", collages_src);
				session.setAttribute("titles", titles);
				session.setAttribute("collages_classes", collages);
				request.getRequestDispatcher("/display.jsp").forward(request, response);
			} 
			else {
				session.setAttribute("errorMessage", "Incorrect Password");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} 
		else {
			session.setAttribute("errorMessage", "Username not Recognized");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}


}