package com.hr.dto;

public class CandidateExperianceDto {

	
	 private String candidateUniqueNumber;
	 private String candidateFullName;
	 private Double totalExperience;
	public CandidateExperianceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CandidateExperianceDto(String candidateUniqueNumber, String candidateFullName, Double totalExperience) {
		super();
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.totalExperience = totalExperience;
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
	public Double getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}


	@Override
	public String toString() {
		return "CandidateExperianceDto [candidateUniqueNumber=" + candidateUniqueNumber + ", candidateFullName="
				+ candidateFullName + ", totalExperience=" + totalExperience + "]";
	}
	 
	 
	 
		
}
