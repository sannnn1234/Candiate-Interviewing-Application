package com.hr.dto;

public class InterviewerDeptDto {

	private long empId;
	private String fullName;
	private Integer profileId;

	
	public InterviewerDeptDto() {
		super();
		
	}


	public InterviewerDeptDto(long empId, String fullName, Integer profileId) {
		super();
		this.empId = empId;
		this.fullName = fullName;
		this.profileId = profileId;
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


	public Integer getProfileId() {
		return profileId;
	}


	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}


	@Override
	public String toString() {
		return "InterviewerDeptDto [empId=" + empId + ", fullName=" + fullName + ", profileId=" + profileId + "]";
	}

	
	
    
	
	

}
