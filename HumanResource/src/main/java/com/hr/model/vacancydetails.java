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
@EntityListeners(AuditingEntityListener.class)
@Table(name="vacancy_details_master ")
public class vacancydetails {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "vacancy_id", nullable = false)
	private Long vacancyId;
	
	@Column(name = "department", nullable = false)
	private String department;
	
	@Column(name = "profile", nullable = false)
	private String profile;
	
	@Column(name = "position", nullable = false)
	private Long position;
	
	
	@Column(name = "experience", nullable = false)
	private String experience;
	
	@Column(name = "target", nullable = false)
	private Long target;
	
	@Column(name = "positions_filled", nullable = false)
	private Long positionsFilled;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@Column(name = "year", nullable = false)
	private String year;
	
	@Column(name = "max_salary", nullable = false)
	private Long maxSalary;

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

	public vacancydetails() {
		super();
		
	}
	
	

	public vacancydetails(Long vacancyId, String department, String profile, Long position, String experience,
			Long target, Long positionsFilled, String location, String year, Long maxSalary, Long createdBy,
			Date createdTime, Long modifiedBy, Date modifiedTime) {
		super();
		this.vacancyId = vacancyId;
		this.department = department;
		this.profile = profile;
		this.position = position;
		this.experience = experience;
		this.target = target;
		this.positionsFilled = positionsFilled;
		this.location = location;
		this.year = year;
		this.maxSalary = maxSalary;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}



	public Long getVacancyId() {
		return vacancyId;
	}

	public void setVacancyId(Long vacancyId) {
		this.vacancyId = vacancyId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public Long getPositionsFilled() {
		return positionsFilled;
	}

	public void setPositionsFilled(Long positionsFilled) {
		this.positionsFilled = positionsFilled;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Long maxSalary) {
		this.maxSalary = maxSalary;
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



	@Override
	public String toString() {
		return "vacancydetails [vacancyId=" + vacancyId + ", department=" + department + ", profile=" + profile
				+ ", position=" + position + ", experience=" + experience + ", target=" + target + ", positionsFilled="
				+ positionsFilled + ", location=" + location + ", year=" + year + ", maxSalary=" + maxSalary
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedTime=" + modifiedTime + "]";
	}


	

	
}
