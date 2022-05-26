package com.fitness.mantra.model;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plan {

	private Integer id;
	private String name;
	private Integer price;

	public Plan(HttpServletRequest request) {
		this.id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
		this.name = request.getParameter("name");
		this.price = request.getParameter("price") != null ? Integer.parseInt(request.getParameter("price")) : null;
	}

}
