package com.hr.model;

import java.text.SimpleDateFormat;
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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.hr.dto.CandidateSelectedDto;
import com.hr.dto.FeedbackDto;
import com.hr.dto.InterviewScheduleIdDto;
import com.hr.dto.InterviewerDeptDto;
import com.hr.dto.ScheduledInterviewListDto;

//@formatter:off
@NamedNativeQuery(name = "getCandidateSelectedList", query = "SELECT i.scheduled_id, i.candidate_unique_number, i.candidate_full_name, i.round_date, DATEDIFF(CURRENT_DATE(), i.round_date) AS days "
		+ "FROM interview_scheduled_master i "
		+ "INNER JOIN candidate_information_master c ON i.candidate_unique_number = c.candidate_unique_number "
		+ "WHERE DATEDIFF(CURRENT_DATE(), i.round_date) >= 10 "
		+ "AND i.round_status = 'Selected'  and c.joining_date is null and c.joining_feedback is null", resultSetMapping = "candidateSelected", resultClass = CandidateSelectedDto.class)

@SqlResultSetMapping(name = "candidateSelected", classes = @ConstructorResult(targetClass = CandidateSelectedDto.class, columns = {
		@ColumnResult(name = "scheduled_id", type = Long.class),
		@ColumnResult(name = "candidate_unique_number", type = String.class),
		@ColumnResult(name = "candidate_full_name", type = String.class),
		@ColumnResult(name = "round_date", type = Date.class), @ColumnResult(name = "days", type = Integer.class),

}))

@NamedNativeQuery(name = "getCandidateFeedbackList", query = "SELECT i.scheduled_id, i.candidate_unique_number, i.candidate_full_name, i.round_date, "
	            + "i.interviewer_employee_id, e.full_name, i.round_details, i.profile_id, r.round_name "
	            + "FROM interview_scheduled_master i "
	            + "INNER JOIN employee_master e ON i.interviewer_employee_id = e.emp_id "
	            + "INNER JOIN round_master r ON i.round_details = r.round_id "
	            + "WHERE DATEDIFF(CURRENT_DATE(), i.round_date) >= 5 "
	            + "AND i.round_status = 'Scheduled';", resultSetMapping = "candidateFeedback", resultClass = FeedbackDto.class)

	@SqlResultSetMapping(name = "candidateFeedback", classes = @ConstructorResult(targetClass = FeedbackDto.class,
	        columns = {
	            @ColumnResult(name = "scheduled_id", type = Long.class),
	            @ColumnResult(name = "candidate_unique_number", type = String.class),
	            @ColumnResult(name = "candidate_full_name", type = String.class),
	            @ColumnResult(name = "interviewer_employee_id", type = Long.class),
	            @ColumnResult(name = "full_name", type = String.class),
	            @ColumnResult(name = "round_date", type = Date.class),
	            @ColumnResult(name = "round_details", type = Integer.class),
	            @ColumnResult(name = "profile_id", type = Integer.class),
	            @ColumnResult(name = "round_name", type = String.class)
	        }))

