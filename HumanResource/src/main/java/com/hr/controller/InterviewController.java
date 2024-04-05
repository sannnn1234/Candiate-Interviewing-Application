/*
 * Author:Sandeep Gupta
 * Date:28 December 2022
 * Version:0.0.1
 */
package com.hr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.Interview;
import com.hr.repository.InterviewRepository;


@RestController
@CrossOrigin("${port}")
public class InterviewController {
	
	@Autowired
	private InterviewRepository interviewrepo;
	
	
	    //get all interview information 
		@GetMapping("/interview")
		public List<Interview> getAllInterviewInformation(){
			return (List<Interview>) interviewrepo.findAll();
		}
		
		//create  mode information
		@PostMapping("/interview")
		public ResponseEntity<Interview> departmentInfo(@RequestBody Interview interview)
		{
			return ResponseEntity.ok(interviewrepo.save(interview));
		}
		
		//get department interview  information by Id rest api
		@GetMapping("/interview/{interviewId}")
		public ResponseEntity<Interview> getInterviewInformationById(@PathVariable Integer interviewId) {
			Interview interview = interviewrepo.findById(interviewId).get();
//			interview.setVideoLinkDetails("<a href='" +interview.getVideoLinkDetails() +"' >"+  interview.getVideoLinkDetails()+"</a>");
			return ResponseEntity.ok(interview);
		}
				
		//update interview  information rest api
		@PutMapping("/interview/{interviewId}")
		public ResponseEntity<Interview> updatedInterviewInfo(@PathVariable Integer interviewId,@RequestBody Interview interview){
			Interview interviewinfo = interviewrepo.findById(interviewId).get();
			interviewinfo.setModeNo(interview.getModeNo());
		
			
			Interview updatedInterviewInfo = interviewrepo.save(interview);
			return ResponseEntity.ok(updatedInterviewInfo);
		}
		
		// delete interviewer  information rest api
		@DeleteMapping("/interview/{interviewId}")
		public ResponseEntity<Map<String, Boolean>> deleteInterviewInfo(@PathVariable Integer interviewId){
			Interview interviewinfo = interviewrepo.findById(interviewId).get();
			interviewrepo.delete(interviewinfo);
			Map<String, Boolean> response = new HashMap<String, Boolean>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
