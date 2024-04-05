package com.hr.dto;



public class InterviewScheduleIdDto {

	private Long scheduledInterviewId;
	private String candidateUniqueNumber;
	private String candidateFullName;
	private Long contactNumber;
	private Long currentSalary;
	private Long expectedSalary;
	private Double relevantExperience;
	private Double totalExperience;
	private String emailAddress;
	private String department;
	private String profile;
	private long   interviewerEmployeeId;
	private String fullName;
	private long   empPhone;
	private String email;
	private String roundName;
	private Float communicationRating;
	private Integer noticePeriod;
	private String groupDescription;
	private String constructiveFeedback;
	private String readyToRelocate;
	private Double profileRelevance;
	
	public InterviewScheduleIdDto() {
		super();
		
	}

	public InterviewScheduleIdDto(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			Long contactNumber, Long currentSalary, Long expectedSalary, Double relevantExperience,
			Double totalExperience, String emailAddress, String department, String profile, long interviewerEmployeeId,
			String fullName, long empPhone, String email, String roundName, Float communicationRating,
			Integer noticePeriod, String groupDescription, String constructiveFeedback, String readyToRelocate,
			Double profileRelevance) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.contactNumber = contactNumber;
		this.currentSalary = currentSalary;
		this.expectedSalary = expectedSalary;
		this.relevantExperience = relevantExperience;
		this.totalExperience = totalExperience;
		this.emailAddress = emailAddress;
		this.department = department;
		this.profile = profile;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.fullName = fullName;
		this.empPhone = empPhone;
		this.email = email;
		this.roundName = roundName;
		this.communicationRating = communicationRating;
		this.noticePeriod = noticePeriod;
		this.groupDescription = groupDescription;
		this.constructiveFeedback = constructiveFeedback;
		this.readyToRelocate = readyToRelocate;
		this.profileRelevance = profileRelevance;
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

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(Long currentSalary) {
		this.currentSalary = currentSalary;
	}

	public Long getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(Long expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public Double getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(Double relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public Double getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
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

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public Float getCommunicationRating() {
		return communicationRating;
	}

	public void setCommunicationRating(Float communicationRating) {
		this.communicationRating = communicationRating;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getConstructiveFeedback() {
		return constructiveFeedback;
	}

	public void setConstructiveFeedback(String constructiveFeedback) {
		this.constructiveFeedback = constructiveFeedback;
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

	@Override
	public String toString() {
		return "InterviewScheduleIdDto [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", contactNumber="
				+ contactNumber + ", currentSalary=" + currentSalary + ", expectedSalary=" + expectedSalary
				+ ", relevantExperience=" + relevantExperience + ", totalExperience=" + totalExperience
				+ ", emailAddress=" + emailAddress + ", department=" + department + ", profile=" + profile
				+ ", interviewerEmployeeId=" + interviewerEmployeeId + ", fullName=" + fullName + ", empPhone="
				+ empPhone + ", email=" + email + ", roundName=" + roundName + ", communicationRating="
				+ communicationRating + ", noticePeriod=" + noticePeriod + ", groupDescription=" + groupDescription
				+ ", constructiveFeedback=" + constructiveFeedback + ", readyToRelocate=" + readyToRelocate
				+ ", profileRelevance=" + profileRelevance + "]";
	}

	

	
	
	
}
