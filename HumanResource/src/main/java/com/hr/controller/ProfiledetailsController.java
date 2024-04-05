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
import com.hr.model.profiledetails;
import com.hr.repository.ProfiledetailsRepository;

@RestController
@CrossOrigin("${port}")
public class ProfiledetailsController {

	@Autowired
	public ProfiledetailsRepository profiledetailsRepository;
	
	    // get all Profile  information
		@GetMapping("/profile")
		public List<profiledetails> getProfileList() {
			return (List<profiledetails>) profiledetailsRepository.findAllByOrderByProfileId();
		}
		
		

		// get all active profile list
		@GetMapping("/profile-list")
		public List<profiledetails> getProfileDetailsList() {
			return (List<profiledetails>) profiledetailsRepository.getProfileList();
		}


		// create Profile  rest api
		@PostMapping("/profile")
		public ResponseEntity<Object>profileDetails(@RequestBody profiledetails profile) {
           try {
        	   profiledetailsRepository.save(profile);
           }catch(Exception e) {
        	   System.err.println(e.getMessage());
        	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}

		//get profile  details by Id rest api
		@GetMapping("/profile/{profileId}")
		public ResponseEntity<profiledetails> getDepartmentDetailsById(@PathVariable int profileId) {
			profiledetails profile = profiledetailsRepository.findById(profileId).get();
			return ResponseEntity.ok(profile);
		}
		
		// update profile list rest api
		@PutMapping("/profile/{profileId}")
		public ResponseEntity<profiledetails> updatedProfileDetails(@PathVariable int profileId, @RequestBody profiledetails profile) {
			try {
				profiledetails pro = profiledetailsRepository.findById(profileId).get();
				pro.setProfile(profile.getProfile());
				pro.setActive(profile.getActive());
			    profiledetailsRepository.save(pro);
			}catch(Exception e) {
				System.err.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			
		}

		
}
