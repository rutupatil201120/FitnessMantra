package com.fitness.mantra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fitness.mantra.dao.LoginDao;
import com.fitness.mantra.model.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	LoginBean loginBean = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		this.loginBean = new LoginBean();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("UserSignIn.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginDao loginDao = new LoginDao();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		loginBean.setUsername(username);
		loginBean.setPassword(password);

		if (loginDao.validate(loginBean)) {
			session.setAttribute("username", username);
			response.sendRedirect("UserHome.html");
		} else {
			session.invalidate();
			response.sendRedirect("UserSignIn.html");
		}

	}

}