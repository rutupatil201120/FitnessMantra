package com.fitness.mantra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.GPlanDao;
import com.fitness.mantra.model.GPlanBean;

/**
 * Servlet implementation class GPlanServlet
 */
@WebServlet("/GPlan")
public class GPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GPlanDao gplandao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GPlanServlet() {
		super();
		gplandao = new GPlanDao();
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
		String pname = request.getParameter("pname");
		String price = request.getParameter("price");
		String duration = request.getParameter("duration");

		GPlanBean gplanbean = new GPlanBean(pname, price, duration);
		response.getWriter().print(gplandao.insert(gplanbean));
	}

}
