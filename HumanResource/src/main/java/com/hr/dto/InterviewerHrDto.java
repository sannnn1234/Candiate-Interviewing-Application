package com.hr.dto;

public class InterviewerHrDto {

    private long createdBy;
	
	private String fullName;
	
	private String email;
	
	private Long empPhone;

	public InterviewerHrDto() {
		super();
		
	}

	public InterviewerHrDto(long createdBy, String fullName, String email, Long empPhone) {
		super();
		this.createdBy = createdBy;
		this.fullName = fullName;
		this.email = email;
		this.empPhone = empPhone;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(Long empPhone) {
		this.empPhone = empPhone;
	}

	@Override
	public String toString() {
		return "InterviewerHrDto [createdBy=" + createdBy + ", fullName=" + fullName + ", email=" + email
				+ ", empPhone=" + empPhone + "]";
	}
	
}
