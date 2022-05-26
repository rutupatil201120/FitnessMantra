package com.fitness.mantra.model;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String birthDate;
	private String gender;
	private String timeSlot;
	private Integer planId;
	private String password;
	private String isAdmin;

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(HttpServletRequest request) {
		this.id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
		this.firstName = request.getParameter("firstName");
		this.lastName = request.getParameter("lastName");
		this.contactNumber = request.getParameter("contactNumber");
		this.email = request.getParameter("email");
		this.birthDate = request.getParameter("birthDate");
		this.gender = request.getParameter("gender");
		this.timeSlot = request.getParameter("timeSlot");
		this.planId = request.getParameter("planId") != null ? Integer.parseInt(request.getParameter("planId")) : null;
		this.password = request.getParameter("password");
	}

	public boolean isAdmin() {
		return "Y".equalsIgnoreCase(isAdmin);
	}

}
