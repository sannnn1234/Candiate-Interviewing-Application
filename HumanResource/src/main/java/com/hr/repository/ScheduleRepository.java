package com.hr.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.dto.CandidateFeedbackList;
import com.hr.dto.CandidateSelectedDto;
import com.hr.dto.EmployeeDto;
import com.hr.dto.FeedbackDto;
import com.hr.dto.HRReportDto;
import com.hr.dto.InterviewScheduleIdDto;
import com.hr.dto.InterviewerDeptDto;
import com.hr.dto.InterviewerDto;
import com.hr.dto.InterviewerHrDto;
import com.hr.dto.MonthlyHrRoundStatusDto;
import com.hr.dto.ScheduleDto;
import com.hr.dto.ScheduledInterviewListDto;
import com.hr.dto.departmentWiseReportDto;
import com.hr.dto.roundPrevDto;
import com.hr.model.Agreement;
import com.hr.model.ScheduledInterview;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduledInterview, Long> {

	public ScheduledInterview findByCandidateUniqueNumber(@Param("candidateUniqueNumber") String candidateUniqueNumber);

	public ScheduledInterview findByScheduledInterviewId(@Param("scheduledInterviewId") Long scheduledInterviewId);

	// [NamedNativeQuery for fetching Scheduled Interview List]
	// [Updated on 06 Feb 2024]
	// comment this query becasuse using another query for fetching list
	// @Query(value = "select s from ScheduledInterview s where s.roundStatus
	// in('Scheduled','On-hold')")
	// public List<ScheduledInterview> getRoundStatus();
	@Query(name = "getScheduledInterviewList", nativeQuery = true)
	List<ScheduledInterviewListDto> getScheduledInterviewList();

	 // [NamedNativeQuery for fetching Scheduled Interview List]
	 // [Updated on 06 Feb 2024]
     //@Query(value = "select s from ScheduledInterview s where s.interviewerEmployeeId =:interviewerEmployeeId and s.roundStatus in('Scheduled','On-hold')")
     //public List<ScheduledInterview> getInterviewerCandidateList(@Param("interviewerEmployeeId") Long interviewerEmployeeId);
	@Query(name = "getScheduledInterviewBasedOnInterviewId", nativeQuery = true)
	List<ScheduledInterviewListDto> getScheduledInterviewBasedOnIEId(@Param("interviewerEmployeeId") Long interviewerEmployeeId);
	

	@Query(value = "select i from ScheduledInterview i")
	public List<ScheduledInterview> getAllListScheduleCandidate();

	@Transactional
	@Modifying
	@Query(value = "{CALL candidate_next_round(:scheduledInterviewId)}", nativeQuery = true)
	public void getnextRoundListOfCandidate(@Param("scheduledInterviewId") Long scheduledInterviewId);

	@Query(value = "select count(distinct s.candidateFullName) from ScheduledInterview s where s.roundStatus ='Scheduled'")
	public Long getInterviewSchedule();

	@Query(value = "select count( s.candidateFullName) from ScheduledInterview s where s.roundStatus='Rejected'")
	public Long getTotalRejected();

	@Query(value = "select count(distinct s.candidateFullName) from ScheduledInterview s where s.roundStatus='Selected'")
	public Long getTotalHiredCandidate();

	@Query(value = "select distinct(Year(s.createdTime)) from ScheduledInterview s")
	public List<Integer> getFinancialYearList();

	@Query("select new com.hr.dto.ScheduleDto(ed.candidateFullName, ed.roundDate, ed.roundTime, ed.videoLinkDetails) from ScheduledInterview ed")
	List<ScheduleDto> getSchedulerDetails();

	// namedNativeQuery for fetching feedback list
	@Query(name = "getInterviewerName", nativeQuery = true)
	List<InterviewerDeptDto> getInterviewerNameDetails(@Param("profileId") Integer profileId);

	@Query("select new com.hr.dto.InterviewerDto(a.empId, a.fullName) from employee a")
	List<InterviewerDto> getInterviewerName();

	@Query(value = "select iim.video_link_details  from interview_information_master iim"
			+ "	where iim.video_link_details not in (select  video_link_details  from interview_scheduled_master ism where :roundDate between ism.round_date and addtime(ism.round_date, '00:59:00.00'))and iim.video_link_details != ''", nativeQuery = true)
	List<String> getVideoLinkAvailableDetails(@Param("roundDate") String roundDate);

	@Query("select new com.hr.dto.InterviewScheduleIdDto(s.scheduledInterviewId, s.candidateUniqueNumber, s.candidateFullName, c.contactNumber,c.currentSalary, c.expectedSalary, c.relevantExperience, c.totalExperience, c.emailAddress, d.department, p.profile, s.interviewerEmployeeId, e.fullName, e.empPhone, e.email , r.roundName, s.communicationRating, c.noticePeriod, e.groupDescription, s.constructiveFeedback, s.readyToRelocate, s.profileRelevance) from ScheduledInterview s inner join candidate c on s.candidateUniqueNumber = c.candidateUniqueNumber inner join employee e on s.interviewerEmployeeId = e.empId inner join departmentdetails d on s.departmentId = d.departmentId inner join profiledetails p  on s.profileId = p.profileId inner join Round r on s.roundDetails = r.roundId Where s.scheduledInterviewId=:scheduledInterviewId")
	InterviewScheduleIdDto getScheduleDetailsById(@Param("scheduledInterviewId") Long scheduledInterviewId);

	@Query(value = "select a.round_id  from department_round_mapping a , department_rounds_information d, interview_scheduled_master c , round_master r where a.item_no = d.item_no and d.profile_id = c.profile_id and a.round_id = r.round_id and  c.profile_id = :profileId and a.round_id > :roundDetails and scheduled_id = :scheduledInterviewId order by round_id asc limit 1; ", nativeQuery = true)
	Integer getRoundNameListDetails(@Param("scheduledInterviewId") Long scheduledInterviewId,
			@Param("profileId") Integer profileId, @Param("roundDetails") Integer roundDetails);

	@Query(value = "select new com.hr.dto.roundPrevDto(s.roundDetails, r.roundName) from ScheduledInterview s inner join Round r on s.roundDetails = r.roundId where s.candidateUniqueNumber = :candidateUniqueNumber and s.roundDetails < :roundDetails order by s.roundDetails desc ")
	List<roundPrevDto> getpreviousRound(@Param("candidateUniqueNumber") String candidateUniqueNumber,
			@Param("roundDetails") Integer roundDetails);

	@Query(value = "select new com.hr.dto.InterviewerHrDto(s.createdBy, e.fullName, e.email, e.empPhone) from ScheduledInterview s inner join employee e on s.createdBy = e.empId  where s.candidateUniqueNumber = :candidateUniqueNumber  and s.roundDetails = 1 ORDER BY s.createdBy")
	List<InterviewerHrDto> getHrRounddetails(@Param("candidateUniqueNumber") String candidateUniqueNumber);

	// namedNativeQuery for fetching candidate list
	@Query(name = "getCandidateSelectedList", nativeQuery = true)
	List<CandidateSelectedDto> getCandidateListAfterDuration();

	// namedNativeQuery for fetching feedback list
	@Query(name = "getCandidateFeedbackList", nativeQuery = true)
	List<FeedbackDto> getCandidateFeedbackAfterDuration();

	// scheduled interview based on scheduled id
	@Query(name = "getInterviewscheduledDto", nativeQuery = true)
	InterviewScheduleIdDto getInterviewScheduledDto(@Param("scheduledInterviewId") Long scheduledInterviewId);

	@Query(value = "select scheduled_id FROM interview_scheduled_master WHERE DATE_sub(CURRENT_DATE() ,INTERVAL 10 Day) > Date(round_date) "
			+ "	and  round_status in('Scheduled', 'Shortlisted');", nativeQuery = true)
	List<String> getNotUpdateCandidateJoinigList();

	// Update 6 Feb 2024 for Authorization
	@Query(value = "select em.emp_id FROM interview_scheduled_master s inner join candidate_information_master c "
			+ "on s.candidate_unique_number = c.candidate_unique_number  "
			+ "inner join employee_master em on c.created_by = em.emp_id "
			+ "where s.scheduled_id = :scheduledInterviewId " + "union "
			+ "select em.emp_id FROM interview_scheduled_master s inner join candidate_information_master c "
			+ "on s.candidate_unique_number = c.candidate_unique_number  "
			+ "inner join employee_master em on s.interviewer_employee_id  = em.emp_id "
			+ "where s.scheduled_id = :scheduledInterviewId", nativeQuery = true)
	List<Long> getIsValidAccess(@Param("scheduledInterviewId") Long scheduledInterviewId);

	@Query(value = "select em.emp_id FROM interview_scheduled_master s inner join candidate_information_master c "
			+ "on s.candidate_unique_number = c.candidate_unique_number  "
			+ "inner join employee_master em on c.created_by = em.emp_id "
			+ "where s.scheduled_id = :scheduledInterviewId ", nativeQuery = true)
	Long getUserValidAccess(@Param("scheduledInterviewId") Long scheduledInterviewId);

	// profile Id HR
	@Query(value = "Select p.profileId from profiledetails p where p.profile ='HR'")
	Integer getHRProfileId();

	// reports
	@Query(value = "select new com.hr.dto.HRReportDto(s.createdBy,e.fullName, s.roundStatus, count(s.roundStatus)) FROM ScheduledInterview s inner join employee e on s.createdBy = e.empId WHERE MONTH(s.createdTime) = :month AND s.roundStatus IN ('Selected', 'Rejected', 'Scheduled','Shortlisted') and s.scheduledInterviewId  =(select max(scheduledInterviewId) from ScheduledInterview where candidateUniqueNumber = s.candidateUniqueNumber) group by s.createdBy, e.fullName, s.roundStatus")
	List<HRReportDto> gethrReportOnMonth(@Param("month") Integer month);

	// reports
	@Query(value = "select new com.hr.dto.departmentWiseReportDto( s.departmentId , d.department , count(s.roundStatus) )from ScheduledInterview s inner join departmentdetails d  on s.departmentId = d.departmentId  where Year(s.roundDate) = :year and round_status ='Selected' group by s.departmentId , d.department")
	List<departmentWiseReportDto> getDepartmentWiseSelection(@Param("year") Integer year);

	// reports
	@Query(value = "select new com.hr.dto.MonthlyHrRoundStatusDto(s.candidateUniqueNumber , s.candidateFullName, s.createdBy, s.interviewerEmployeeId, e.fullName, s.roundDetails, r.roundName, s.roundDate, s.roundStatus)from ScheduledInterview s inner join employee e on s.interviewerEmployeeId = e.empId inner join Round r on s.roundDetails = r.roundId where ( :roundStatus='all' or s.roundStatus in (:roundStatus)) and Month(s.roundDate) =:month and s.createdBy =:createdBy ")
	List<MonthlyHrRoundStatusDto> getMonthlyHRAndRoundStatusReport(@Param("createdBy") Long createdBy,
			@Param("month") Integer month, @Param("roundStatus") String roundStatus);

//	reports
	@Query(value = "select new com.hr.dto.MonthlyHrRoundStatusDto(s.candidateUniqueNumber , s.candidateFullName, s.createdBy, s.interviewerEmployeeId, e.fullName, s.roundDetails, r.roundName, s.roundDate, s.roundStatus)from ScheduledInterview s inner join employee e on s.interviewerEmployeeId = e.empId inner join Round r on s.roundDetails = r.roundId where  Year(s.roundDate) =:year  order by s.createdBy, s.roundDate")
	List<MonthlyHrRoundStatusDto> getYearlyHRReport(@Param("year") Integer year);

	// get all Hr list
	@Query(value = "select distinct new com.hr.dto.EmployeeDto(s.createdBy, e.fullName)from ScheduledInterview s inner join employee e on s.createdBy = e.empId")
	public List<EmployeeDto> getHRList();

	// get all Round status list
	@Query(value = "select distinct s.roundStatus from ScheduledInterview s")
	public List<String> getRoundStatusList();

	// get Current ScheduledId based on CandidateUnique Number
	@Query(value = "select s.scheduledInterviewId from ScheduledInterview s where s.scheduledInterviewId = (Select MAX(s.scheduledInterviewId) FROM ScheduledInterview s WHERE s.candidateUniqueNumber = :candidateUniqueNumber)")
	Integer getCurrentScheduledID(@Param("candidateUniqueNumber") String candidateUniqueNumber);

	// get Current ScheduledId based on CandidateUnique Number
	@Query(value = "select a from Agreement a")
	public List<Agreement> getAgreementList();

	// get the list feedback all round in candidate master
	@Query(value = "select new com.hr.dto.CandidateFeedbackList(s.scheduledInterviewId, s.candidateUniqueNumber , s.candidateFullName, s.roundDetails, r.roundName, s.roundStatus, s.constructiveFeedback, c.joiningFeedback) from ScheduledInterview s Inner Join  Round r on s.roundDetails = r.roundId Inner Join candidate c on s.candidateUniqueNumber = c.candidateUniqueNumber where s.candidateUniqueNumber = :candidateUniqueNumber")
	public List<CandidateFeedbackList> getFeedbackRoundList(
			@Param("candidateUniqueNumber") String candidateUniqueNumber);

	@Query(value = "SELECT d.round_id FROM department_round_mapping  d , department_rounds_information r, round_master rm WHERE d.item_no  = r.item_no and d.round_id  = rm.round_id and r.profile_id =:profileId ORDER BY round_id DESC LIMIT 1;", nativeQuery = true)
	Integer getFinalRound(@Param("profileId") Integer profileId);

	@Query(value = "select constructive_feedback from interview_scheduled_master where candidate_unique_number =:candidateUniqueNumber and round_details = 1 order by scheduled_id desc limit 1;", nativeQuery = true)
	String getHRFeedbackEmail(@Param("candidateUniqueNumber") String candidateUniqueNumber);

	@Query(value = "select ready_to_relocate  from interview_scheduled_master  where candidate_unique_number =:candidateUniqueNumber and ready_to_relocate is Not NULL limit 1;", nativeQuery = true)
	String getReadyToRelocate(@Param("candidateUniqueNumber") String candidateUniqueNumber);
}
