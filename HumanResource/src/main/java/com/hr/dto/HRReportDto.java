package com.hr.dto;

public class HRReportDto {
	
	private Long createdBy;
	private String fullName;
	private String roundStatus;
	private Long count;
	
	public HRReportDto() {
		super();
	
	}

	public HRReportDto(Long createdBy, String fullName, String roundStatus, Long count) {
		super();
		this.createdBy = createdBy;
		this.fullName = fullName;
		this.roundStatus = roundStatus;
		this.count = count;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "HRReportDto [createdBy=" + createdBy + ", fullName=" + fullName + ", roundStatus=" + roundStatus
				+ ", count=" + count + "]";
	}

	
	

}
