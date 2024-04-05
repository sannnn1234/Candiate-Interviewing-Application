/*
 * Author:Sandeep Gupta
 * Date:16 Feb 2023
 * Version:0.0.1
 */
package com.hr.controller;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.InterviewScheduleIdDto;
import com.hr.dto.InterviewerHrDto;
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

@RestController
@CrossOrigin("${port}")
public class HomeController {

	@Autowired
	public ScheduleRepository scheduleRepository;
	
	@Autowired
    public candidaterepository cr;
	@Autowired
	public EmailService emailservice;

	@Autowired
	public EmployeeRepository empRepo;
	
	@Autowired
	public ProfiledetailsRepository pdr;
	
	@Autowired
	public RoundListRepository rl;

	// [Update Schedule Status]
	@PutMapping("/schedule-details/{scheduledInterviewId}/{roundDetails}/{roundStatus}/{constructiveFeedback}")
	public ResponseEntity<Object> updateScheduleStatus(@PathVariable Long scheduledInterviewId,
			@PathVariable int roundDetails, @PathVariable String roundStatus, @PathVariable String constructiveFeedback,
			@RequestBody ScheduledInterview schedule) {
		ScheduledInterview scheduled = scheduleRepository.findByScheduledInterviewId(scheduledInterviewId);
		candidate cand = cr.findByCandidateUniqueNumber(scheduled.getCandidateUniqueNumber());
		try {
			System.out.println(schedule);
			if (roundStatus.equals("Shortlisted")) {
				scheduled.setRoundStatus("Shortlisted");
				scheduled.setConstructiveFeedback(constructiveFeedback);
				scheduled.setProfileRelevance(schedule.getProfileRelevance());
				scheduled.setCommunicationRating(schedule.getCommunicationRating());
				scheduled.setReadyToRelocate(schedule.getReadyToRelocate());
				scheduled.setAgreement(schedule.getAgreement());
				cand.setRoundStatus("Shortlisted");
				scheduleRepository.save(scheduled);
			    cr.save(cand);
				System.out.println(schedule.getInterviewerEmployeeId());
				scheduleRepository.getnextRoundListOfCandidate(scheduledInterviewId);
				employee en = empRepo.findByEmpId(scheduled.getInterviewerEmployeeId());
				profiledetails pd = pdr.findByProfileId(schedule.getProfileId());
				Integer rId= scheduleRepository.getRoundNameListDetails(scheduledInterviewId, scheduled.getProfileId(), roundDetails);
				Round r = rl.findById(rId).get();
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber()); 
				d.setRoundName(r.getRoundName());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());

				ExecutorService executor = Executors.newWorkStealingPool();
				executor.execute(() -> {
					emailservice.shortlistedCandidateEmailToHR(scheduled, d, hr, hr.getEmail());
					
				});
			}else if(roundStatus.equals("Selected")){
				scheduled.setRoundStatus("Selected");
				scheduled.setConstructiveFeedback(constructiveFeedback);
				scheduled.setProfileRelevance(schedule.getProfileRelevance());
				scheduled.setCommunicationRating(schedule.getCommunicationRating());
				scheduled.setReadyToRelocate(schedule.getReadyToRelocate());
				scheduled.setAgreement(schedule.getAgreement());
				cand.setRoundStatus("Selected");
				scheduleRepository.save(scheduled);
				cr.save(cand);
				scheduleRepository.getnextRoundListOfCandidate(scheduledInterviewId);
				Round r = rl.findById(roundDetails).get();
				employee en = empRepo.findByEmpId(scheduled.getInterviewerEmployeeId());
				profiledetails pd = pdr.findByProfileId(schedule.getProfileId());
				InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber());
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				d.setRoundName(r.getRoundName());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());
				ExecutorService executor = Executors.newWorkStealingPool();
				executor.execute(() -> {
					emailservice.emailForSalaryDetails(scheduled,hr, cand.getEmailAddress());
					emailservice.selectedCandidateEmailToHR(scheduled, d, hr, hr.getEmail());
					
				});	
			}else if(roundStatus.trim().equals("On-hold")) {
				scheduled.setConstructiveFeedback(constructiveFeedback);
				scheduled.setCandidateUniqueNumber(schedule.getCandidateUniqueNumber());
				scheduled.setCandidateFullName(schedule.getCandidateFullName());
				scheduled.setRoundStatus("On-hold");
				cand.setRoundStatus("On-hold");
				scheduleRepository.save(scheduled);
				cr.save(cand);
				Round r = rl.findById(roundDetails).get();
				employee en = empRepo.findByEmpId(scheduled.getInterviewerEmployeeId());
				profiledetails pd = pdr.findByProfileId(schedule.getProfileId());
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());
				d.setRoundName(r.getRoundName());
				
				InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber());
				ExecutorService executor = Executors.newWorkStealingPool();
				executor.execute(() -> {
					emailservice.onHoldCandidateEmailToHR(scheduled, hr, d, hr.getEmail());
				});
				 
			}
			else {
				scheduled.setRoundStatus("Rejected");
				cand.setRoundStatus("Rejected");
				scheduled.setConstructiveFeedback(constructiveFeedback);
				scheduled.setCommunicationRating(schedule.getCommunicationRating());
				InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber()); 
				Round r = rl.findById(roundDetails).get();
				employee en = empRepo.findByEmpId(scheduled.getInterviewerEmployeeId());
				profiledetails pd = pdr.findByProfileId(schedule.getProfileId());
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				d.setProfile(pd.getProfile());
				d.setRoundName(r.getRoundName());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());
				scheduleRepository.save(scheduled);
				cr.save(cand);
				ExecutorService executor = Executors.newWorkStealingPool();
				executor.execute(() -> {
					emailservice.rejectionEmailToCandidate(scheduled, d, hr, cand.getEmailAddress());
					emailservice.rejectedCandidateEmailToHR(scheduled, d, hr, hr.getEmail());
				});
			}
		}catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		Integer is =  scheduleRepository.getCurrentScheduledID(schedule.getCandidateUniqueNumber());
		return ResponseEntity.ok(is);
	}
	
	//[final selection round]
	@PutMapping("/schedule-details-final-round/{scheduledInterviewId}/{roundDetails}/{roundStatus}/{constructiveFeedback}")
	public ResponseEntity<Object> updateScheduleDetails(@PathVariable Long scheduledInterviewId,
			@PathVariable Integer roundDetails, @PathVariable String roundStatus, @PathVariable String constructiveFeedback,
			@RequestBody ScheduledInterview schedule) {
		ScheduledInterview scheduled = scheduleRepository.findByScheduledInterviewId(scheduledInterviewId);
		candidate cand = cr.findByCandidateUniqueNumber(scheduled.getCandidateUniqueNumber());
		try {
			  if(roundStatus.equals("Selected")){
				scheduled.setRoundStatus("Selected");
				scheduled.setConstructiveFeedback(constructiveFeedback);
				scheduled.setProfileRelevance(schedule.getProfileRelevance());
				scheduled.setCommunicationRating(schedule.getCommunicationRating());
				scheduled.setReadyToRelocate(schedule.getReadyToRelocate());
				scheduled.setAgreement(schedule.getAgreement());
				cand.setRoundStatus("Selected");
				scheduleRepository.save(scheduled);
				cr.save(cand);
				profiledetails pd = pdr.findByProfileId(schedule.getProfileId());
				employee en = empRepo.findByEmpId(scheduled.getInterviewerEmployeeId());
				InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber());
				Round r = rl.findById(roundDetails).get();
				InterviewScheduleIdDto d = new InterviewScheduleIdDto();
				d.setRoundName(r.getRoundName());
				d.setProfile(pd.getProfile());
				d.setProfile(pd.getProfile());
				d.setFullName(en.getFullName());
				d.setEmpPhone(en.getEmpPhone());
				d.setEmail(en.getEmail());
				ExecutorService executor = Executors.newWorkStealingPool();
				executor.execute(() -> {
				    emailservice.emailForSalaryDetails(scheduled,hr, cand.getEmailAddress());
					emailservice.selectedCandidateEmailToHR(scheduled, d, hr, hr.getEmail());
				});	
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
		
	    
}
