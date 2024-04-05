/*
 * Author:Sandeep Gupta
 * Date:20 January 2022
 * Version:0.0.1
 */
package com.hr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.config.JWTUtils;
import com.hr.model.JWTRequest;
import com.hr.model.JWTResponse;
import com.hr.model.employee;
import com.hr.repository.EmployeeRepository;
import com.hr.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin("${port}")
public class AuthenticationController {

	@Autowired
	private JWTUtils jWTUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	public EmployeeRepository employeeRepository;

	// [generate token]
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest j) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(j.getEmpId(), j.getPassword()));
			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(j.getEmpId());
			String t = this.jWTUtils.generateToken(userDetails);
			return ResponseEntity.ok(new JWTResponse(t));
		} catch (DisabledException e) {
			 System.out.println("User is disabled: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is disabled");
		} catch (BadCredentialsException e) {
			System.out.println("Invalid credentials: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
		} catch (Exception e) {
			System.out.println("Error generating token: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error generating token");
		}
	}

	// [Return details of the current user]
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        try {
            Long empId = Long.parseLong(principal.getName());
            employee emp = this.employeeRepository.findByEmpId(empId);
            if (emp != null) {
                return ResponseEntity.ok(emp);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            System.err.println("Error fetching current user details: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching current user details");
        }
    }


}
