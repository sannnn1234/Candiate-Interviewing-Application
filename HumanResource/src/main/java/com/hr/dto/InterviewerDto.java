package com.hr.dto;

public class InterviewerDto {
	
	private long empId;
	private String fullName;

	public InterviewerDto() {
		super();
	
	}

	public InterviewerDto(long empId, String fullName) {
		super();
		this.empId = empId;
		this.fullName = fullName;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "InterviewerDto [empId=" + empId + ", fullName=" + fullName + "]";
	}
}
