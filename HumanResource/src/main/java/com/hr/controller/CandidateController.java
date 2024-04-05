/*
 * Author:Sandeep Gupta
 * Date:28 December 2022
 * Version:0.0.1
 */
package com.hr.controller;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hr.dto.CandidateDto;
import com.hr.model.ScheduledInterview;
import com.hr.model.candidate;
import com.hr.repository.candidaterepository;

@RestController
@CrossOrigin("${port}")
public class CandidateController {

	@Autowired
	public candidaterepository candidaterepo;

	@Autowired
	public FileUploadHelper fileUploadHelper;

	@Value("${attachmentUrl}")
	String fileUrlPath;

	// [Get all candidate Information]
	@GetMapping("/candidate-information")
	public List<candidate> getAllCandidateInfo() {
		List<candidate> can = (List<candidate>) candidaterepo.findAll();
		try {
			can.forEach(e -> {
				String candidateResume = e.getCandidateResume();
				e.setCandidateResume(fileUrlPath + candidateResume);
			});
			can.sort(Comparator.comparingLong(candidate::getCandidateNo).reversed());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return can;
	}

	// [get all schedule details]
	@GetMapping("/candidate-information-candidate")
	public Long getTotalCandidate() {
		return candidaterepo.getCountTotalCandidate();

	}

	// [get all schedule details]
	@GetMapping("/candidate-information-schedule")
	public List<ScheduledInterview> getSchedule() {
		return candidaterepo.getSchedulelist();

	}

	// [get all schedule details]
	@GetMapping("/candidate-information-location")
	public List<String> getListOfLocation() {
		return candidaterepo.getListOfLocation();

	}

	// [get all schedule details]
	@GetMapping("/candidate-information-candidate-status")
	public List<CandidateDto> getCandidateByStatus() {
		return candidaterepo.getShorlistedCandidate();
	}

	// [Create candidate information rest api]
	@PostMapping(value = "/candidate-information")
	public ResponseEntity<Object> candidateInformation(
			@RequestParam(name = "file", required = false) MultipartFile file,
			@RequestParam("resumefile") MultipartFile resumefile, @ModelAttribute candidate cd) {

		// Three months validation while creating new candidate information
		Calendar calendar = Calendar.getInstance();
		Date currentDate = new Date();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, -3);
		// Get the updated date
		Date updatedDate = calendar.getTime();
		if (cd.getDateOfBirth() == null) {
			if (candidaterepo.existsByCandidateFullNameAndCreatedTimeGreaterThanEqual(cd.getCandidateFullName(),
					updatedDate)) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		if (candidaterepo.existsByCandidateFullNameAndDateOfBirthAndCreatedTimeGreaterThanEqual(
				cd.getCandidateFullName(), cd.getDateOfBirth(), updatedDate)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			cd.setRoundStatus("Created");
			candidate dbCandidate = candidaterepo.save(cd);

			if (dbCandidate.getCandidateUniqueNumber() == null || dbCandidate.getCandidateUniqueNumber().equals("")
					|| dbCandidate.getCandidateUniqueNumber().equals("undefined")) {
				StringBuilder candidateId = new StringBuilder("HRCAN");
				String ll = dbCandidate.getCandidateNo() + "";
				int len = 8 - ll.length();
				for (int i = 1; i <= len; i++) {
					candidateId.append("0");

				}
				candidateId.append(dbCandidate.getCandidateNo());
				dbCandidate.setCandidateUniqueNumber(candidateId.toString());
			}
			try {
				if (file != null && !file.isEmpty()) {
					if (!file.getContentType().equals("application/pdf") && !file.getContentType().equals("image/jpeg")
							&& !file.getContentType().equals("image/png")) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
					} else {
						if (fileUploadHelper.uploadFile(file, dbCandidate)) {

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (!resumefile.isEmpty()) {
					if (!resumefile.getContentType().equals("application/pdf")) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
					} else if (resumefile.getSize() > 2 * 1024 * 1024) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(4);
					} else {
						if (fileUploadHelper.uploadFile1(resumefile, dbCandidate)) {
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

			candidaterepo.save(dbCandidate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}

	// [get candidate information by Id rest api]
	@GetMapping("/candidate-information/{candidateNo}")
	public ResponseEntity<candidate> getCandidateInfoById(@PathVariable Long candidateNo) {
		candidate can = candidaterepo.findById(candidateNo).get();
		String candidateResume = can.getCandidateResume();
		can.setCandidateResume(fileUrlPath + candidateResume);
		return ResponseEntity.ok(can);
	}

	// [update candidate information rest api]
	@PutMapping(value = "/candidate-information/{candidateNo}")
	public ResponseEntity<Object> updatedCandidateInfo(@PathVariable Long candidateNo, @ModelAttribute candidate cand,
			@RequestParam(name = "dateOfBirth", required = false, defaultValue = "") String dateOfBirth,
			@RequestParam(name = "resumefile", required = false) MultipartFile resumefile) {
		try {
			candidate candidate = candidaterepo.findById(candidateNo).get();
			candidate.setCandidateUniqueNumber(cand.getCandidateUniqueNumber());
			candidate.setCandidateFullName(cand.getCandidateFullName());
			candidate.setDateOfBirth(cand.getDateOfBirth());
			candidate.setCandidateImage(cand.getCandidateImage());
			candidate.setContactNumber(cand.getContactNumber());
			candidate.setEmailAddress(cand.getEmailAddress());
			candidate.setProfilePreference1(cand.getProfilePreference1());
			candidate.setProfilePreference2(cand.getProfilePreference2());
			candidate.setTotalExperience(cand.getTotalExperience());
			candidate.setKeySkills(cand.getKeySkills());
			candidate.setCurrentSalary(cand.getCurrentSalary());
			candidate.setExpectedSalary(cand.getExpectedSalary());
			candidate.setCurrentLocation(cand.getCurrentLocation());
			candidate.setLocationPreference(cand.getLocationPreference());
			candidate.setRelevantExperience(cand.getRelevantExperience());
			candidate.setNoticePeriod(cand.getNoticePeriod());
			candidate.setReadyToNegotiate(cand.getReadyToNegotiate());

			if (resumefile != null && !resumefile.isEmpty()) {
				if (!resumefile.getContentType().equals("application/pdf")) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
				} else if (resumefile.getSize() > 2 * 1024 * 1024) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(4);
				} else {
					System.out.println(resumefile);
					if (fileUploadHelper.uploadFile1(resumefile, candidate)) {
					}
				}
			}
			candidaterepo.save(candidate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}

	// [update joining date in candidate information rest api]
	@PutMapping("/candidate-information-joiningdate/{candidateUniqueNumber}")
	public ResponseEntity<candidate> updatedJoiningDate(@PathVariable String candidateUniqueNumber,
			@RequestBody candidate cand) {
		candidate candidate = candidaterepo.findByCandidateUniqueNumber(candidateUniqueNumber);
		candidate.setJoiningDate(cand.getJoiningDate());
		candidate.setJoiningFeedback(cand.getJoiningFeedback());
		candidate updatedCandidateInfo = candidaterepo.save(candidate);
		return ResponseEntity.ok(updatedCandidateInfo);
	}

	// delete vacancy details rest api
	@DeleteMapping("/candidate-information/{candidateNo}")
	public ResponseEntity<Map<String, Boolean>> deleteCandidateInfo(@PathVariable Long candidateNo) {
		candidate candidate = candidaterepo.findById(candidateNo).get();
		candidaterepo.delete(candidate);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
