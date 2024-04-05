package com.hr.dto;

import java.util.Date;

public class CandidateSelectedDto {
	
	private Long scheduledInterviewId;
	private String candidateUniqueNumber;
	private String candidateFullName;
	private Date roundDate;
	private Integer days;
	
	public CandidateSelectedDto() {
		super();
		
	}

	public CandidateSelectedDto(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			Date roundDate, Integer days) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.roundDate = roundDate;
		this.days = days;
	}

	public Long getScheduledInterviewId() {
		return scheduledInterviewId;
	}

	public void setScheduledInterviewId(Long scheduledInterviewId) {
		this.scheduledInterviewId = scheduledInterviewId;
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

	public Date getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "CandidateSelectedDto [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", roundDate=" + roundDate
				+ ", days=" + days + "]";
	}

	
}
