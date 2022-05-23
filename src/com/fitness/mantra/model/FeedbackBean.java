package com.fitness.mantra.model;

public class FeedbackBean {

	private String name,email,mesg;

	public FeedbackBean() {
		super();
	}

	public FeedbackBean(String name, String email, String mesg) {
		super();
		this.name = name;
		this.email = email;
		this.mesg = mesg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	
	
}
