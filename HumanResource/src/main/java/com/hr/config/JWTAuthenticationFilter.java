package com.hr.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hr.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Autowired
	private JWTUtils jWTUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requwstTokenHeader = request.getHeader("Authorization");
//		System.out.println(requwstTokenHeader);
		String empId = null;
		String jwtToken = null;
//		System.out.println(requwstTokenHeader);
		if (requwstTokenHeader != null && requwstTokenHeader.startsWith("Bearer ")) {

			jwtToken = requwstTokenHeader.substring(7);
			try {
				empId = this.jWTUtils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
//				System.out.println("Token expired");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occured");
			}
		} else {
			System.out.println("Invalid token don't start with bearers");
		}
		if (empId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.userDetailService.loadUserByUsername(empId);

			if (this.jWTUtils.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}

		} else {
			System.out.println("Token not valid");
		}
		filterChain.doFilter(request, response);

	}

}
