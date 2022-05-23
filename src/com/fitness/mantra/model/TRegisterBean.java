package com.fitness.mantra.model;

public class TRegisterBean {

	private String fname, lname, DOB, adharno, address, gender, agree, email, contact, uname, password;

	public TRegisterBean() {
		super();
	}

	public TRegisterBean(String fname, String lname, String dOB, String adharno, String address, String gender,
			String agree, String email, String contact, String uname, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.DOB = dOB;
		this.adharno = adharno;
		this.address = address;
		this.gender = gender;
		this.agree = agree;
		this.email = email;
		this.contact = contact;
		this.uname = uname;
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getAdharno() {
		return adharno;
	}

	public void setAdharno(String adharno) {
		this.adharno = adharno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
