/*
 * Author:Sandeep Gupta
 * Date:6 January 2023
 * Version:0.0.1
 */
package com.hr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.Groupmaster;
import com.hr.model.employee;

import com.hr.repository.EmployeeRepository;
import com.hr.repository.GroupmasterRepository;

@RestController
@CrossOrigin("${port}")
public class EmployeeController {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public GroupmasterRepository groupmasterRepository;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	// get all employee details
	@GetMapping("/employee-details")
	public List<employee> getAllEmployeeDetails() {
		return (List<employee>) employeeRepository.findAll();

	}

	// Create employe details rest api
	@PostMapping(value = "/employee-details")
	public ResponseEntity<Object> employeeDetails(@RequestBody employee ed) {
       try {
    	   Groupmaster gr = groupmasterRepository.findByGroupdesc(ed.getGroupDescription());
    	   ed.setGroupCode(gr.getGroupCode());
    	   // ecryptpassword
    	   ed.setPassword(bCryptPasswordEncoder.encode(ed.getPassword()));
    	   employeeRepository.save(ed);
       }catch(Exception e) {
    	   System.err.println(e.getMessage());
    	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
	  return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	// get employee details by Id rest api
	@GetMapping("/employee-details/{empId}")
	public ResponseEntity<employee> getEmployeeDetailsById(@PathVariable Long empId) {
		employee emp = employeeRepository.findById(empId).get();
		return ResponseEntity.ok(emp);
	}

	// update employee details rest api
	@PutMapping("/employee-details/{empId}")
	public ResponseEntity<employee> updateEmployeeDetails(@PathVariable Long empId, @RequestBody employee emp) {
		try {
			employee empl = employeeRepository.findById(empId).get();
			Groupmaster gr = groupmasterRepository.findByGroupdesc(emp.getGroupDescription());
			empl.setFullName(emp.getFullName());
			empl.setEmail(emp.getEmail());
			empl.setEmpPhone(emp.getEmpPhone());
			empl.setActive(emp.getActive());
			empl.setGroupDescription(emp.getGroupDescription());
			empl.setGroupCode(gr.getGroupCode());
			empl.setProfileId(emp.getProfileId());
			empl.setProfilemapping(emp.getProfilemapping());
			empl.setPassword(emp.getPassword());
			employeeRepository.save(empl);
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	// delete employee details rest api
	@DeleteMapping("/employee-details/{empId}")
	public ResponseEntity<Map<String, Boolean>> deleteVacancyDetails(@PathVariable Long empId) {
		employee employee = employeeRepository.findById(empId).get();
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
