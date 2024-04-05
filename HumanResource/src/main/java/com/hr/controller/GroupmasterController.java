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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hr.model.Groupmaster;
import com.hr.repository.GroupmasterRepository;

@RestController
@CrossOrigin("${port}")
public class GroupmasterController {
	
	
	@Autowired
	public GroupmasterRepository groupmasterRepository;
	
	//get all groupmasterDetails
	@GetMapping("/groupmaster-details")
	public List<Groupmaster> getAllGroupmasterDetails(){
		return (List<Groupmaster>) groupmasterRepository.findAll();
		
	}
	
	//get all groupmasterDetails
	@GetMapping("/groupmaster-list")
	public List<Groupmaster> getAllGroupmasterList(){
		return (List<Groupmaster>) groupmasterRepository.getGroupMasterList();
		
	}
	
	//Create group master details rest api
	@PostMapping(value = "/groupmaster-details")
	public  ResponseEntity<Object> groupmasterDetails(@RequestBody Groupmaster groupmaster) {
		try {
			groupmasterRepository.save(groupmaster);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	   return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	//get group master  details by Id rest api
	@GetMapping("/groupmaster-details/{groupCode}")
	public ResponseEntity<Groupmaster> getGroupmasterDetailsById(@PathVariable Long groupCode) {
		Groupmaster groupmaster = groupmasterRepository.findById(groupCode).get();
		return ResponseEntity.ok(groupmaster);
	}
	
    // update group master details rest api
	@PutMapping("/groupmaster-details/{groupCode}")
	public ResponseEntity<Groupmaster> updateGroupmasterDetails(@PathVariable Long groupCode, @RequestBody Groupmaster groupmaster){
		try {
			Groupmaster groupmast = groupmasterRepository.findById(groupCode).get();
			groupmast.setGroupdesc(groupmaster.getGroupdesc());
			groupmast.setActive(groupmaster.getActive());
			groupmasterRepository.save(groupmast);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	// delete group master details rest api
	@DeleteMapping("/groupmaster-details/{groupCode}")
	public ResponseEntity<Map<String, Boolean>> deleteVacancyDetails(@PathVariable Long groupCode){
		Groupmaster groupmast = groupmasterRepository.findById(groupCode).get();
		groupmasterRepository.delete(groupmast);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}



