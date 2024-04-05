package com.hr.dto;

public class CandidateDto {

    private String candidateUniqueNumber;
	private String candidateFullName;
	
	public CandidateDto() {
		super();

	}
	public CandidateDto(String candidateUniqueNumber, String candidateFullName) {
		super();
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
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
	@Override
	public String toString() {
		return "CandidateDto [candidateUniqueNumber=" + candidateUniqueNumber + ", candidateFullName="
				+ candidateFullName + "]";
	}
	
	
}
