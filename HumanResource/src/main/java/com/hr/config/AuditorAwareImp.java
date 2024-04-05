package com.hr.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hr.model.employee;

@Component("auditorAware")
public class AuditorAwareImp implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        employee ur = (employee) principal;
	        return Optional.of(ur.getEmpId());
	}
	
}
