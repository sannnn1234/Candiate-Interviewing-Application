package com.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agreement_details")
public class Agreement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agreementId;
	
	private String agreement;
	

	private String active;
	
	public Agreement() {
		super();
		
	}

	public Agreement(int agreementId, String agreement, String active) {
		super();
		this.agreementId = agreementId;
		this.agreement = agreement;
		this.active = active;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Agreement [agreementId=" + agreementId + ", agreement=" + agreement + ", active=" + active + "]";
	}

    
	
}
