/*
 * Author:Sandeep Gupta
 * Date:7 July 2023
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
import com.hr.model.Agreement;
import com.hr.repository.AgreementRepository;


@RestController
@CrossOrigin("${port}")
public class AgreementController {
	
	@Autowired
	public AgreementRepository ar;
	
	//get all Agreement
	@GetMapping("/agreement")
	public List<Agreement> getAllAgreementList(){
	 return (List<Agreement>) ar.findAllByOrderByAgreementId();
		
	}
	
	//get all Agreement list
	@GetMapping("/agreement-list")
	public List<Agreement> getAgreementList(){
	 return (List<Agreement>) ar.getAgreementList();
		
	}

	//Create Agreement rest api
	@PostMapping(value = "/agreement")
	public ResponseEntity<Object> agreementList(@RequestBody Agreement ag) {
	try {
		ar.save(ag);
	}catch(Exception e) {
		System.err.println(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
	}

	//get agreement details by Id rest api
	@GetMapping("/agreement/{agreementId}")
	public ResponseEntity<Agreement> getAgreementListById(@PathVariable Integer agreementId) {
		Agreement agreement = ar.findById(agreementId).get();
		return ResponseEntity.ok(agreement);
	}
	
    // update agreement details rest api
	@PutMapping("/agreement/{agreementId}")
	public ResponseEntity<Agreement> updateAgreementList(@PathVariable Integer agreementId,@RequestBody Agreement ag){
		try {
			
			Agreement agreement = ar.findById(agreementId).get();
			agreement.setAgreement(ag.getAgreement());	
			agreement.setActive(ag.getActive());
			ar.save(agreement);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}
