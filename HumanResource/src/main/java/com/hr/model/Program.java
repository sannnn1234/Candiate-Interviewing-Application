package com.hr.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "program_master")
public class Program {

	@Id
	@Column(name = "program_code")
	Integer programCode;
	@Column(name = "program_description")
	String programDescription;
	@Column(name = "program_name")
	String programName;
	@Column(name = "program_print")
	Integer programPrint;
	@Column(name = "program_order")
	Integer programOrder;
	@Column(name = "created_by")
	String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	Date createdDate;
	@Column(name = "modified_by")
	String modifiedBy;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_Date")
	Date modifiedDate;
	
	@Transient
	List<Program> programLIst = new ArrayList<>();
	
	public Program() {
		super();
		
	}

	public Program(Integer programCode, String programDescription, String programName, Integer programPrint,
			Integer programOrder, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate,
			List<Program> programLIst) {
		super();
		this.programCode = programCode;
		this.programDescription = programDescription;
		this.programName = programName;
		this.programPrint = programPrint;
		this.programOrder = programOrder;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.programLIst = programLIst;
	}

	public Integer getProgramCode() {
		return programCode;
	}

	public void setProgramCode(Integer programCode) {
		this.programCode = programCode;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getProgramPrint() {
		return programPrint;
	}

	public void setProgramPrint(Integer programPrint) {
		this.programPrint = programPrint;
	}

	public Integer getProgramOrder() {
		return programOrder;
	}

	public void setProgramOrder(Integer programOrder) {
		this.programOrder = programOrder;
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

	public List<Program> getProgramLIst() {
		return programLIst;
	}

	public void setProgramLIst(List<Program> programLIst) {
		this.programLIst = programLIst;
	}

	@Override
	public String toString() {
		return "Program [programCode=" + programCode + ", programDescription=" + programDescription + ", programName="
				+ programName + ", programPrint=" + programPrint + ", programOrder=" + programOrder + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", programLIst=" + programLIst + "]";
	}

	
	
	
	
}
