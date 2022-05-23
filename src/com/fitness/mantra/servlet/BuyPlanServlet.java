package com.fitness.mantra.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.GPlanDao;
import com.fitness.mantra.dao.UserDao;
import com.fitness.mantra.model.GPlanBean;

/**
 * Servlet implementation class GPlanServlet
 */
@WebServlet("/BuyPlan")
public class BuyPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GPlanDao gplanDao;
	private final UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyPlanServlet() {
		super();
		gplanDao = new GPlanDao();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			throw new RuntimeException("No user found");
		} else if (request.getParameter("id") != null) {

		} else {
			List<GPlanBean> plans = gplanDao.selectAllPlans();
			request.setAttribute("plans", plans);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BuyPlan.jsp");
			dispatcher.forward(request, response);
		}
	}

}
