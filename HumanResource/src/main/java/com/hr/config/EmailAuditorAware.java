package com.hr.config;

import java.util.Optional;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hr.model.employee;


@Component
public class EmailAuditorAware {
	public Optional<String> getCurrentAuditor() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		employee ur = (employee) principal;
		return Optional.of(ur.getEmail());

	}
}
