package com.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.model.PasswordResetToken;
import com.hr.model.employee;
import com.hr.repository.PasswordResetTokenRepository;

@Service
public class EmployeeService {

	@Autowired
	PasswordResetTokenRepository pr;
	public void createPasswordResetTokenForUser(employee user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		pr.save(myToken);
	}
}