@NamedNativeQuery(
	    name = "getInterviewscheduledDto",
	    query = "SELECT s.scheduled_id, s.candidate_unique_number, s.candidate_full_name, c.contact_number, c.email_address, c.current_salary, c.expected_salary, c.total_experience, d.department, p.profile, s.interviewer_employee_id, e.full_name, e.emp_phone, e.email, r.round_name, pr.communication_rating"
	        + "FROM interview_scheduled_master s "
	        + "INNER JOIN candidate_information_master c ON s.candidate_unique_number = c.candidate_unique_number "
	        + "INNER JOIN employee_master e ON s.interviewer_employee_id = e.emp_id "
	        + "INNER JOIN department_master d ON s.department_id = d.department_id "
	        + "INNER JOIN profile_master p ON s.profile_id = p.profile_id "
	        + "INNER JOIN round_master r ON s.round_details = r.round_id "
	        + "LEFT JOIN interview_scheduled_master pr ON s.candidate_unique_number = pr.candidate_unique_number "
	        + "WHERE s.scheduled_id > pr.scheduled_id AND s.scheduled_id = :scheduled_id "
	        + "ORDER BY pr.scheduled_id DESC "
	        + "LIMIT 1",
	    resultSetMapping = "interviewScheduledDto",
	    resultClass = InterviewScheduleIdDto.class
	)

	@SqlResultSetMapping(
	    name = "interviewScheduledDto",
	    classes = @ConstructorResult(
	        targetClass = InterviewScheduleIdDto.class,
	        columns = {
	            @ColumnResult(name = "scheduled_id", type = Long.class),
	            @ColumnResult(name = "candidate_unique_number", type = String.class),
	            @ColumnResult(name = "candidate_full_name", type = String.class),
	            @ColumnResult(name = "contact_number", type = Long.class),
	            @ColumnResult(name = "email_address", type = String.class),
	            @ColumnResult(name = "current_salary", type = Long.class),
	            @ColumnResult(name = "expected_salary", type = Long.class),
	            @ColumnResult(name = "total_experience", type = Double.class),
	            @ColumnResult(name = "department", type = String.class),
	            @ColumnResult(name = "profile", type = String.class),
	            @ColumnResult(name = "interviewer_employee_id", type = Long.class),
	            @ColumnResult(name = "full_name", type = String.class),
	            @ColumnResult(name = "emp_phone", type = Long.class),
	            @ColumnResult(name = "email", type = String.class),
	            @ColumnResult(name = "round_name", type = String.class),
	            @ColumnResult(name = "communication_rating", type = Integer.class)
	        }
	    )
	)

@NamedNativeQuery(
	    name = "getInterviewerName",
	    query = "SELECT DISTINCT e.emp_id, e.full_name, a.profile_id FROM employee_master e INNER JOIN employee_profile_mapping a "
	            + "ON e.emp_id = a.emp_id "
	            + "WHERE a.profile_id = :profileId And e.active = 'Y' ", resultSetMapping = "interviewerName",  resultClass = InterviewerDeptDto.class
	)

	@SqlResultSetMapping(
	    name = "interviewerName",
	    classes = @ConstructorResult(
	        targetClass = InterviewerDeptDto.class,
	        columns = {
	            @ColumnResult(name = "emp_id", type = Long.class),
	            @ColumnResult(name = "full_name", type = String.class),
	            @ColumnResult(name = "profile_id", type = Integer.class),
	        }
	    )
	)




@NamedNativeQuery(
	    name = "getScheduledInterviewList",
	    query = "SELECT " +
	            "i.scheduled_id, i.candidate_full_name, i.candidate_unique_number, i.department_id, i.profile_id, " +
	            "i.interviewer_employee_id, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = i.interviewer_employee_id) AS interviewerName, " +
	            "i.mode_no, i.video_link_details, i.round_details, i.round_date, i.round_time, i.round_status, i.candidate_resume, " +
	            "i.constructive_feedback, i.created_by, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = i.created_by) AS scheduledCreatedByName, " +
	            "i.created_time, i.agreement, i.communication_rating, i.ready_to_relocate, i.profile_relevance, " +
	            "i.modified_by, i.modified_time, i.ics_file, " +
	            "(c.created_by) AS candidateCreatedBy, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = c.created_by) AS candidateCreatedByName " +
	            "FROM " +
	            "interview_scheduled_master i, candidate_information_master c " +
	            "WHERE " +
	            "c.candidate_unique_number = i.candidate_unique_number " +
	            "AND i.round_status IN ('Scheduled', 'On-hold')",
	    resultSetMapping = "ScheduledInterviewList",
	    resultClass = ScheduledInterviewListDto.class
	)
