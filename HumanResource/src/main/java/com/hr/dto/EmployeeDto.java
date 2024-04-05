package com.hr.dto;

public class EmployeeDto {
	
	private Long createdBy;
	private String fullName;
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDto(Long createdBy, String fullName) {
		super();
		this.createdBy = createdBy;
		this.fullName = fullName;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "EmployeeDto [createdBy=" + createdBy + ", fullName=" + fullName + "]";
	}
		
}
