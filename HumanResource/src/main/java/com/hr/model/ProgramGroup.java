package com.hr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "program_groupmapping")
public class ProgramGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prgm_id")
	private Integer prgmid;
	@Column(name = "group_code")
	private Long groupCode;
	@Column(name = "program_code")
	private Integer programCode;
	@Column(name = "created_by")
	private String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "modified_by")
	private String modifiedBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	public ProgramGroup() {
		super();
		
	}

	public ProgramGroup(Integer prgmid, Long groupCode, Integer programCode, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate) {
		super();
		this.prgmid = prgmid;
		this.groupCode = groupCode;
		this.programCode = programCode;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public Integer getPrgmid() {
		return prgmid;
	}

	public void setPrgmid(Integer prgmid) {
		this.prgmid = prgmid;
	}

	public Long getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Long groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getProgramCode() {
		return programCode;
	}

	public void setProgramCode(Integer programCode) {
		this.programCode = programCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ProgramGroup [prgmid=" + prgmid + ", groupCode=" + groupCode + ", programCode=" + programCode
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	
	
	
	
}
