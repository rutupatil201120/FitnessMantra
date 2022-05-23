package com.fitness.mantra.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.mantra.dao.TRegisterDao;
import com.fitness.mantra.model.TRegisterBean;

/**
 * Servlet implementation class TRegisterServlet
 */
@WebServlet("/TRegister")
public class TRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TRegisterServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String DOB = request.getParameter("DOB");
		String adharno = request.getParameter("adharno");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String agree = request.getParameter("agree");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");

		TRegisterBean tregisterbean = new TRegisterBean(fname, lname, DOB, adharno, address, gender, agree, email,
				contact, uname, password);
		TRegisterDao trdao = new TRegisterDao();
		String result = trdao.insert(tregisterbean);
		response.getWriter().print(result);

	}

}
