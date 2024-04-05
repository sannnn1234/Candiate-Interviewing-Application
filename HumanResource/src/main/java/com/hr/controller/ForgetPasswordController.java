/*
 * Author:Sandeep Gupta
 * Date:6 January 2023
 * Version:0.0.1
 */
package com.hr.controller;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.PasswordResetToken;
import com.hr.model.employee;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.PasswordResetTokenRepository;
import com.hr.service.EmailService;
import com.hr.service.EmployeeService;

@RestController
@CrossOrigin("${port}")
public class ForgetPasswordController {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public EmailService emailService;

	@Autowired
	EmployeeService es;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	PasswordResetTokenRepository pr;

	/*
	 * Forget Password Sending mail
	 */
	@PostMapping(value = "/forget-password")
	public ResponseEntity<Object> getForgetPassPost(@RequestParam("email") String email) {
		try {
			Long empId = employeeRepository.findByEmpEmailIgnoreCase(email);
			if (empId != null) {
				String token = String.valueOf(OTP(6));
				employee emp = employeeRepository.findById(empId).get();
				es.createPasswordResetTokenForUser(emp, token);
				emailService.otpMail(email, token);
				return ResponseEntity.ok().build();
			} else {
				// Multiple users found for the given email, handle accordingly
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(2);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(1);
		}

	}

	/*
	 * Forget Password Verify OTP
	 */
	@PostMapping(value = "/verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestParam("otp") String otp) {
		PasswordResetToken obToken = pr.findByToken(otp);
		if (obToken != null) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	/*
	 * Change password page
	 */
	@PostMapping(value = "/change-password")
	public ResponseEntity<?> getForgetPasswordChagePass(@RequestParam("password") String password,
			@RequestParam("otp") String otp) {
		PasswordResetToken obToken = pr.findByToken(otp);
		if (obToken != null) {
			employeeRepository.updateUserPassword(obToken.getEmp().getEmpId(), bCryptPasswordEncoder.encode(password));
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	/*
	 * Generate OTP
	 */
	static char[] OTP(int len) {
		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[len];
		for (int i = 0; i < len; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		return otp;
	}

}
