package com.hr.dto;

public class departmentWiseReportDto {

	private Integer departmentId;
	private String department;
	private Long count;
	
	public departmentWiseReportDto() {
		super();
	
	}

	public departmentWiseReportDto(Integer departmentId, String department, Long count) {
		super();
		this.departmentId = departmentId;
		this.department = department;
		this.count = count;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "departmentWiseReportDto [departmentId=" + departmentId + ", department=" + department + ", count="
				+ count + "]";
	}

	
	
	
}
