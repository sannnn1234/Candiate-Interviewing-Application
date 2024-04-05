package com.hr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.hr.dto.CandidateDto;




//@formatter:off
@NamedNativeQuery(name = "getCandidateList", query = "SELECT distinct s.candidate_unique_number, s.candidate_full_name "
		+ "FROM candidate_information_master s "
		+ "Left outer JOIN interview_scheduled_master i ON s.candidate_unique_number = i.candidate_unique_number "
		+ "WHERE (s.round_status = 'Created' "
		+ "OR (s.round_status = 'Rejected' AND i.created_time <= DATE_SUB(CURDATE(), INTERVAL 80 DAY)));", resultSetMapping = "candidateList", resultClass = CandidateDto.class)

@SqlResultSetMapping(name = "candidateList", classes = @ConstructorResult(targetClass = CandidateDto.class, columns = {
		@ColumnResult(name = "candidate_unique_number", type = String.class),
		@ColumnResult(name = "candidate_full_name", type = String.class),
	
}))


//@formatter:on

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="candidate_information_master")
@DynamicUpdate
public class candidate {

	private static final long serialVersionUID = 6832006422622219737L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //getting error just drop table and create
	@Column(name = "candidate_no", nullable = false)
	private Long candidateNo;
	
	
	@Column(name = "candidate_unique_number", nullable = false)
	private String candidateUniqueNumber;
	
	
	@Column(name = "candidate_full_name", nullable = false )
	private String candidateFullName;
	
	
	@Column(name = "candidate_image", nullable = false )
	private String candidateImage;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 @Column(name = "date_of_birth")
	 @Temporal(TemporalType.DATE)
	 private Date dateOfBirth;
	 
	@Column(name = "contact_number", nullable = false )
	private Long contactNumber;
	

	@Column(name = "email_address", nullable = false )
	private String emailAddress;
	
	
	 @Column(name = "profile_preference1",nullable = false )
	 private String profilePreference1;
	 
	 @Column(name = "profile_preference2")
	 private String profilePreference2;
	
	
	@Column(name = "total_experience")
	private Double totalExperience;
	

	@Column(name = "key_skills")
	private String keySkills;
	
	
	 @Column(name = "current_salary")
	 private Long currentSalary;
	 
	
	 @Column(name = "expected_salary")
	 private Long expectedSalary;
	
	
	 
	 @Column(name = "current_location",nullable = false )
	 private String currentLocation;
	 
	
	 @Column(name = "location_preference")
	 private String locationPreference;
	 
	 
	 @Column(name = "relevant_experience")
	 private Double relevantExperience;
	 
	 @Column(name = "notice_period")
	  private Integer noticePeriod;
		
	 
	 @Column(name = "candidate_resume",nullable = false )
	 private String candidateResume;

	 @Column(name = "round_status")
	 private String roundStatus;
	 
	 @Column(name = "joining_date")
	 @Temporal(TemporalType.DATE)
	 private Date joiningDate;
	 
	 @Column(name = "joining_feedback")
	 private String joiningFeedback;
	 
	 private String readyToNegotiate;
	 
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

	public candidate() {
		super();
		
	}

	public candidate(Long candidateNo, String candidateUniqueNumber, String candidateFullName, String candidateImage,
			Date dateOfBirth, Long contactNumber, String emailAddress, String profilePreference1,
			String profilePreference2, Double totalExperience, String keySkills, Long currentSalary,
			Long expectedSalary, String currentLocation, String locationPreference, Double relevantExperience,
			Integer noticePeriod, String candidateResume, String roundStatus, Date joiningDate, String joiningFeedback,
			String readyToNegotiate, Long createdBy, Date createdTime, Long modifiedBy, Date modifiedTime) {
		super();
		this.candidateNo = candidateNo;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.candidateImage = candidateImage;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
		this.profilePreference1 = profilePreference1;
		this.profilePreference2 = profilePreference2;
		this.totalExperience = totalExperience;
		this.keySkills = keySkills;
		this.currentSalary = currentSalary;
		this.expectedSalary = expectedSalary;
		this.currentLocation = currentLocation;
		this.locationPreference = locationPreference;
		this.relevantExperience = relevantExperience;
		this.noticePeriod = noticePeriod;
		this.candidateResume = candidateResume;
		this.roundStatus = roundStatus;
		this.joiningDate = joiningDate;
		this.joiningFeedback = joiningFeedback;
		this.readyToNegotiate = readyToNegotiate;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}

	public Long getCandidateNo() {
		return candidateNo;
	}

	public void setCandidateNo(Long candidateNo) {
		this.candidateNo = candidateNo;
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

	public String getCandidateImage() {
		return candidateImage;
	}

	public void setCandidateImage(String candidateImage) {
		this.candidateImage = candidateImage;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getProfilePreference1() {
		return profilePreference1;
	}

	public void setProfilePreference1(String profilePreference1) {
		this.profilePreference1 = profilePreference1;
	}

	public String getProfilePreference2() {
		return profilePreference2;
	}

	public void setProfilePreference2(String profilePreference2) {
		this.profilePreference2 = profilePreference2;
	}

	public Double getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	public Long getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(Long currentSalary) {
		this.currentSalary = currentSalary;
	}

	public Long getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(Long expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getLocationPreference() {
		return locationPreference;
	}

	public void setLocationPreference(String locationPreference) {
		this.locationPreference = locationPreference;
	}

	public Double getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(Double relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getCandidateResume() {
		return candidateResume;
	}

	public void setCandidateResume(String candidateResume) {
		this.candidateResume = candidateResume;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getJoiningFeedback() {
		return joiningFeedback;
	}

	public void setJoiningFeedback(String joiningFeedback) {
		this.joiningFeedback = joiningFeedback;
	}

	public String getReadyToNegotiate() {
		return readyToNegotiate;
	}

	public void setReadyToNegotiate(String readyToNegotiate) {
		this.readyToNegotiate = readyToNegotiate;
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
		return "candidate [candidateNo=" + candidateNo + ", candidateUniqueNumber=" + candidateUniqueNumber
				+ ", candidateFullName=" + candidateFullName + ", candidateImage=" + candidateImage + ", dateOfBirth="
				+ dateOfBirth + ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress
				+ ", profilePreference1=" + profilePreference1 + ", profilePreference2=" + profilePreference2
				+ ", totalExperience=" + totalExperience + ", keySkills=" + keySkills + ", currentSalary="
				+ currentSalary + ", expectedSalary=" + expectedSalary + ", currentLocation=" + currentLocation
				+ ", locationPreference=" + locationPreference + ", relevantExperience=" + relevantExperience
				+ ", noticePeriod=" + noticePeriod + ", candidateResume=" + candidateResume + ", roundStatus="
				+ roundStatus + ", joiningDate=" + joiningDate + ", joiningFeedback=" + joiningFeedback
				+ ", readyToNegotiate=" + readyToNegotiate + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}
	
}
