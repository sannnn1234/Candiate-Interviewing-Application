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
import com.hr.model.departmentdetails;
import com.hr.repository.DepartmentdetailsRepository;


@RestController
@CrossOrigin("${port}")
public class DepartmentdetailsController {
	@Autowired
	public DepartmentdetailsRepository departmentdetailsRepository;

	// get all department  information
	@GetMapping("/department-details")
	public List<departmentdetails> getDepartmnetList() {
		return (List<departmentdetails>) departmentdetailsRepository.findAllByOrderByDepartmentId();
	}

	// get all department  information
	@GetMapping("/department-list")
	public List<departmentdetails> getDepartmnetDetailsList() {
		return (List<departmentdetails>) departmentdetailsRepository.getDepartmentDetailsList();
	}

	

	// create department  rest api
	@PostMapping("/department-details")
	public ResponseEntity<Object> departmentDetails(@RequestBody departmentdetails dept) {
         try{
        	 departmentdetailsRepository.save(dept);
         
         }catch(Exception ex){
        	 System.err.println(ex.getMessage());
        	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	//get department and profile details by Id rest api
	@GetMapping("/department-details/{departmentId}")
	public ResponseEntity<departmentdetails> getDepartmentDetailsById(@PathVariable int departmentId) {
		departmentdetails depart = departmentdetailsRepository.findById(departmentId).get();
		return ResponseEntity.ok(depart);
	}
	
	// update department information rest api
	@PutMapping("/department-details/{departmentId}")
	public ResponseEntity<departmentdetails> updatedDepartmentDetails(@PathVariable int departmentId, @RequestBody departmentdetails dept) {
		try {		
			departmentdetails dep = departmentdetailsRepository.findById(departmentId).get();
			dep.setDepartment(dept.getDepartment());
			dep.setActive(dept.getActive());
			departmentdetailsRepository.save(dep);	
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	

}
