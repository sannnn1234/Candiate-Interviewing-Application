/*
 * Author:Sandeep Gupta
 * Date:17 April 2023
 * Version:0.0.1
 */
package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hr.dto.CandidateExperianceDto;
import com.hr.model.candidate;
import com.hr.model.onboarding;
import com.hr.repository.OnboardingRepository;
import com.hr.repository.candidaterepository;

@RestController
@CrossOrigin("${port}")
public class OnboardingController {
	
	@Autowired
	public OnboardingRepository onboardingRepository;
	
	@Autowired
    public candidaterepository cr;
	
	//get all onboarding candidate list
	@GetMapping("/onboarding")
	public List<onboarding> getAllCandidateOnboardingList(){
		return (List<onboarding>) onboardingRepository.findAll();
		
	}
	
	//get all onboarding candidate list selected
		@GetMapping("/onboarding-selected")
		public List<CandidateExperianceDto> getCandidateOnboardingList(){
			return (List<CandidateExperianceDto>) onboardingRepository.getOnboardingCandidate();
			
		}
	
	
	//Create onboarding candidate rest api
	@PostMapping(value = "/onboarding")
	public  ResponseEntity<Object> onboardingCandidate(@RequestBody onboarding onboard) {
		candidate cand = cr.findByCandidateUniqueNumber(onboard.getCandidateUniqueNumber());
		
		onboard.setCandidateUniqueNumber(cand.getCandidateUniqueNumber());
		onboard.setCandidateFullName(cand.getCandidateFullName());
		onboard.setIsSubmittedDocument(onboard.getIsSubmittedDocument());
		onboardingRepository.save(onboard);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	//get onboarding candidate details by Id rest api
	@GetMapping("/onboarding/{onboardingId}")
	public ResponseEntity<onboarding> getOnBoardingCandidateById(@PathVariable Long onboardingId) {
		onboarding onboard = onboardingRepository.findById(onboardingId).get();
		return ResponseEntity.ok(onboard);
	}

	
	// update onboarding candidate details rest api
	@PutMapping("/onboarding/{onboardingId}")
	public ResponseEntity<onboarding> updateOnbaordingCandidatelist(@PathVariable Long onboardingId,@RequestBody onboarding ob){
		onboarding onboard = onboardingRepository.findById(onboardingId).get();
		onboard.setCandidateUniqueNumber(ob.getCandidateUniqueNumber());
		onboard.setCandidateFullName(ob.getCandidateFullName());
		onboard.setExperienceLevel(ob.getExperienceLevel());
		onboard.setAllMarkSheets(ob.getAllMarkSheets());
		onboard.setResidenceProof(ob.getResidenceProof());
		onboard.setPhotoIdProof(ob.getPhotoIdProof());
		onboard.setPassportPhotograph(ob.getPassportPhotograph());
		onboard.setRelievingLetterCurrentOrg(ob.getRelievingLetterCurrentOrg());
		onboard.setEarlierJobRelievingLetter(ob.getEarlierJobRelievingLetter());
		onboard.setLastThreeMonthsPayslips(ob.getLastThreeMonthsPayslips());
		onboard.setCanceledCheque(ob.getCanceledCheque());
		
		onboard.setAllMarkSheetsDate(ob.getAllMarkSheetsDate());
		onboard.setResidenceProofDate(ob.getResidenceProofDate());
		onboard.setPhotoIdProofDate(ob.getPhotoIdProofDate());
		onboard.setPassportPhotographDate(ob.getPassportPhotographDate());
		onboard.setRelievingLetterCurrentOrgDate(ob.getRelievingLetterCurrentOrgDate());
		onboard.setLastThreeMonthsPayslipsDate(ob.getLastThreeMonthsPayslipsDate());
		onboard.setEarlierJobRelievingLetterDate(ob.getEarlierJobRelievingLetterDate());
		onboard.setCanceledChequeDate(ob.getCanceledChequeDate());
	
	
		      
	
		
		onboarding updateOnbaordingCandidatelist = onboardingRepository.save(onboard);
		return ResponseEntity.ok(updateOnbaordingCandidatelist);
	
	

	}
	
}
