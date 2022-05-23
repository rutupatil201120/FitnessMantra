package com.fitness.mantra.model;

public class UInfoBean {

	private int id;
	private String fname, lname, DOB, adharno, age, gender, height, weight, timeSlot, medical, email, contact, address;

	public UInfoBean() {
		super();
	}

	public UInfoBean(String fname, String lname, String dOB, String adharno, String age, String gender, String height,
			String weight, String timeSlot, String medical, String email, String contact, String address) {
		super();
		this.fname = fname;
		this.lname = lname;
		DOB = dOB;
		this.adharno = adharno;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.timeSlot = timeSlot;
		this.medical = medical;
		this.email = email;
		this.contact = contact;
		this.address = address;
	}

	public UInfoBean(int id, String fname, String lname, String dOB, String adharno, String age, String gender,
			String height, String weight, String timeSlot, String medical, String email, String contact,
			String address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		DOB = dOB;
		this.adharno = adharno;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.timeSlot = timeSlot;
		this.medical = medical;
		this.email = email;
		this.contact = contact;
		this.address = address;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getMedical() {
		return medical;
	}

	public void setMedical(String medical) {
		this.medical = medical;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
