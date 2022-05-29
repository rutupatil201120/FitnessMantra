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

import com.fitness.mantra.dao.PaymentsDao;
import com.fitness.mantra.model.Payment;
import com.fitness.mantra.model.User;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the payment.
 */

@WebServlet(urlPatterns = { "/admin/payment-list", "/admin/update-payment" })
public class AdminPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PaymentsDao paymentsDao;

	public AdminPaymentServlet() {
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
			case "/admin/update-payment":
				updatePayment(request, response);
				break;
			case "/admin/payment-list":
				listPayment(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPayment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Payment> listPayment = paymentsDao.selectAllPayments();
		request.setAttribute("payments", listPayment);
		showAdminPage(request, response, "AdminPayments.jsp");
	}

	private void updatePayment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		Payment payment = new Payment(request);
		payment.setAdminId(user.getId());

		paymentsDao.updatePaymentStatus(payment);
		redirectToAdmin(request, response, "payment-list");
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