@SqlResultSetMapping(
	    name = "ScheduledInterviewList",
	    classes = @ConstructorResult(
	        targetClass = ScheduledInterviewListDto.class,
	        columns = {
	            @ColumnResult(name = "scheduled_id", type = Long.class),
	            @ColumnResult(name = "candidate_full_name", type = String.class),
	            @ColumnResult(name = "candidate_unique_number", type = String.class),
	            @ColumnResult(name = "department_id", type = Integer.class),
	            @ColumnResult(name = "profile_id", type = Integer.class),
	            @ColumnResult(name = "interviewer_employee_id", type = Long.class),
	            @ColumnResult(name = "interviewerName", type = String.class),
	            @ColumnResult(name = "mode_no", type = String.class),
	            @ColumnResult(name = "video_link_details", type = String.class),
	            @ColumnResult(name = "round_details", type = Integer.class),
	            @ColumnResult(name = "round_date", type = Date.class),
	            @ColumnResult(name = "round_time", type = String.class),
	            @ColumnResult(name = "round_status", type = String.class),
	            @ColumnResult(name = "candidate_resume", type = String.class),
	            @ColumnResult(name = "constructive_feedback", type = String.class),
	            @ColumnResult(name = "created_by", type = Long.class),
	            @ColumnResult(name = "scheduledCreatedByName", type = String.class),
	            @ColumnResult(name = "created_time", type = Date.class),
	            @ColumnResult(name = "agreement", type = String.class),
	            @ColumnResult(name = "communication_rating", type = Float.class),
	            @ColumnResult(name = "ready_to_relocate", type = String.class),
	            @ColumnResult(name = "profile_relevance", type = Double.class),
	            @ColumnResult(name = "modified_by", type = Long.class),
	            @ColumnResult(name = "modified_time", type = Date.class),
	            @ColumnResult(name = "ics_file", type = String.class),
	            @ColumnResult(name = "candidateCreatedBy", type = Long.class),
	            @ColumnResult(name = "candidateCreatedByName", type = String.class)
	        }
	    )
	)


@NamedNativeQuery(
	    name = "getScheduledInterviewBasedOnInterviewId",
	    query = "SELECT " +
	            "i.scheduled_id, i.candidate_full_name, i.candidate_unique_number, i.department_id, i.profile_id, " +
	            "i.interviewer_employee_id, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = i.interviewer_employee_id) AS interviewerName, " +
	            "i.mode_no, i.video_link_details, i.round_details, i.round_date, i.round_time, i.round_status, i.candidate_resume, " +
	            "i.constructive_feedback, i.created_by, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = i.created_by) AS scheduledCreatedByName, " +
	            "i.created_time, i.agreement, i.communication_rating, i.ready_to_relocate, i.profile_relevance, " +
	            "i.modified_by, i.modified_time, i.ics_file, " +
	            "(c.created_by) AS candidateCreatedBy, " +
	            "(SELECT em.full_name FROM employee_master em WHERE em.emp_id = c.created_by) AS candidateCreatedByName " +
	            "FROM " +
	            "interview_scheduled_master i, candidate_information_master c " +
	            "WHERE " +
	            "c.candidate_unique_number = i.candidate_unique_number " +
	            "And i.interviewer_employee_id =:interviewerEmployeeId " +
	            "AND i.round_status IN ('Scheduled', 'On-hold')",
	    resultSetMapping = "ScheduledInterviewBasedOnInterviewId",
	    resultClass = ScheduledInterviewListDto.class
	)
