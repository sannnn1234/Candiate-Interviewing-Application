/*
 * Author:Sandeep Gupta
 * Date:6 Feb 2023
 * Version:0.0.1
 */

package com.hr.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
import com.hr.dto.ScheduledInterviewListDto;
import com.hr.dto.departmentWiseReportDto;
import com.hr.dto.roundPrevDto;
import com.hr.model.Agreement;
import com.hr.model.Round;
import com.hr.model.ScheduledInterview;
import com.hr.model.candidate;
import com.hr.model.employee;
import com.hr.model.profiledetails;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.ProfiledetailsRepository;
import com.hr.repository.RoundListRepository;
import com.hr.repository.ScheduleRepository;
import com.hr.repository.candidaterepository;
import com.hr.service.EmailService;
import com.hr.service.ScheduleService;

@RestController
@CrossOrigin("${port}")
public class InterviewScheduleController {

	@Autowired
	public ScheduleRepository scheduleRepository;
	@Autowired
	public ScheduleService ss;

	@Autowired
	public EmailService emailservice;

	@Autowired
	public candidaterepository cr;

	@Autowired
	public EmployeeRepository empRespo;

	@Autowired
	public ProfiledetailsRepository pdr;

	@Autowired
	public RoundListRepository rl;

	@Autowired
	public FileUploadHelper fileUploadHelper;

