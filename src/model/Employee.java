package model;

import java.util.Date;

public class Employee {
	private int id;
	private String name;
	private String position;
	private String phone;
	private Date startDate;

	// Constructor
	public Employee(int id, String name, String position, String phone, Date startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.startDate = startDate;
	}

	// Getters and Setters
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", phone=" + phone + ", startDate="
				+ startDate + "]";
	}
	
	
}
