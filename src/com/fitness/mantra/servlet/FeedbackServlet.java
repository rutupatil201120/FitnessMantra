package com.fitness.mantra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.FeedbackDao;
import com.fitness.mantra.model.FeedbackBean;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/Feedback")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedbackServlet() {
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
		String DOB = request.getParameter("email");
		String adharno = request.getParameter("mesg");
		FeedbackBean feedbackbean = new FeedbackBean(fname, DOB, adharno);
		FeedbackDao feedbackdao = new FeedbackDao();
		String result = feedbackdao.insert(feedbackbean);
		response.getWriter().print(result);
	}

}
