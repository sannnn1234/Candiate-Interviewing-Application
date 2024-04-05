package com.hr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "department_rounds_information")
@EntityListeners(AuditingEntityListener.class)
public class department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemNo;
	private int departmentId;
	private int profileId;
	private String roundNo;
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

	@OneToMany()
	@JoinTable(name = "department_round_mapping", joinColumns = @JoinColumn(name = "itemNo"), inverseJoinColumns = @JoinColumn(name = "roundId"))
	private List<Round> round;
	private String interviewLengthMins;
	
	private String active;
	
	public department() {
		super();
		
	}

	public department(Long itemNo, int departmentId, int profileId, String roundNo, Long createdBy, Date createdTime,
			Long modifiedBy, Date modifiedTime, List<Round> round, String interviewLengthMins, String active) {
		super();
		this.itemNo = itemNo;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.roundNo = roundNo;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
		this.round = round;
		this.interviewLengthMins = interviewLengthMins;
		this.active = active;
	}

	public Long getItemNo() {
		return itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
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

	public String getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(String roundNo) {
		this.roundNo = roundNo;
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

	public List<Round> getRound() {
		return round;
	}

	public void setRound(List<Round> round) {
		this.round = round;
	}

	public String getInterviewLengthMins() {
		return interviewLengthMins;
	}

	public void setInterviewLengthMins(String interviewLengthMins) {
		this.interviewLengthMins = interviewLengthMins;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "department [itemNo=" + itemNo + ", departmentId=" + departmentId + ", profileId=" + profileId
				+ ", roundNo=" + roundNo + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", modifiedBy="
				+ modifiedBy + ", modifiedTime=" + modifiedTime + ", round=" + round + ", interviewLengthMins="
				+ interviewLengthMins + ", active=" + active + "]";
	}
	
	
}
