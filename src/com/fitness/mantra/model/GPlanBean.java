package com.fitness.mantra.model;

public class GPlanBean {

	private int id;
	private String name, price, duration;

	public GPlanBean(int id, String name, String price, String duration) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.duration = duration;
	}

	public GPlanBean(String name, String price, String duration) {
		super();
		this.name = name;
		this.price = price;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
