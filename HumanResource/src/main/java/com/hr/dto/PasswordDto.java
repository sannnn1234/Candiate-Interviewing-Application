package com.hr.dto;

public class PasswordDto {

	private String oldPassword;

	private String token;

	private String newPassword;

	public PasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordDto(String oldPassword, String token, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.token = token;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordDto [oldPassword=" + oldPassword + ", token=" + token + ", newPassword=" + newPassword + "]";
	}
	
	

}
