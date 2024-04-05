package com.hr.model;

public class JWTRequest {

	String empId;
	String password;

	public JWTRequest() {
	}

	public JWTRequest(String empId, String password) {
		super();
		this.empId = empId;
		this.password = password;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
