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
import com.hr.dto.RoundListDto;
import com.hr.dto.SelectionCriteriaDto;
import com.hr.model.department;
import com.hr.repository.departmentroundrepository;

@RestController
@CrossOrigin("${port}")
public class SelectionCriteriaController {

	@Autowired
	public departmentroundrepository departmentrepo;

	// get all department round information
	@GetMapping("/department")
	public List<department> getAllDepartmnet() {
		return (List<department>) departmentrepo.findAll();
	}
	
	// get all selection criteria list
	@GetMapping("/department-selection-list")
	public List<SelectionCriteriaDto> getSelectionCriteria() {
		return departmentrepo.getSelectionCriteriaDetails();
	}

	// get all round List
	@GetMapping("/department-round")
	public List<RoundListDto> getRoundDetails() {
		return departmentrepo.getRoundDetails();
	}

	// create department round information rest api
	@PostMapping("/department")
	public ResponseEntity<Object> departmentInfo(@RequestBody department dept) {
		try {
			
			departmentrepo.save(dept);
		}catch(Exception ex) {
		     System.err.println(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}

	// get department round information by Id rest api
	@GetMapping("/department/{itemNo}")
	public ResponseEntity<department> getDepartmentById(@PathVariable Long itemNo) {
		department dept = departmentrepo.findById(itemNo).get();
		return ResponseEntity.ok(dept);
	}

	// update department round information rest api
	@PutMapping("/department/{itemNo}")
	public ResponseEntity<department> updatedDepartmentRound(@PathVariable Long itemNo, @RequestBody department dept) {
		try {
			department dep = departmentrepo.findById(itemNo).get();
			dep.setDepartmentId(dept.getDepartmentId());
			dep.setProfileId(dept.getProfileId());
			dep.setRoundNo(dept.getRoundNo());
			dep.setInterviewLengthMins(dept.getInterviewLengthMins());
			dep.setRound(dept.getRound());
			dep.setActive(dept.getActive());
		    departmentrepo.save(dep);
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}

	// delete department round information rest api
	@DeleteMapping("/department/{itemNo}")
	public ResponseEntity<Map<String, Boolean>> deleteDepartmentRound(@PathVariable Long itemNo) {
		department department = departmentrepo.findById(itemNo).get();
		departmentrepo.delete(department);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
