package com.hr.dto;



public class CandidateFeedbackList {
	
	private Long scheduledInterviewId;
	private String candidateUniqueNumber;
	private String candidateFullName;
	private int roundDetails;
	private String roundName;
	private String roundStatus;
	private String constructiveFeedback;
	private String joiningFeedback;
	
	public CandidateFeedbackList() {
		super();
		
	}

	public CandidateFeedbackList(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			int roundDetails, String roundName, String roundStatus, String constructiveFeedback,
			String joiningFeedback) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.roundDetails = roundDetails;
		this.roundName = roundName;
		this.roundStatus = roundStatus;
		this.constructiveFeedback = constructiveFeedback;
		this.joiningFeedback = joiningFeedback;
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

	public int getRoundDetails() {
		return roundDetails;
	}

	public void setRoundDetails(int roundDetails) {
		this.roundDetails = roundDetails;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public String getConstructiveFeedback() {
		return constructiveFeedback;
	}

	public void setConstructiveFeedback(String constructiveFeedback) {
		this.constructiveFeedback = constructiveFeedback;
	}

	public String getJoiningFeedback() {
		return joiningFeedback;
	}

	public void setJoiningFeedback(String joiningFeedback) {
		this.joiningFeedback = joiningFeedback;
	}

	@Override
	public String toString() {
		return "CandidateFeedbackList [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", roundDetails=" + roundDetails
				+ ", roundName=" + roundName + ", roundStatus=" + roundStatus + ", constructiveFeedback="
				+ constructiveFeedback + ", joiningFeedback=" + joiningFeedback + "]";
	}

   

}
