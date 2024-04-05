package com.hr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="onboarding")
@EntityListeners(AuditingEntityListener.class)
public class onboarding {
	

		private static final long serialVersionUID = 6832006422622219737L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long onboardingId;
		private String candidateUniqueNumber;
		private String candidateFullName;
		private String experienceLevel;
		private Boolean allMarkSheets;
		private Boolean residenceProof;
		private Boolean photoIdProof;
		private Boolean passportPhotograph;
		private Boolean relievingLetterCurrentOrg;
		private Boolean lastThreeMonthsPayslips;
		private Boolean earlierJobRelievingLetter;
		private Boolean canceledCheque;
		
		

		@Column(name = "commentDocument")
		private String isSubmittedDocument;

		@Temporal(value = TemporalType.DATE)
		private Date allMarkSheetsDate;

		@Temporal(value = TemporalType.DATE)
		private Date residenceProofDate;

		@Temporal(value = TemporalType.DATE)
		private Date photoIdProofDate;

		@Temporal(value = TemporalType.DATE)
		private Date passportPhotographDate;

		@Temporal(value = TemporalType.DATE)
		private Date relievingLetterCurrentOrgDate;

		@Temporal(value = TemporalType.DATE)
		private Date lastThreeMonthsPayslipsDate;

		@Temporal(value = TemporalType.DATE)
		private Date earlierJobRelievingLetterDate;

		@Temporal(value = TemporalType.DATE)
		private Date canceledChequeDate;
		
		@CreatedBy
		private Long createdBy;

		@Temporal(TemporalType.TIMESTAMP)
		@CreatedDate
		private Date createdTime;

		@LastModifiedBy
		private Long modifiedBy;

		@LastModifiedDate
		@Temporal(TemporalType.TIMESTAMP)
		private Date modifiedTime;

		public onboarding() {
			super();
			
		}

		public onboarding(Long onboardingId, String candidateUniqueNumber, String candidateFullName,
				String experienceLevel, Boolean allMarkSheets, Boolean residenceProof, Boolean photoIdProof,
				Boolean passportPhotograph, Boolean relievingLetterCurrentOrg, Boolean lastThreeMonthsPayslips,
				Boolean earlierJobRelievingLetter, Boolean canceledCheque, String isSubmittedDocument,
				Date allMarkSheetsDate, Date residenceProofDate, Date photoIdProofDate, Date passportPhotographDate,
				Date relievingLetterCurrentOrgDate, Date lastThreeMonthsPayslipsDate,
				Date earlierJobRelievingLetterDate, Date canceledChequeDate, Long createdBy, Date createdTime,
				Long modifiedBy, Date modifiedTime) {
			super();
			this.onboardingId = onboardingId;
			this.candidateUniqueNumber = candidateUniqueNumber;
			this.candidateFullName = candidateFullName;
			this.experienceLevel = experienceLevel;
			this.allMarkSheets = allMarkSheets;
			this.residenceProof = residenceProof;
			this.photoIdProof = photoIdProof;
			this.passportPhotograph = passportPhotograph;
			this.relievingLetterCurrentOrg = relievingLetterCurrentOrg;
			this.lastThreeMonthsPayslips = lastThreeMonthsPayslips;
			this.earlierJobRelievingLetter = earlierJobRelievingLetter;
			this.canceledCheque = canceledCheque;
			this.isSubmittedDocument = isSubmittedDocument;
			this.allMarkSheetsDate = allMarkSheetsDate;
			this.residenceProofDate = residenceProofDate;
			this.photoIdProofDate = photoIdProofDate;
			this.passportPhotographDate = passportPhotographDate;
			this.relievingLetterCurrentOrgDate = relievingLetterCurrentOrgDate;
			this.lastThreeMonthsPayslipsDate = lastThreeMonthsPayslipsDate;
			this.earlierJobRelievingLetterDate = earlierJobRelievingLetterDate;
			this.canceledChequeDate = canceledChequeDate;
			this.createdBy = createdBy;
			this.createdTime = createdTime;
			this.modifiedBy = modifiedBy;
			this.modifiedTime = modifiedTime;
		}

		public Long getOnboardingId() {
			return onboardingId;
		}

