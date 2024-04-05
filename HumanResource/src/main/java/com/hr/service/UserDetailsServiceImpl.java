package com.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hr.model.employee;
import com.hr.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		employee emp = this.employeeRepository.findByEmpId(Long.parseLong(s));
		if (emp == null) {
			System.out.println("User not found exception");
			throw new UsernameNotFoundException("No user found");
		}
		return emp;
	}
}