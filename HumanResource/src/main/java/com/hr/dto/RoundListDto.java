package com.hr.dto;

public class RoundListDto {

   private int roundId;
   
   private String roundName;

	public RoundListDto() {
		super();
		
	}

	public RoundListDto(int roundId, String roundName) {
		super();
		this.roundId = roundId;
		this.roundName = roundName;
	}

	public int getRoundId() {
		return roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	@Override
	public String toString() {
		return "RoundListDto [roundId=" + roundId + ", roundName=" + roundName + "]";
	}	
}
