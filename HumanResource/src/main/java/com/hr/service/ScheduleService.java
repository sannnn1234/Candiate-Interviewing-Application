package com.hr.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hr.controller.FileUploadHelper;
import com.hr.dto.InterviewScheduleIdDto;
import com.hr.dto.InterviewerHrDto;
import com.hr.model.ScheduledInterview;
import com.hr.model.candidate;
import com.hr.model.employee;
import com.hr.model.profiledetails;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.InterviewRepository;
import com.hr.repository.ProfiledetailsRepository;
import com.hr.repository.ScheduleRepository;
import com.hr.repository.candidaterepository;

@Service
public class ScheduleService {

	@Autowired
	public ScheduleRepository scheduleRepository;

	@Autowired
	public candidaterepository cr;

	@Autowired
	public EmployeeRepository er;

	@Autowired
	public InterviewRepository ir;

	@Autowired
	public EmailService emailservice;
	@Autowired
	public ProfiledetailsRepository pdr;

	@Autowired
	public FileUploadHelper fileUploadHelper;

	// save the details scheduled candidate interview information
	public ResponseEntity<Object> getNameByCandidateUniqueNumber(MultipartFile file, ScheduledInterview s,
			String roundStatus, Integer roundDetails) {
		try {
			candidate scheduled = cr.findByCandidateUniqueNumber(s.getCandidateUniqueNumber());
			s.setCandidateFullName(scheduled.getCandidateFullName());
			s.setCandidateUniqueNumber(scheduled.getCandidateUniqueNumber());
			s.setCandidateResume(scheduled.getCandidateResume());
			try {
				if (file != null && !file.isEmpty()) {
					if (!file.getContentType().equals("text/calendar")) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
					} else {
						if (fileUploadHelper.uploadIcsFile(file, s)) {
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ExecutorService executor = Executors.newWorkStealingPool();
			employee e = er.findByEmpId(s.getInterviewerEmployeeId());
			profiledetails pd = pdr.findById(s.getProfileId()).get();
			InterviewerHrDto hr = cr.getHrList(scheduled.getCandidateUniqueNumber());
			InterviewScheduleIdDto d = new InterviewScheduleIdDto();
			d.setContactNumber(scheduled.getContactNumber());
			d.setCurrentSalary(scheduled.getCurrentSalary());
			d.setExpectedSalary(scheduled.getExpectedSalary());
			d.setRelevantExperience(scheduled.getRelevantExperience());
			d.setTotalExperience(scheduled.getTotalExperience());
			d.setNoticePeriod(scheduled.getNoticePeriod());
			d.setProfile(pd.getProfile());
			d.setFullName(e.getFullName());
			d.setEmpPhone(e.getEmpPhone());
			d.setEmail(e.getEmail());

			executor.execute(() -> {
				// HR sent an email to the candidate for scheduled interview
				emailservice.scheduleInteviewEmailToCandidate(s, d, hr, scheduled.getEmailAddress());
				// HR sent an email to the interviewer for scheduled interview
				emailservice.InteviewScheduleEmailToInterviewer(s, d, hr, e.getEmail());

			});
			scheduleRepository.save(s);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

}
