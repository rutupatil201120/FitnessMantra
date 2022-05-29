package com.fitness.mantra.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.constants.CommonConstants;
import com.fitness.mantra.dao.PaymentsDao;
import com.fitness.mantra.dao.PlansDao;
import com.fitness.mantra.dao.UsersDao;
import com.fitness.mantra.model.Payment;
import com.fitness.mantra.model.Plan;
import com.fitness.mantra.model.User;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 */

@WebServlet(urlPatterns = { "/user/", "/user/new", "/user/update", "/user/payments" })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final UsersDao usersDao;
	private final PlansDao plansDao;
	private final PaymentsDao paymentsDao;

	public UserServlet() {
		usersDao = new UsersDao();
		plansDao = new PlansDao();
		paymentsDao = new PaymentsDao();
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
			case "/user/payments":
				handlePayment(request, response);
				break;
			default:
				showUserPage(request, response, null);
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
			showUserPage(request, response, "UserDetails.jsp");
		}
	}

	private void handlePayment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("payments", paymentsDao.selectAllPayments(user.getId()));
		showUserPage(request, response, "PaymentList.jsp");
	}

	private boolean isPostRequest(HttpServletRequest request) {
		return "POST".equals(request.getMethod());
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		User user = new User(request);
		if (!usersDao.checkIfUserExist(user)) {
			usersDao.insertUser(user);
			addPaymentEntry(request, user);
			request.getSession().setAttribute("user", user);
			redirectToHome(request, response);
		} else {
			request.setAttribute("user", user);
			request.setAttribute("errorMessage", "Email id alreay exist.");
			request.setAttribute("errorField", "email");
			showUserPage(request, response, null);
		}
	}

	private void addPaymentEntry(HttpServletRequest request, User user) {
		Plan plan = plansDao.selectPlan(user.getPlanId());
		paymentsDao.insertPayment(new Payment(user.getId(), plan.getPrice()));
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		usersDao.updateUser(new User(request));
		redirectToHome(request, response);
	}

	private void showUserPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		if (page == null || "UserDetails.jsp".equals(page)) {
			request.setAttribute("genders", CommonConstants.GENDERS);
			request.setAttribute("timeSlots", CommonConstants.TIME_SLOTS);
			request.setAttribute("plans", plansDao.selectAllPlans());
		}

		if (request.getSession().getAttribute("user") != null) {
			request.setAttribute("user", request.getSession().getAttribute("user"));
		}

		request.setAttribute("page", page);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
		dispatcher.forward(request, response);
	}

	private void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/user/");
	}

}