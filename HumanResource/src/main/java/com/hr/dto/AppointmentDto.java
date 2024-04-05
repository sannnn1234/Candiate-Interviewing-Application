package com.hr.dto;

import java.util.Date;

public class AppointmentDto {

	private String text;

	private Date startDate;

	private Date endDate;

	private String videoLinkDetails;

	public AppointmentDto() {
		super();
	}

	public AppointmentDto(String text, Date startDate, Date endDate, String videoLinkDetails) {
		super();
		this.text = text;
		this.startDate = startDate;
		this.endDate = endDate;
		this.videoLinkDetails = videoLinkDetails;
	}

	@SuppressWarnings("deprecation")
	public AppointmentDto(String text, String startDate, String endDate, String videoLinkDetails) {
		super();
		this.text = text;
		this.startDate = new Date(startDate);
		this.endDate = new Date(endDate);
		this.videoLinkDetails = videoLinkDetails;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVideoLinkDetails() {
		return videoLinkDetails;
	}

	public void setVideoLinkDetails(String videoLinkDetails) {
		this.videoLinkDetails = videoLinkDetails;
	}

	@Override
	public String toString() {
		return "AppointmentDto [text=" + text + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", videoLinkDetails=" + videoLinkDetails + "]";
	}

}
