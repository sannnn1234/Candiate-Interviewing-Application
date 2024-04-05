package com.hr.dto;

import java.util.Date;

public class FeedbackDto {

	private Long scheduledInterviewId;
	private String candidateUniqueNumber;
	private String candidateFullName;
	private Long interviewerEmployeeId;
	private String fullName;
	private Date roundDate;
	private int roundDetails;
	private int profileId;
	private String roundName;
	
	public FeedbackDto() {
		super();
		
	}

	public FeedbackDto(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			Long interviewerEmployeeId, String fullName, Date roundDate, int roundDetails, int profileId,
			String roundName) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.fullName = fullName;
		this.roundDate = roundDate;
		this.roundDetails = roundDetails;
		this.profileId = profileId;
		this.roundName = roundName;
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

	public Date getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public int getRoundDetails() {
		return roundDetails;
	}

	public void setRoundDetails(int roundDetails) {
		this.roundDetails = roundDetails;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	@Override
	public String toString() {
		return "FeedbackDto [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", interviewerEmployeeId="
				+ interviewerEmployeeId + ", fullName=" + fullName + ", roundDate=" + roundDate + ", roundDetails="
				+ roundDetails + ", profileId=" + profileId + ", roundName=" + roundName + "]";
	}

	
	
	
	

}
