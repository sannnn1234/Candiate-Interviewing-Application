package com.hr.dto;

public class roundPrevDto {

	
	private Integer roundDetails;
	private String  roundName;
	public roundPrevDto() {
		super();
	}
	public roundPrevDto(Integer roundDetails, String roundName) {
		super();
		this.roundDetails = roundDetails;
		this.roundName = roundName;
	}
	public Integer getRoundDetails() {
		return roundDetails;
	}
	public void setRoundDetails(Integer roundDetails) {
		this.roundDetails = roundDetails;
	}
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	@Override
	public String toString() {
		return "roundPrevDto [roundDetails=" + roundDetails + ", roundName=" + roundName + "]";
	}
	
	
	
	
}
