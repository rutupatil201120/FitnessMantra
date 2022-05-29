package com.fitness.mantra.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

	private Integer id;
	private Integer userId;
	private Double amount;
	private boolean received;
	private String dueDate;
	private String paymentDate;
	private Integer adminId;
	private String description;
	private String userName;
	private String adminName;

	public Payment(HttpServletRequest request) {
		this.id = Integer.parseInt(request.getParameter("id"));
		this.received = Boolean.parseBoolean(request.getParameter("status"));
	}

	public Payment(Integer userId, Double amount) {

		this.userId = userId;
		this.amount = amount;
		this.description = "New Subscription (" + new SimpleDateFormat("MMM-yyyy").format(new Date()) + ")";

		if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) > 15) {
			this.amount = this.amount / 2;
		}

	}

	public String getStatus() {
		String status = "warning";
		if (received) {
			status = "success";
		} else if (LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(LocalDate.now())) {
			status = "danger";
		}
		return status;
	}

	public String getMessage() {
		String message = null;
		if (received) {
			message = "Received by " + adminName + " on " + paymentDate;
		} else {
			message = "Due on " + dueDate;
		}
		return message;
	}

	public boolean getRecieved() {
		return this.received;
	}

}
