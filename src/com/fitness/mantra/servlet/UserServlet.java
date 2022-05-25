package com.fitness.mantra.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.UserDao;
import com.fitness.mantra.model.User;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 */

@WebServlet(urlPatterns = { "/user/", "/user/new", "/user/update" })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final UserDao userDao;

	public UserServlet() {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequests(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequests(request, response);
	}

	private void handleRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/user/new":
				handleNew(request, response);
				break;
			case "/user/update":
				handleUpdate(request, response);
				break;
			default:
				userHome(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void handleNew(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (isPostRequest(request)) {
			insertUser(request, response);
		} else {
			showUserPage(request, response, "UserDetails.jsp");
		}
	}

	private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (isPostRequest(request)) {
			updateUser(request, response);
		} else {
			showEditForm(request, response);
		}
	}

	private boolean isPostRequest(HttpServletRequest request) {
		return "POST".equals(request.getMethod());
	}

	private void userHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDao.selectUser(id);
		request.setAttribute("user", existingUser);
		showUserPage(request, response, "UserForm.jsp");
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		User user = getUser(request);
		userDao.insertUser(user);
		request.getSession().setAttribute("user", user);
		redirectToHome(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		userDao.updateUser(getUser(request));
		redirectToHome(request, response);
	}

	private void showUserPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.setAttribute("user", request.getSession().getAttribute("user"));
		request.setAttribute("page", page);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
		dispatcher.forward(request, response);
	}

	private void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/user/");
	}

	private User getUser(HttpServletRequest request) {

		int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("email");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		return new User(id, firstName, lastName, userName, email, password);
	}
}