package com.fitness.mantra.model;

public class InquiryBean {

	private String name,contact,email,mesg;
	
	public InquiryBean() {
		super();
	}

	public InquiryBean(String name, String contact, String email, String mesg) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.mesg = mesg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
