package com.hr.dto;

import java.util.Date;

public class ScheduleDto {
 
	private String candidateFullName;

	private Date roundDate;

	private String roundTime;
	
	private String videoLinkDetails;

	public ScheduleDto() {
		super();
		
	}

	public ScheduleDto(String candidateFullName, Date roundDate, String roundTime, String videoLinkDetails) {
		super();
		this.candidateFullName = candidateFullName;
		this.roundDate = roundDate;
		this.roundTime = roundTime;
		this.videoLinkDetails = videoLinkDetails;
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

	public String getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(String roundTime) {
		this.roundTime = roundTime;
	}

	public String getVideoLinkDetails() {
		return videoLinkDetails;
	}

	public void setVideoLinkDetails(String videoLinkDetails) {
		this.videoLinkDetails = videoLinkDetails;
	}

	@Override
	public String toString() {
		return "ScheduleDto [candidateFullName=" + candidateFullName + ", roundDate=" + roundDate + ", roundTime="
				+ roundTime + ", videoLinkDetails=" + videoLinkDetails + "]";
	}

	
	
	

}
