package com.fitness.mantra.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.PlansDao;
import com.fitness.mantra.model.Plan;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the plan.
 */

@WebServlet(urlPatterns = { "/admin/plan-list", "/admin/new-plan", "/admin/update-plan", "/admin/delete-plan" })
public class AdminPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PlansDao planDao;

	public AdminPlanServlet() {
		planDao = new PlansDao();
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
			case "/admin/delete-plan":
				deletePlan(request, response);
				break;
			case "/admin/new-plan":
			case "/admin/update-plan":
				handleUpdate(request, response);
				break;
			case "/admin/plan-list":
				listPlan(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (isPostRequest(request)) {
			updatePlan(request, response);
		} else {
			showEditForm(request, response);
		}
	}

	private boolean isPostRequest(HttpServletRequest request) {
		return "POST".equals(request.getMethod());
	}

	private void listPlan(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Plan> listPlan = planDao.selectAllPlans();
		request.setAttribute("plans", listPlan);
		showAdminPage(request, response, "AdminPlanList.jsp");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Plan existingPlan = planDao.selectPlan(id);
			request.setAttribute("plan", existingPlan);
		}
		showAdminPage(request, response, "AdminPlanUpdate.jsp");

	}

	private void updatePlan(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		planDao.updatePlan(new Plan(request));
		redirectToAdmin(request, response, "plan-list");
	}

	private void deletePlan(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		planDao.deletePlan(id);
		redirectToAdmin(request, response, "plan-list");
	}

	private void showAdminPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.setAttribute("page", page);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
		dispatcher.forward(request, response);
	}

	private void redirectToAdmin(HttpServletRequest request, HttpServletResponse response, String page)
			throws IOException {
		response.sendRedirect(request.getContextPath() + "/admin/" + page);
	}
}