	// get all schedule details
	@GetMapping("/schedule-details")
	public List<ScheduledInterviewListDto> getAllScheduleDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		employee e = (employee) principal;
		if ((e.getGroupDescription().equalsIgnoreCase("HR") || e.getGroupDescription().equalsIgnoreCase("Admin"))) {
			return (List<ScheduledInterviewListDto>) scheduleRepository.getScheduledInterviewList();
		} else {
			return (List<ScheduledInterviewListDto>) scheduleRepository.getScheduledInterviewBasedOnIEId(e.getEmpId());
		}

	}

	// get schedule details List
	@GetMapping("/schedule-details-interviewer-schedule")
	public List<InterviewerDto> getScheduleDeatilsList() {
		return scheduleRepository.getInterviewerName();
	}

	// get the HR List
	@GetMapping("/schedule-details-hr-list")
	public List<EmployeeDto> getHRList() {
		return scheduleRepository.getHRList();
	}

	// get the Round status List
	@GetMapping("/schedule-details-roundstatus-list")
	public List<String> getRoundStatusList() {
		return scheduleRepository.getRoundStatusList();
	}

	// get the agreement List
	@GetMapping("/schedule-details-agreement")
	public List<Agreement> getAgreementList() {
		return scheduleRepository.getAgreementList();
	}

	// get the HR Profile Id
	@GetMapping("/schedule-details-hr-profileid")
	public Integer getHRProfileId() {
		return scheduleRepository.getHRProfileId();
	}

	// get the feedback List
	@GetMapping("/schedule-details-feedbacklist")
	public List<CandidateFeedbackList> getRoundFeedbackList(
			@RequestParam("candidateUniqueNumber") String candidateUniqueNumber) {
		return scheduleRepository.getFeedbackRoundList(candidateUniqueNumber);
	}

	// get all department and profile details
	@GetMapping("/schedule-details-roundname/{scheduledInterviewId}/{profileId}/{roundDetails}")
	public Integer getRoundNameDetails(@PathVariable Long scheduledInterviewId, @PathVariable Integer profileId,
			@PathVariable Integer roundDetails) {
		return scheduleRepository.getRoundNameListDetails(scheduledInterviewId, profileId, roundDetails);

	}

	// get all schedule interview details
	@GetMapping("/schedule-details-candidate")
	public Long getTotalCandidate() {
		return scheduleRepository.getInterviewSchedule();

	}

	// get all Rejected Candidate
	@GetMapping("/schedule-details-rejected")
	public Long getTotalRejectedCandidate() {
		return scheduleRepository.getTotalRejected();

	}

	// get schedule details List
	@GetMapping("/schedule-details-scheduled")
	public InterviewScheduleIdDto getScheduleList(@RequestBody ScheduledInterview schedule) {
//		System.out.println(scheduleRepository.getScheduleDetailsById(schedule.getScheduledInterviewId() , schedule.getProfileId() , schedule.getRoundDetails()));
		return scheduleRepository.getScheduleDetailsById(schedule.getScheduledInterviewId());
	}

	// get all available videolinks
	@GetMapping("/schedule-details-videolinks")
	public List<String> getAvialableVideoLinks(@RequestParam("roundDate") String roundDate) {
		return scheduleRepository.getVideoLinkAvailableDetails(roundDate);

	}

	// get all InterViewerName
	@GetMapping("/schedule-details-Interviewer")
	public List<InterviewerDeptDto> getDepartmentInterviewerName(@RequestParam("profileId") int profileId) {
		return scheduleRepository.getInterviewerNameDetails(profileId);

	}

	// get the list of candidates if HR has not reverted on the update on candidate
	// with respect to joining within 10 days.
	@GetMapping("/schedule-details-candidate-selected")
	public List<CandidateSelectedDto> getCandidateSelected() {
		return scheduleRepository.getCandidateListAfterDuration();

	}

	// get the list of candidates if feedback not received within 5 days
	@GetMapping("/schedule-details-candidate-feedback")
	public List<FeedbackDto> getCandidateFeeback() {
		return scheduleRepository.getCandidateFeedbackAfterDuration();

	}

	// get the list created by, roundstatus and candidate count based on month
	@GetMapping("/schedule-details-hr-report")
	public List<HRReportDto> getHRReport(@RequestParam Integer month) {
		return scheduleRepository.gethrReportOnMonth(month + 1);

	}

	// get the list of department wise candidate selection based on month and year
	@GetMapping("/schedule-details-department-report")
	public List<departmentWiseReportDto> getDepartmentWiseSelection(@RequestParam Integer year) {
		return scheduleRepository.getDepartmentWiseSelection(year);

	}

	// get the list of Monthly Hr and roundstatus report
	@GetMapping("/schedule-details-hr-roundstatus-report")
	public List<MonthlyHrRoundStatusDto> getMonthlyHRRoundStatusReport(@RequestParam Long createdBy,
			@RequestParam Integer month, @RequestParam(name = "roundStatus", defaultValue = "all") String roundStatus) {
		return scheduleRepository.getMonthlyHRAndRoundStatusReport(createdBy, month, roundStatus);

	}

	// get the list of yearly based hr reports
	@GetMapping("/schedule-details-yearly-hr-report")
	public List<MonthlyHrRoundStatusDto> getYearlyHRReport(@RequestParam Integer year) {
		return scheduleRepository.getYearlyHRReport(year);

	}

	// get the list of financial year
	@GetMapping("/schedule-details-year")
	public List<Integer> getFinancialYear() {
		return scheduleRepository.getFinancialYearList();

	}

	// get all Hired Candidate
	@GetMapping("/schedule-details-hired")
	public Long getTotalHiredCandidate() {
		return scheduleRepository.getTotalHiredCandidate();

	}

	// Create schedule Details rest api
	@PostMapping(value = "/schedule-details/{roundStatus}/{roundDetails}")
	public ResponseEntity<Object> scheduleDetails(@RequestParam(name = "file", required = false) MultipartFile file,
			@ModelAttribute ScheduledInterview schedule, @PathVariable String roundStatus,
			@PathVariable Integer roundDetails) {

		candidate cand = cr.findByCandidateUniqueNumber(schedule.getCandidateUniqueNumber());
		try {
			if (roundStatus.equals("Scheduled")) {
				schedule.setRoundStatus("Scheduled");
				schedule.setRoundDetails(roundDetails);
				cand.setRoundStatus("Scheduled");
				ss.getNameByCandidateUniqueNumber(file, schedule, roundStatus, roundDetails);
				cr.save(cand);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	// get schedule details by Id rest api
	@GetMapping("/schedule-details/{scheduledInterviewId}")
	public ResponseEntity<ScheduledInterview> getScheduleDetailsById(@PathVariable Long scheduledInterviewId) {
		ScheduledInterview scheduled = scheduleRepository.findById(scheduledInterviewId).get();
		return ResponseEntity.ok(scheduled);
	}

	// Reschedule interview details rest api
	@PutMapping("/schedule-details-edits/{scheduledInterviewId}/{roundDetails}/{roundStatus}")
	public ResponseEntity<ScheduledInterview> updateScheduleDetails(
			@RequestParam(name = "file", required = false) MultipartFile file, @PathVariable Long scheduledInterviewId,
			@PathVariable Integer roundDetails, @PathVariable String roundStatus,
			@ModelAttribute ScheduledInterview schedule) {
		try {
			ScheduledInterview oldScheduled = scheduleRepository.findByScheduledInterviewId(scheduledInterviewId);
			ScheduledInterview scheduled = new ScheduledInterview();
			candidate cand = cr.findByCandidateUniqueNumber(schedule.getCandidateUniqueNumber());
			if (roundStatus.equals("Rescheduled")) {
				scheduled.setScheduledInterviewId(null);
				scheduled.setCandidateUniqueNumber(schedule.getCandidateUniqueNumber());
				scheduled.setCandidateFullName(schedule.getCandidateFullName());
				scheduled.setDepartmentId(schedule.getDepartmentId());
				scheduled.setProfileId(schedule.getProfileId());
				scheduled.setInterviewerEmployeeId(schedule.getInterviewerEmployeeId());
				scheduled.setModeNo(schedule.getModeNo());
				scheduled.setVideoLinkDetails(schedule.getVideoLinkDetails());
				scheduled.setRoundTime(schedule.getRoundTime());
				scheduled.setRoundDetails(schedule.getRoundDetails());
				scheduled.setCandidateResume(cand.getCandidateResume());
				scheduled.setCommunicationRating(oldScheduled.getCommunicationRating());
				scheduled.setRoundDetails(roundDetails);
				scheduled.setProfileRelevance(oldScheduled.getProfileRelevance());
				scheduled.setRoundDate(schedule.getRoundDate());

				try {
					if (file != null && !file.isEmpty()) {
						if (!file.getContentType().equals("text/calendar")) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
						} else {
							if (fileUploadHelper.uploadIcsFile(file, scheduled)) {
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				cand.setRoundStatus("Rescheduled");
				scheduled.setRoundStatus("Scheduled");
				oldScheduled.setRoundStatus("Rescheduled");
				oldScheduled.setConstructiveFeedback("Interview postponed");
				scheduleRepository.save(scheduled);
				scheduleRepository.save(oldScheduled);
				cr.save(cand);
				employee en = empRespo.findByEmpId(schedule.getInterviewerEmployeeId());
				profiledetails pd = pdr.findById(schedule.getProfileId()).get();
				Round r = rl.findById(schedule.getRoundDetails()).get();
				String readytorelocate = scheduleRepository.getReadyToRelocate(schedule.getCandidateUniqueNumber());
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				InterviewerHrDto hr = cr.getHrList(schedule.getCandidateUniqueNumber());
				String feedback = scheduleRepository.getHRFeedbackEmail(schedule.getCandidateUniqueNumber());
				d.setContactNumber(cand.getContactNumber());
				d.setCurrentSalary(cand.getCurrentSalary());
				d.setExpectedSalary(cand.getExpectedSalary());
				d.setRelevantExperience(cand.getRelevantExperience());
				d.setTotalExperience(cand.getTotalExperience());
				d.setNoticePeriod(cand.getNoticePeriod());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setGroupDescription(en.getGroupDescription());
				d.setEmail(en.getEmail());
				d.setRoundName(r.getRoundName());
				d.setConstructiveFeedback(feedback);
				d.setReadyToRelocate(readytorelocate);
				Integer i = scheduleRepository.getFinalRound(schedule.getProfileId());
				if (schedule.getRoundDetails() == i) {
					ExecutorService executor = Executors.newWorkStealingPool();
					executor.execute(() -> {
						// Hr send rescheduled email to candidate for next round
						emailservice.reScheduleInteviewEmailToCandidate(scheduled, d, hr, cand.getEmailAddress());
						// Hr send rescheduled email to interviewer for next round
						emailservice.rescheduledInterviewEmailToInterviewerFinal(scheduled, d, hr, en.getEmail());
					});
				} else {
					ExecutorService executor = Executors.newWorkStealingPool();
					executor.execute(() -> {
						// Hr send rescheduled email to candidate for Hr round
						emailservice.reScheduledInteviewHREmailToCandidate(scheduled, d, hr, cand.getEmailAddress());
						// Hr send rescheduled email to interviewer for Hr round
						emailservice.rescheduledInteviewEmailToInterviewer(scheduled, d, hr, en.getEmail());
					});
				}

			}
		} catch (NonUniqueResultException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}

	// update schedule interview details
	@PutMapping("/schedule-details-update/{scheduledInterviewId}/{roundStatus}")
	public ResponseEntity<ScheduledInterview> updateNextRoundInterview(
			@RequestParam(name = "file", required = false) MultipartFile file, @PathVariable Long scheduledInterviewId,
			@PathVariable String roundStatus, @ModelAttribute ScheduledInterview schedule) {
		ScheduledInterview s = scheduleRepository.findByScheduledInterviewId(schedule.getScheduledInterviewId());
		candidate cand = cr.findByCandidateUniqueNumber(schedule.getCandidateUniqueNumber());
		try {

			if (roundStatus.equals("Scheduled")) {
				schedule.setCandidateFullName(cand.getCandidateFullName());
				schedule.setCandidateUniqueNumber(cand.getCandidateUniqueNumber());
				schedule.setCandidateResume(cand.getCandidateResume());
				schedule.setCreatedBy(s.getCreatedBy());
				schedule.setCreatedTime(s.getCreatedTime());
				schedule.setCommunicationRating(s.getCommunicationRating());
				schedule.setProfileRelevance(s.getProfileRelevance());

				try {
					if (file != null && !file.isEmpty()) {
						if (!file.getContentType().equals("text/calendar")) {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
						} else {
							if (fileUploadHelper.uploadIcsFile(file, schedule)) {
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				schedule.setRoundStatus("Scheduled");
				cand.setRoundStatus("Scheduled");
				employee en = empRespo.findByEmpId(schedule.getInterviewerEmployeeId());
				String readytorelocate = scheduleRepository.getReadyToRelocate(schedule.getCandidateUniqueNumber());
				profiledetails pd = pdr.findById(schedule.getProfileId()).get();
				Round r = rl.findById(schedule.getRoundDetails()).get();
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				roundPrevDto rd = scheduleRepository
						.getpreviousRound(schedule.getCandidateUniqueNumber(), schedule.getRoundDetails()).get(0);
				InterviewerHrDto hr = cr.getHrList(schedule.getCandidateUniqueNumber());
				String feedback = scheduleRepository.getHRFeedbackEmail(schedule.getCandidateUniqueNumber());
				d.setContactNumber(cand.getContactNumber());
				d.setCurrentSalary(cand.getCurrentSalary());
				d.setExpectedSalary(cand.getExpectedSalary());
				d.setRelevantExperience(cand.getRelevantExperience());
				d.setTotalExperience(cand.getTotalExperience());
				d.setNoticePeriod(cand.getNoticePeriod());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());
				d.setRoundName(r.getRoundName());
				d.setConstructiveFeedback(feedback);
				d.setGroupDescription(en.getGroupDescription());
				d.setReadyToRelocate(readytorelocate);
				Integer i = scheduleRepository.getFinalRound(schedule.getProfileId());
				if (schedule.getRoundDetails() == i) {
					ExecutorService executor = Executors.newWorkStealingPool();
					executor.execute(() -> {
						// HR send scheduled interview email to candidate for next round
						emailservice.roundWiseEmailToCandidate(schedule, d, rd, hr, cand.getEmailAddress());
						// Hr send scheduled interview email to interviewer for next round
						emailservice.InteviewScheduleEmailToInterviewerFinal(schedule, d, hr, en.getEmail());
					});
				} else {
					ExecutorService executor = Executors.newWorkStealingPool();
					executor.execute(() -> {
						// HR send scheduled interview email to candidate for HR round
						emailservice.roundWiseEmailToCandidate(schedule, d, rd, hr, cand.getEmailAddress());
						// HR send scheduled interview email to interviewer for Hr round
						emailservice.scheduleInteviewEmailToInterviewer(schedule, d, hr, en.getEmail());
					});
				}

				scheduleRepository.save(schedule);
				cr.save(cand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}

	// update schedule interview feedbak details
	@SuppressWarnings("unused")
	@PutMapping("/schedule-details-update-feedback/{scheduledInterviewId}")
	public ResponseEntity<ScheduledInterview> updateFeedbackList(@PathVariable Long scheduledInterviewId,
			@RequestBody ScheduledInterview schedule) {

		try {
			ScheduledInterview scheduled = scheduleRepository.findByScheduledInterviewId(scheduledInterviewId);
			scheduled.setHrFeedback(schedule.getHrFeedback());
			ScheduledInterview updateScheduleDetails = scheduleRepository.save(scheduled);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}

	// get authorization of component
	@GetMapping("/schedule-details-verifyRole/{scheduledInterviewId}")
	public ResponseEntity<List<Long>> getIsValidUser(@PathVariable Long scheduledInterviewId) {
		try {
			List<Long> isValidAccess = scheduleRepository.getIsValidAccess(scheduledInterviewId);
			return ResponseEntity.ok(isValidAccess);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// get authorization of component
	@GetMapping("/schedule-details-roleaccess/{scheduledInterviewId}")
	public ResponseEntity<Long> getUserAccess(@PathVariable Long scheduledInterviewId) {
		try {
			Long userAccess = scheduleRepository.getUserValidAccess(scheduledInterviewId);
			return ResponseEntity.ok(userAccess);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// get list of not update candidate respect to joining waiting of 10 days
	@GetMapping("/schedule-details-update-joining/{scheduledInterviewId}")
	public ResponseEntity<String> getUpdateCandidateJoinigList() {
		scheduleRepository.getNotUpdateCandidateJoinigList();
		return ResponseEntity.ok().build();
	}

	// get list of not update candidate respect to joining date waiting of 10 days
	@GetMapping("/schedule-details-status")
	public List<ScheduledInterview> getAllInterviewScheduleDetails() {
		return (List<ScheduledInterview>) scheduleRepository.getAllListScheduleCandidate();

	}

}
