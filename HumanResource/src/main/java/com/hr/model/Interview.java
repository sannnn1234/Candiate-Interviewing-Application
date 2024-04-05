package com.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="interview_information_master")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="interview_id")
	private int interviewId;

	@Column(name ="mode_no")
	private String modeNo;
	

	public Interview() {
		super();
	}


	public Interview(int interviewId, String modeNo) {
		super();
		this.interviewId = interviewId;
		this.modeNo = modeNo;
	}


	public int getInterviewId() {
		return interviewId;
	}


	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}


	public String getModeNo() {
		return modeNo;
	}


	public void setModeNo(String modeNo) {
		this.modeNo = modeNo;
	}


	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", modeNo=" + modeNo + "]";
	}

	
}