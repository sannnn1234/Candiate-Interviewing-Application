package com.hr.dto;

import java.util.Date;



public class InterviewScheduleDto {

	private Long scheduledInterviewId;
	private String candidateUniqueNumber;
	private String candidateFullName;
	private int departmentId;
	private int profileId;
	private String modeNo;
	private String videoLinkDetails;
	private int roundDetails;
	private Date roundDate;
	private String roundTime;
	private String constructiveFeedback;
	private String candidateResume;
	private String roundStatus;
	private String emailAddress;
	private String department;
	private String profile;
	private long   interviewerEmployeeId;
	private String fullName;
	private long   empPhone;
	private String email;
	
	public InterviewScheduleDto() {
		super();
		
	}

	public InterviewScheduleDto(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			int departmentId, int profileId, String modeNo, String videoLinkDetails, int roundDetails, Date roundDate,
			String roundTime, String constructiveFeedback, String candidateResume, String roundStatus,
			String emailAddress, String department, String profile, long interviewerEmployeeId, String fullName,
			long empPhone, String email) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.modeNo = modeNo;
		this.videoLinkDetails = videoLinkDetails;
		this.roundDetails = roundDetails;
		this.roundDate = roundDate;
		this.roundTime = roundTime;
		this.constructiveFeedback = constructiveFeedback;
		this.candidateResume = candidateResume;
		this.roundStatus = roundStatus;
		this.emailAddress = emailAddress;
		this.department = department;
		this.profile = profile;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.fullName = fullName;
		this.empPhone = empPhone;
		this.email = email;
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

	public String getConstructiveFeedback() {
		return constructiveFeedback;
	}

	public void setConstructiveFeedback(String constructiveFeedback) {
		this.constructiveFeedback = constructiveFeedback;
	}

	public String getCandidateResume() {
		return candidateResume;
	}

	public void setCandidateResume(String candidateResume) {
		this.candidateResume = candidateResume;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public long getInterviewerEmployeeId() {
		return interviewerEmployeeId;
	}

	public void setInterviewerEmployeeId(long interviewerEmployeeId) {
		this.interviewerEmployeeId = interviewerEmployeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public long getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(long empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "InterviewScheduleDto [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", departmentId=" + departmentId
				+ ", profileId=" + profileId + ", modeNo=" + modeNo + ", videoLinkDetails=" + videoLinkDetails
				+ ", roundDetails=" + roundDetails + ", roundDate=" + roundDate + ", roundTime=" + roundTime
				+ ", constructiveFeedback=" + constructiveFeedback + ", candidateResume=" + candidateResume
				+ ", roundStatus=" + roundStatus + ", emailAddress=" + emailAddress + ", department=" + department
				+ ", profile=" + profile + ", interviewerEmployeeId=" + interviewerEmployeeId + ", fullName=" + fullName
				+ ", empPhone=" + empPhone + ", email=" + email + "]";
	}
	
	
	
	
	
}