@SqlResultSetMapping(
	    name = "ScheduledInterviewBasedOnInterviewId",
	    classes = @ConstructorResult(
	        targetClass = ScheduledInterviewListDto.class,
	        columns = {
	            @ColumnResult(name = "scheduled_id", type = Long.class),
	            @ColumnResult(name = "candidate_full_name", type = String.class),
	            @ColumnResult(name = "candidate_unique_number", type = String.class),
	            @ColumnResult(name = "department_id", type = Integer.class),
	            @ColumnResult(name = "profile_id", type = Integer.class),
	            @ColumnResult(name = "interviewer_employee_id", type = Long.class),
	            @ColumnResult(name = "interviewerName", type = String.class),
	            @ColumnResult(name = "mode_no", type = String.class),
	            @ColumnResult(name = "video_link_details", type = String.class),
	            @ColumnResult(name = "round_details", type = Integer.class),
	            @ColumnResult(name = "round_date", type = Date.class),
	            @ColumnResult(name = "round_time", type = String.class),
	            @ColumnResult(name = "round_status", type = String.class),
	            @ColumnResult(name = "candidate_resume", type = String.class),
	            @ColumnResult(name = "constructive_feedback", type = String.class),
	            @ColumnResult(name = "created_by", type = Long.class),
	            @ColumnResult(name = "scheduledCreatedByName", type = String.class),
	            @ColumnResult(name = "created_time", type = Date.class),
	            @ColumnResult(name = "agreement", type = String.class),
	            @ColumnResult(name = "communication_rating", type = Float.class),
	            @ColumnResult(name = "ready_to_relocate", type = String.class),
	            @ColumnResult(name = "profile_relevance", type = Double.class),
	            @ColumnResult(name = "modified_by", type = Long.class),
	            @ColumnResult(name = "modified_time", type = Date.class),
	            @ColumnResult(name = "ics_file", type = String.class),
	            @ColumnResult(name = "candidateCreatedBy", type = Long.class),
	            @ColumnResult(name = "candidateCreatedByName", type = String.class)
	        }
	    )
	)
//@formatter:on
@Entity
@Table(name = "interview_scheduled_master")
@EntityListeners(AuditingEntityListener.class)
public class ScheduledInterview {

	private static final long serialVersionUID = 6832006422622219737L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scheduled_id")
	private Long scheduledInterviewId;

	@Column(name = "candidate_unique_number")
	private String candidateUniqueNumber;

	@Column(name = "candidate_full_name")
	private String candidateFullName;

	@Column(name = "department_id")
	private int departmentId;

	@Column(name = "profile_id")
	private int profileId;

	@Column(name = "interviewer_employee_id")
	private Long interviewerEmployeeId;

	@Column(name = "mode_no")
	private String modeNo;

	@Column(name = "Video_link_details")
	private String videoLinkDetails;

