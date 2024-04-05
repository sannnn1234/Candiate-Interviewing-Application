package com.hr.dto;

public class SelectionCriteriaDto {

	private long itemNo;
	private int departmentId;
	private String department;
	private int profileId;
	private String profile;
	private String roundNo;
	private String interviewLengthMins;
	public SelectionCriteriaDto() {
		super();

	}
	public SelectionCriteriaDto(long itemNo, int departmentId, String department, int profileId, String profile,
			String roundNo, String interviewLengthMins) {
		super();
		this.itemNo = itemNo;
		this.departmentId = departmentId;
		this.department = department;
		this.profileId = profileId;
		this.profile = profile;
		this.roundNo = roundNo;
		this.interviewLengthMins = interviewLengthMins;
	}
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(String roundNo) {
		this.roundNo = roundNo;
	}
	public String getInterviewLengthMins() {
		return interviewLengthMins;
	}
	public void setInterviewLengthMins(String interviewLengthMins) {
		this.interviewLengthMins = interviewLengthMins;
	}
	@Override
	public String toString() {
		return "SelectionCriteria [itemNo=" + itemNo + ", departmentId=" + departmentId + ", department=" + department
				+ ", profileId=" + profileId + ", profile=" + profile + ", roundNo=" + roundNo
				+ ", interviewLengthMins=" + interviewLengthMins + "]";
	}
	
	
	
	
}
