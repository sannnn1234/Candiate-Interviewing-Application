/*
 * Author:Sandeep Gupta
 * Date:28 December 2022
 * Version:0.0.1
 */
package com.hr.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.model.employee;
import com.hr.repository.EmployeeRepository;
import com.hr.service.ProgramService;


@RestController
@CrossOrigin("${port}")
public class LoginController {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	ProgramService pr;
	
	//create the login page
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody employee emp) {
		employee empl = employeeRepository.findByEmpIdAndPassword(emp.getEmpId(), emp.getPassword());
		if (empl != null) {
			return ResponseEntity.ok(empl);
		} else {
			return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}

	}

	
	@GetMapping(value = { "/login", "" })
	public String login(HttpServletRequest request) {
		return "login";
	}

	@SuppressWarnings("unused")
	@RequestMapping("/login-success")
	public String index(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		employee ur = (employee) principal;
		String groupCode = employeeRepository.findgrpCodeByEmpId(ur.getEmpId());
		session.setAttribute("empId", ur.getEmpId());
		session.setAttribute("empName", ur.getFullName());
//		session.setAttribute("menus", pr.findAllMenus(Long.parseLong(groupCode)));
		return "redirect:/home";
	}

	@GetMapping("/login-error")
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		String errorMessage = null;
		if (session != null) {
			AuthenticationException ex = (AuthenticationException) session
					.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessage = ex.getMessage();
			}
		}
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}
}
