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
@Table(name="profile_master")
@EntityListeners(AuditingEntityListener.class)
public class profiledetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "profile_id")
	private int profileId;
	
	@Column(name = "profile")
	private String profile;
	
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

	public profiledetails() {
		super();
		
	}

	public profiledetails(int profileId, String profile, String active, Long createdBy, Date createdTime,
			Long modifiedBy, Date modifiedTime) {
		super();
		this.profileId = profileId;
		this.profile = profile;
		this.active = active;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
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
		return "profiledetails [profileId=" + profileId + ", profile=" + profile + ", active=" + active + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + "]";
	}

	


}
