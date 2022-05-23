package com.fitness.mantra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.InquiryDao;
import com.fitness.mantra.model.InquiryBean;

/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/Inquiry")
public class InquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InquiryServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		String fname = request.getParameter("name");
		String lname = request.getParameter("contact");
		String DOB = request.getParameter("email");
		String adharno = request.getParameter("mesg");
		InquiryBean inquirybean = new InquiryBean(fname, lname, DOB, adharno);
		InquiryDao inquirydao = new InquiryDao();
		String result = inquirydao.insert(inquirybean);
		response.getWriter().print(result);
	}

}
