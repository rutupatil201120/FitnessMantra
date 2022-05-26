package com.fitness.mantra.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitness.mantra.dao.UsersDao;
import com.fitness.mantra.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login", "/logout" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final UsersDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		this.userDao = new UsersDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("/logout".equals(request.getServletPath())) {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
		} else {
			redirectToHomePage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User(email, password);

		if (userDao.validate(user)) {
			session.setAttribute("user", user);
		}

		redirectToHomePage(request, response);
	}

	private void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			response.sendRedirect(user.isAdmin() ? "admin" : "user");
		} else {
			session.invalidate();
			response.sendRedirect("UserSignIn.html");
		}
	}

}