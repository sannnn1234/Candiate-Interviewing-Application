package com.hr.dto;

public class UserDto {

	
	private Long groupCode;

	public UserDto() {
		super();
		
	}

	public UserDto(Long groupCode) {
		super();
		this.groupCode = groupCode;
	}

	public Long getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Long groupCode) {
		this.groupCode = groupCode;
	}

	@Override
	public String toString() {
		return "UserDto [groupCode=" + groupCode + "]";
	}
	
	
	
}
