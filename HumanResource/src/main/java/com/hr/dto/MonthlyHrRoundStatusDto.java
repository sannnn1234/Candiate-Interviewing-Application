package com.hr.dto;

import java.util.Date;

public class MonthlyHrRoundStatusDto {
	
	private String candidateUniqueNumber;
	private String candidateFullName;
	private Long createdBy;
	private Long interviewerEmployeeId;
	private String fullName;
	private Integer roundDetails;
	private String roundName;
	private Date roundDate;
	private String roundStatus;
	
	public MonthlyHrRoundStatusDto() {
		super();
		
	}

	public MonthlyHrRoundStatusDto(String candidateUniqueNumber, String candidateFullName, Long createdBy,
			Long interviewerEmployeeId, String fullName, Integer roundDetails, String roundName, Date roundDate,
			String roundStatus) {
		super();
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.createdBy = createdBy;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.fullName = fullName;
		this.roundDetails = roundDetails;
		this.roundName = roundName;
		this.roundDate = roundDate;
		this.roundStatus = roundStatus;
	}

	public String getCandidateUniqueNumber() {
		return candidateUniqueNumber;
	}

	public void setCandidateUniqueNumber(String candidateUniqueNumber) {
		this.candidateUniqueNumber = candidateUniqueNumber;
	}

	public String getCandidateFullName() {
		return candidateFullName;
	}

	public void setCandidateFullName(String candidateFullName) {
		this.candidateFullName = candidateFullName;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getInterviewerEmployeeId() {
		return interviewerEmployeeId;
	}

	public void setInterviewerEmployeeId(Long interviewerEmployeeId) {
		this.interviewerEmployeeId = interviewerEmployeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getRoundDetails() {
		return roundDetails;
	}

	public void setRoundDetails(Integer roundDetails) {
		this.roundDetails = roundDetails;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public Date getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	@Override
	public String toString() {
		return "MonthlyHrRoundStatusDto [candidateUniqueNumber=" + candidateUniqueNumber + ", candidateFullName="
				+ candidateFullName + ", createdBy=" + createdBy + ", interviewerEmployeeId=" + interviewerEmployeeId
				+ ", fullName=" + fullName + ", roundDetails=" + roundDetails + ", roundName=" + roundName
				+ ", roundDate=" + roundDate + ", roundStatus=" + roundStatus + "]";
	}

   
	
	

}
