package main.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.model.User;
import main.java.repository.UserRepository;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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

		UserRepository users = new UserRepository();
		User currentUser = users.findByUsername(username);
		
		HttpSession session = request.getSession();
		if(currentUser != null) {
			session.setAttribute("registerError", "Username taken");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		} 
		User newUser = new User(Integer.toString(users.getNumUsers()),username,password);
		users.saveUser(newUser);
		session.setAttribute("userID", newUser.getId());
		session.setAttribute("registerError", "");
		session.setAttribute("errorMessage", "");
		request.getRequestDispatcher("/options.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

}
