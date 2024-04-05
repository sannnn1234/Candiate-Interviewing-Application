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
@Table(name="department_master")
@EntityListeners(AuditingEntityListener.class)
public class departmentdetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "department_id")
	private int departmentId;
	
	@Column(name = "department")
	private String department;
	
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
	
	@Column(name = "active")
	private String active;

	public departmentdetails() {
		super();
		
	}

	public departmentdetails(int departmentId, String department, Long createdBy, Date createdTime, Long modifiedBy,
			Date modifiedTime, String active) {
		super();
		this.departmentId = departmentId;
		this.department = department;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
		this.active = active;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "departmentdetails [departmentId=" + departmentId + ", department=" + department + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + ", active=" + active + "]";
	}

	
	
}
