/*
 * Author:Sandeep Gupta
 * Date:13 Feb 2023
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.dto.DepartmentProfileDto;
import com.hr.model.DepartmentProfile;
import com.hr.repository.DepartProfileRepository;



@RestController
@CrossOrigin("${port}")
public class DepartmentProfileController {


	@Autowired
	public DepartProfileRepository dr;
	


		
	//get all department and profile details
	@GetMapping("/department-profile-details")
	public List<DepartmentProfileDto> getAllDepartmentProfileDetails(){
		return (List<DepartmentProfileDto>) dr.getDepartmentProfileDetails();
	}
	
	//get all department and profile details
	@GetMapping("/department-profile-mapping")
	public List<DepartmentProfileDto> getAllDepartmentProfileMapping(@RequestParam Integer departmentId){
		return (List<DepartmentProfileDto>) dr.getDepartmentProfileMapping(departmentId);
	}

	//Create  department and profile details rest api
	@PostMapping(value = "/department-profile-details")
	public ResponseEntity<Object> departmentProfileDetails(@RequestBody DepartmentProfile dp) {
     	try {
     		dr.save(dp);
     	}catch(Exception ex) {
     		System.err.println(ex.getMessage());
     		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     	}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}

	//get department and profile details by Id rest api
	@GetMapping("/department-profile-details/{departmentProfileId}")
	public ResponseEntity<DepartmentProfileDto> getDepartmentprofileDetailById(@PathVariable Long departmentProfileId) {
		DepartmentProfileDto depart = dr.getDepartmentProfileDetailsById(departmentProfileId).get(0);
		return ResponseEntity.ok(depart);
	}
	
    // update department and profile details  rest api
	@PutMapping("/department-profile-details/{departmentProfileId}")
	public ResponseEntity<DepartmentProfile> updatedepartmentprofileDetails(@PathVariable Long departmentProfileId,@RequestBody DepartmentProfile department){
		try {
			DepartmentProfile departmentpro = dr.findById(departmentProfileId).get();
			departmentpro.setDepartmentId(department.getDepartmentId());
			departmentpro.setProfileId(department.getProfileId());
			departmentpro.setActive(department.getActive());
			dr.save(department);
			
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
}
