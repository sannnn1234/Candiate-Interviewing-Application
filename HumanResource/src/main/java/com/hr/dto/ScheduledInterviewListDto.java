package com.hr.dto;

import java.util.Date;

public class ScheduledInterviewListDto {
	private Long scheduledId;
	private String candidateFullName;
	private String candidateUniqueNumber;
	private int departmentId;
	private int profileId;
	private Long interviewerEmployeeId;
	private String interviewerName;
	private String modeNo;
	private String videoLinkDetails;
	private int roundDetails;
	private Date roundDate;
	private String roundTime;
	private String roundStatus;
	private String candidateResume;
	private String constructiveFeedback;
	private Long createdBy;
	private String scheduledCreatedByName;
	private Date createdTime;
	private String agreement;
	private Float communicationRating;
	private String readyToRelocate;
	private Double profileRelevance;
	private Long modifiedBy;
	private Date modifiedTime;
	private String icsFile;
	private Long candidateCreatedBy;
	private String candidateCreatedByName;
	
	public ScheduledInterviewListDto() {
		super();
		
	}

	public ScheduledInterviewListDto(Long scheduledId, String candidateFullName, String candidateUniqueNumber,
			int departmentId, int profileId, Long interviewerEmployeeId, String interviewerName, String modeNo,
			String videoLinkDetails, int roundDetails, Date roundDate, String roundTime, String roundStatus,
			String candidateResume, String constructiveFeedback, Long createdBy, String scheduledCreatedByName,
			Date createdTime, String agreement, Float communicationRating, String readyToRelocate,
			Double profileRelevance, Long modifiedBy, Date modifiedTime, String icsFile, Long candidateCreatedBy,
			String candidateCreatedByName) {
		super();
		this.scheduledId = scheduledId;
		this.candidateFullName = candidateFullName;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.interviewerName = interviewerName;
		this.modeNo = modeNo;
		this.videoLinkDetails = videoLinkDetails;
		this.roundDetails = roundDetails;
		this.roundDate = roundDate;
		this.roundTime = roundTime;
		this.roundStatus = roundStatus;
		this.candidateResume = candidateResume;
		this.constructiveFeedback = constructiveFeedback;
		this.createdBy = createdBy;
		this.scheduledCreatedByName = scheduledCreatedByName;
		this.createdTime = createdTime;
		this.agreement = agreement;
		this.communicationRating = communicationRating;
		this.readyToRelocate = readyToRelocate;
		this.profileRelevance = profileRelevance;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
		this.icsFile = icsFile;
		this.candidateCreatedBy = candidateCreatedBy;
		this.candidateCreatedByName = candidateCreatedByName;
	}

	public Long getScheduledId() {
		return scheduledId;
	}

	public void setScheduledId(Long scheduledId) {
		this.scheduledId = scheduledId;
	}

	public String getCandidateFullName() {
		return candidateFullName;
	}

	public void setCandidateFullName(String candidateFullName) {
		this.candidateFullName = candidateFullName;
	}

	public String getCandidateUniqueNumber() {
		return candidateUniqueNumber;
	}

	public void setCandidateUniqueNumber(String candidateUniqueNumber) {
		this.candidateUniqueNumber = candidateUniqueNumber;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public Long getInterviewerEmployeeId() {
		return interviewerEmployeeId;
	}

	public void setInterviewerEmployeeId(Long interviewerEmployeeId) {
		this.interviewerEmployeeId = interviewerEmployeeId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getModeNo() {
		return modeNo;
	}

	public void setModeNo(String modeNo) {
		this.modeNo = modeNo;
	}

	public String getVideoLinkDetails() {
		return videoLinkDetails;
	}

	public void setVideoLinkDetails(String videoLinkDetails) {
		this.videoLinkDetails = videoLinkDetails;
	}

	public int getRoundDetails() {
		return roundDetails;
	}

	public void setRoundDetails(int roundDetails) {
		this.roundDetails = roundDetails;
	}

	public Date getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public String getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(String roundTime) {
		this.roundTime = roundTime;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public String getCandidateResume() {
		return candidateResume;
	}

	public void setCandidateResume(String candidateResume) {
		this.candidateResume = candidateResume;
	}

	public String getConstructiveFeedback() {
		return constructiveFeedback;
	}

	public void setConstructiveFeedback(String constructiveFeedback) {
		this.constructiveFeedback = constructiveFeedback;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getScheduledCreatedByName() {
		return scheduledCreatedByName;
	}

	public void setScheduledCreatedByName(String scheduledCreatedByName) {
		this.scheduledCreatedByName = scheduledCreatedByName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public Float getCommunicationRating() {
		return communicationRating;
	}

	public void setCommunicationRating(Float communicationRating) {
		this.communicationRating = communicationRating;
	}

	public String getReadyToRelocate() {
		return readyToRelocate;
	}

	public void setReadyToRelocate(String readyToRelocate) {
		this.readyToRelocate = readyToRelocate;
	}

	public Double getProfileRelevance() {
		return profileRelevance;
	}

	public void setProfileRelevance(Double profileRelevance) {
		this.profileRelevance = profileRelevance;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getIcsFile() {
		return icsFile;
	}

	public void setIcsFile(String icsFile) {
		this.icsFile = icsFile;
	}

	public Long getCandidateCreatedBy() {
		return candidateCreatedBy;
	}

	public void setCandidateCreatedBy(Long candidateCreatedBy) {
		this.candidateCreatedBy = candidateCreatedBy;
	}

	public String getCandidateCreatedByName() {
		return candidateCreatedByName;
	}

	public void setCandidateCreatedByName(String candidateCreatedByName) {
		this.candidateCreatedByName = candidateCreatedByName;
	}

	@Override
	public String toString() {
		return "ScheduledInterviewListDto [scheduledId=" + scheduledId + ", candidateFullName=" + candidateFullName
				+ ", candidateUniqueNumber=" + candidateUniqueNumber + ", departmentId=" + departmentId + ", profileId="
				+ profileId + ", interviewerEmployeeId=" + interviewerEmployeeId + ", interviewerName="
				+ interviewerName + ", modeNo=" + modeNo + ", videoLinkDetails=" + videoLinkDetails + ", roundDetails="
				+ roundDetails + ", roundDate=" + roundDate + ", roundTime=" + roundTime + ", roundStatus="
				+ roundStatus + ", candidateResume=" + candidateResume + ", constructiveFeedback="
				+ constructiveFeedback + ", createdBy=" + createdBy + ", scheduledCreatedByName="
				+ scheduledCreatedByName + ", createdTime=" + createdTime + ", agreement=" + agreement
				+ ", communicationRating=" + communicationRating + ", readyToRelocate=" + readyToRelocate
				+ ", profileRelevance=" + profileRelevance + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + ", icsFile=" + icsFile + ", candidateCreatedBy=" + candidateCreatedBy
				+ ", candidateCreatedByName=" + candidateCreatedByName + "]";
	}
	
	

	
	
	

}