	@Column(name = "round_details")
	private int roundDetails;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "round_date")
	private Date roundDate;

	@Column(name = "round_time")
	private String roundTime;

	@Column(name = "constructive_feedback")
	private String constructiveFeedback;

	@Column(name = "candidate_resume")
	private String candidateResume;

	@Column(name = "round_Status")
	private String roundStatus;

	@Column(name = "ics_file")
	private String icsFile;

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

	private Float communicationRating;

	private String readyToRelocate;

	private String agreement;

	private Double profileRelevance;

	private String hrFeedback;

	
	public ScheduledInterview() {
		super();

	}

	public ScheduledInterview(Long scheduledInterviewId, String candidateUniqueNumber, String candidateFullName,
			int departmentId, int profileId, Long interviewerEmployeeId, String modeNo, String videoLinkDetails,
			int roundDetails, Date roundDate, String roundTime, String constructiveFeedback, String candidateResume,
			String roundStatus, String icsFile, Long createdBy, Date createdTime, Long modifiedBy, Date modifiedTime,
			Float communicationRating, String readyToRelocate, String agreement, Double profileRelevance,
			String hrFeedback) {
		super();
		this.scheduledInterviewId = scheduledInterviewId;
		this.candidateUniqueNumber = candidateUniqueNumber;
		this.candidateFullName = candidateFullName;
		this.departmentId = departmentId;
		this.profileId = profileId;
		this.interviewerEmployeeId = interviewerEmployeeId;
		this.modeNo = modeNo;
		this.videoLinkDetails = videoLinkDetails;
		this.roundDetails = roundDetails;
		this.roundDate = roundDate;
		this.roundTime = roundTime;
		this.constructiveFeedback = constructiveFeedback;
		this.candidateResume = candidateResume;
		this.roundStatus = roundStatus;
		this.icsFile = icsFile;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
		this.communicationRating = communicationRating;
		this.readyToRelocate = readyToRelocate;
		this.agreement = agreement;
		this.profileRelevance = profileRelevance;
		this.hrFeedback = hrFeedback;
	}

	public Long getScheduledInterviewId() {
		return scheduledInterviewId;
	}

	public void setScheduledInterviewId(Long scheduledInterviewId) {
		this.scheduledInterviewId = scheduledInterviewId;
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

	public Long getInterviewerEmployeeId() {
		return interviewerEmployeeId;
	}

	public void setInterviewerEmployeeId(Long interviewerEmployeeId) {
		this.interviewerEmployeeId = interviewerEmployeeId;
	}

	public String getModeNo() {
		return modeNo;
	}

	public void setModeNo(String modeNo) {
		this.modeNo = modeNo;
	}

	public String getVideoLinkDetails() {
		return videoLinkDetails;
	}

	public void setVideoLinkDetails(String videoLinkDetails) {
		this.videoLinkDetails = videoLinkDetails;
	}

	public int getRoundDetails() {
		return roundDetails;
	}

	public void setRoundDetails(int roundDetails) {
		this.roundDetails = roundDetails;
	}

	public Date getRoundDate() {
		return roundDate;
	}

	public String getRoundDateSort() {
		if (roundDate == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("E dd MMM yyyy");
			return dateFormat.format(roundDate);
		}
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public String getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(String roundTime) {
		this.roundTime = roundTime;
	}

	public String getConstructiveFeedback() {
		return constructiveFeedback;
	}

	public void setConstructiveFeedback(String constructiveFeedback) {
		this.constructiveFeedback = constructiveFeedback;
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

	public String getIcsFile() {
		return icsFile;
	}

	public void setIcsFile(String icsFile) {
		this.icsFile = icsFile;
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

	public Float getCommunicationRating() {
		return communicationRating;
	}

	public void setCommunicationRating(Float communicationRating) {
		this.communicationRating = communicationRating;
	}

	public String getReadyToRelocate() {
		return readyToRelocate;
	}

	public void setReadyToRelocate(String readyToRelocate) {
		this.readyToRelocate = readyToRelocate;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public Double getProfileRelevance() {
		return profileRelevance;
	}

	public void setProfileRelevance(Double profileRelevance) {
		this.profileRelevance = profileRelevance;
	}

	public String getHrFeedback() {
		return hrFeedback;
	}

	public void setHrFeedback(String hrFeedback) {
		this.hrFeedback = hrFeedback;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ScheduledInterview [scheduledInterviewId=" + scheduledInterviewId + ", candidateUniqueNumber="
				+ candidateUniqueNumber + ", candidateFullName=" + candidateFullName + ", departmentId=" + departmentId
				+ ", profileId=" + profileId + ", interviewerEmployeeId=" + interviewerEmployeeId + ", modeNo=" + modeNo
				+ ", videoLinkDetails=" + videoLinkDetails + ", roundDetails=" + roundDetails + ", roundDate="
				+ roundDate + ", roundTime=" + roundTime + ", constructiveFeedback=" + constructiveFeedback
				+ ", candidateResume=" + candidateResume + ", roundStatus=" + roundStatus + ", icsFile=" + icsFile
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedTime=" + modifiedTime + ", communicationRating=" + communicationRating
				+ ", readyToRelocate=" + readyToRelocate + ", agreement=" + agreement + ", profileRelevance="
				+ profileRelevance + ", hrFeedback=" + hrFeedback + "]";
	}

}
