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
@Table(name="department_profile_master")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "departmentprofile_id")
	private Long departmentProfileId;
	
	
	@Column(name = "department_id")
	private int departmentId;
	
	@Column(name = "profile_id")
	private int profileId;
	
	@Column(name = "active")
	private String active;
	
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

	public DepartmentProfile() {
		super();
		
	}

	public DepartmentProfile(Long departmentProfileId, int departmentId, int profileId, String active, Long createdBy,
			Date createdTime, Long modifiedBy, Date modifiedTime) {
		super();
		this.departmentProfileId = departmentProfileId;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.active = active;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}

	public Long getDepartmentProfileId() {
		return departmentProfileId;
	}

	public void setDepartmentProfileId(Long departmentProfileId) {
		this.departmentProfileId = departmentProfileId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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
		return "DepartmentProfile [departmentProfileId=" + departmentProfileId + ", departmentId=" + departmentId
				+ ", profileId=" + profileId + ", active=" + active + ", createdBy=" + createdBy + ", createdTime="
				+ createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}

	
	
	
}
