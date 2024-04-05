package com.hr.dto;

public class DepartmentProfileDto {

	private Long departmentProfileId;
	private Integer departmentId;
	private Integer profileId;
	private String department;
	private String profile;
	private String active;

	public DepartmentProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentProfileDto(Long departmentProfileId, Integer departmentId, Integer profileId, String department,
			String profile, String active) {
		super();
		this.departmentProfileId = departmentProfileId;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.department = department;
		this.profile = profile;
		this.active = active;
	}

	public Long getDepartmentProfileId() {
		return departmentProfileId;
	}

	public void setDepartmentProfileId(Long departmentProfileId) {
		this.departmentProfileId = departmentProfileId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DepartmentProfileDto [departmentProfileId=" + departmentProfileId + ", departmentId=" + departmentId
				+ ", profileId=" + profileId + ", department=" + department + ", profile=" + profile + ", active="
				+ active + "]";
	}
	
	
	

	

}
