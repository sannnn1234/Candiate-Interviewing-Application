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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.vacancydetails;
import com.hr.repository.vacancyrepository;


@RestController
@CrossOrigin("${port}")
public class VacancyController {

		@Autowired
		public vacancyrepository vr;
		
		//get all vacancyDetails
		@GetMapping("/vacancy-details")
		public List<vacancydetails> getAllVacancyDetails(){
			return (List<vacancydetails>) vr.findAll();
			
		}
	
		//Create vacancyDetails rest api
		@PostMapping(value = "/vacancy-details")
		public  ResponseEntity<Object> vacancyDetails(@RequestBody vacancydetails vd) {
	     	vr.save(vd);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
	
		//get vacancy details by Id rest api
		@GetMapping("/vacancy-details/{vacancyId}")
		public ResponseEntity<vacancydetails> getVacancyDetailsById(@PathVariable Long vacancyId) {
			vacancydetails vacancy = vr.findById(vacancyId).get();
			return ResponseEntity.ok(vacancy);
		}
		
	    // update vacancy details rest api
		@PutMapping("/vacancy-details/{vacancyId}")
		public ResponseEntity<vacancydetails> updateVacancyDetails(@PathVariable Long vacancyId,@RequestBody vacancydetails vacancy){
			vacancydetails vacancydet = vr.findById(vacancyId).get();
			vacancydet.setDepartment(vacancy.getDepartment());
			vacancydet.setProfile(vacancy.getProfile());
			vacancydet.setPosition(vacancy.getPosition());
			vacancydet.setExperience(vacancy.getExperience());
			vacancydet.setTarget(vacancy.getTarget());
			vacancydet.setPositionsFilled(vacancy.getPositionsFilled());
			vacancydet.setLocation(vacancy.getLocation());
			vacancydet.setYear(vacancy.getYear());
			vacancydet.setMaxSalary(vacancy.getMaxSalary());
			
			vacancydetails updatedVacancyDetail = vr.save(vacancy);
			return ResponseEntity.ok(updatedVacancyDetail);
		}
		
		// delete vacancy details rest api
		@DeleteMapping("/vacancy-details/{vacancyId}")
		public ResponseEntity<Map<String, Boolean>> deleteVacancyDetails(@PathVariable Long vacancyId){
			vacancydetails vacancy = vr.findById(vacancyId).get();
			vr.delete(vacancy);
			Map<String, Boolean> response = new HashMap<String, Boolean>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
