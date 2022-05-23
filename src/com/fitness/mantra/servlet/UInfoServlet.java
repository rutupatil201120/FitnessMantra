package com.fitness.mantra.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.UserDao;
import com.fitness.mantra.model.UInfoBean;

/**
 * Servlet implementation class UInfoServlet
 */
@WebServlet("/UInfo")
public class UInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UInfoServlet() {
		super();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String DOB = request.getParameter("DOB");
		String adharno = request.getParameter("adharno");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String timeSlot = request.getParameter("timeSlot");
		String medical = request.getParameter("medical");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");

		UInfoBean uinfobean = new UInfoBean(fname, lname, DOB, adharno, age, gender, height, weight, timeSlot, medical,
				email, contact, address);

		if (userDao.insert(uinfobean)) {
			response.sendRedirect("BuyPlan");
		}
	}

}
