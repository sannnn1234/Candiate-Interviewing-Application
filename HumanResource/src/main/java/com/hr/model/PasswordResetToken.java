package com.hr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {

	private static final int EXPIRATION = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	@OneToOne(targetEntity = employee.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "emp_id")
	private employee emp;

	private Date expiryDate;

	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(String token, employee emp) {
		super();
		this.token = token;
		this.emp = emp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public employee getEmp() {
		return emp;
	}

	public void setEmp(employee emp) {
		this.emp = emp;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", emp=" + emp + ", expiryDate=" + expiryDate
				+ "]";
	}

}