		public void setOnboardingId(Long onboardingId) {
			this.onboardingId = onboardingId;
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

		public String getExperienceLevel() {
			return experienceLevel;
		}

		public void setExperienceLevel(String experienceLevel) {
			this.experienceLevel = experienceLevel;
		}

		public Boolean getAllMarkSheets() {
			return allMarkSheets;
		}

		public void setAllMarkSheets(Boolean allMarkSheets) {
			this.allMarkSheets = allMarkSheets;
		}

		public Boolean getResidenceProof() {
			return residenceProof;
		}

		public void setResidenceProof(Boolean residenceProof) {
			this.residenceProof = residenceProof;
		}

		public Boolean getPhotoIdProof() {
			return photoIdProof;
		}

		public void setPhotoIdProof(Boolean photoIdProof) {
			this.photoIdProof = photoIdProof;
		}

		public Boolean getPassportPhotograph() {
			return passportPhotograph;
		}

		public void setPassportPhotograph(Boolean passportPhotograph) {
			this.passportPhotograph = passportPhotograph;
		}

		public Boolean getRelievingLetterCurrentOrg() {
			return relievingLetterCurrentOrg;
		}

		public void setRelievingLetterCurrentOrg(Boolean relievingLetterCurrentOrg) {
			this.relievingLetterCurrentOrg = relievingLetterCurrentOrg;
		}

		public Boolean getLastThreeMonthsPayslips() {
			return lastThreeMonthsPayslips;
		}

		public void setLastThreeMonthsPayslips(Boolean lastThreeMonthsPayslips) {
			this.lastThreeMonthsPayslips = lastThreeMonthsPayslips;
		}

		public Boolean getEarlierJobRelievingLetter() {
			return earlierJobRelievingLetter;
		}

		public void setEarlierJobRelievingLetter(Boolean earlierJobRelievingLetter) {
			this.earlierJobRelievingLetter = earlierJobRelievingLetter;
		}

		public Boolean getCanceledCheque() {
			return canceledCheque;
		}

		public void setCanceledCheque(Boolean canceledCheque) {
			this.canceledCheque = canceledCheque;
		}

		public String getIsSubmittedDocument() {
			return isSubmittedDocument;
		}

		public void setIsSubmittedDocument(String isSubmittedDocument) {
			this.isSubmittedDocument = isSubmittedDocument;
		}

		public Date getAllMarkSheetsDate() {
			return allMarkSheetsDate;
		}

		public void setAllMarkSheetsDate(Date allMarkSheetsDate) {
			this.allMarkSheetsDate = allMarkSheetsDate;
		}

		public Date getResidenceProofDate() {
			return residenceProofDate;
		}

		public void setResidenceProofDate(Date residenceProofDate) {
			this.residenceProofDate = residenceProofDate;
		}

		public Date getPhotoIdProofDate() {
			return photoIdProofDate;
		}

		public void setPhotoIdProofDate(Date photoIdProofDate) {
			this.photoIdProofDate = photoIdProofDate;
		}

		public Date getPassportPhotographDate() {
			return passportPhotographDate;
		}

		public void setPassportPhotographDate(Date passportPhotographDate) {
			this.passportPhotographDate = passportPhotographDate;
		}

		public Date getRelievingLetterCurrentOrgDate() {
			return relievingLetterCurrentOrgDate;
		}

		public void setRelievingLetterCurrentOrgDate(Date relievingLetterCurrentOrgDate) {
			this.relievingLetterCurrentOrgDate = relievingLetterCurrentOrgDate;
		}

		public Date getLastThreeMonthsPayslipsDate() {
			return lastThreeMonthsPayslipsDate;
		}

		public void setLastThreeMonthsPayslipsDate(Date lastThreeMonthsPayslipsDate) {
			this.lastThreeMonthsPayslipsDate = lastThreeMonthsPayslipsDate;
		}

		public Date getEarlierJobRelievingLetterDate() {
			return earlierJobRelievingLetterDate;
		}

		public void setEarlierJobRelievingLetterDate(Date earlierJobRelievingLetterDate) {
			this.earlierJobRelievingLetterDate = earlierJobRelievingLetterDate;
		}

		public Date getCanceledChequeDate() {
			return canceledChequeDate;
		}

		public void setCanceledChequeDate(Date canceledChequeDate) {
			this.canceledChequeDate = canceledChequeDate;
		}

		public Long getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Long createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}

		public Long getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(Long modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public Date getModifiedTime() {
			return modifiedTime;
		}

		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "onboarding [onboardingId=" + onboardingId + ", candidateUniqueNumber=" + candidateUniqueNumber
					+ ", candidateFullName=" + candidateFullName + ", experienceLevel=" + experienceLevel
					+ ", allMarkSheets=" + allMarkSheets + ", residenceProof=" + residenceProof + ", photoIdProof="
					+ photoIdProof + ", passportPhotograph=" + passportPhotograph + ", relievingLetterCurrentOrg="
					+ relievingLetterCurrentOrg + ", lastThreeMonthsPayslips=" + lastThreeMonthsPayslips
					+ ", earlierJobRelievingLetter=" + earlierJobRelievingLetter + ", canceledCheque=" + canceledCheque
					+ ", isSubmittedDocument=" + isSubmittedDocument + ", allMarkSheetsDate=" + allMarkSheetsDate
					+ ", residenceProofDate=" + residenceProofDate + ", photoIdProofDate=" + photoIdProofDate
					+ ", passportPhotographDate=" + passportPhotographDate + ", relievingLetterCurrentOrgDate="
					+ relievingLetterCurrentOrgDate + ", lastThreeMonthsPayslipsDate=" + lastThreeMonthsPayslipsDate
					+ ", earlierJobRelievingLetterDate=" + earlierJobRelievingLetterDate + ", canceledChequeDate="
					+ canceledChequeDate + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", modifiedBy="
					+ modifiedBy + ", modifiedTime=" + modifiedTime + "]";
		}


}
