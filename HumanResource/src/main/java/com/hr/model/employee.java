package com.hr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "employee_master")
public class employee implements UserDetails {
	@Id
	@Column(name = "emp_id")
	private long empId;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "email")
	private String email;

	@Column(name = "emp_phone")
	private long empPhone;

	@Column(name = "password")
	private String password;

	@Column(name = "group_code")
	private long groupCode;
	
	@Column(name = "group_description")
	private String groupDescription;
	
	@Column(name="profile_id")
	private String profileId;
	
	@Column(name = "active")
	private String active;
	
	@OneToMany()
	@JoinTable(name = "employee_profile_mapping", joinColumns = @JoinColumn(name = "empId"), inverseJoinColumns = @JoinColumn(name = "profileId"))
	private List<profiledetails> profilemapping;
	private String permissions = "";

	public employee() {
		super();

	}

	
	

	public employee(long empId, String fullName, String email, long empPhone, String password, long groupCode,
			String groupDescription, String profileId, String active, List<profiledetails> profilemapping,
			String permissions) {
		super();
		this.empId = empId;
		this.fullName = fullName;
		this.email = email;
		this.empPhone = empPhone;
		this.password = password;
		this.groupCode = groupCode;
		this.groupDescription = groupDescription;
		this.profileId = profileId;
		this.active = active;
		this.profilemapping = profilemapping;
		this.permissions = permissions;
	}

    


	public long getEmpId() {
		return empId;
	}




	public void setEmpId(long empId) {
		this.empId = empId;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public long getEmpPhone() {
		return empPhone;
	}




	public void setEmpPhone(long empPhone) {
		this.empPhone = empPhone;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public long getGroupCode() {
		return groupCode;
	}




	public void setGroupCode(long groupCode) {
		this.groupCode = groupCode;
	}




	public String getGroupDescription() {
		return groupDescription;
	}




	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}




	public String getProfileId() {
		return profileId;
	}




	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}




	public String getActive() {
		return active;
	}




	public void setActive(String active) {
		this.active = active;
	}




	public List<profiledetails> getProfilemapping() {
		return profilemapping;
	}




	public void setProfilemapping(List<profiledetails> profilemapping) {
		this.profilemapping = profilemapping;
	}




	public String getPermissions() {
		return permissions;
	}




	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}


   

	@Override
	public String toString() {
		return "employee [empId=" + empId + ", fullName=" + fullName + ", email=" + email + ", empPhone=" + empPhone
				+ ", password=" + password + ", groupCode=" + groupCode + ", groupDescription=" + groupDescription
				+ ", profileId=" + profileId + ", active=" + active + ", profilemapping=" + profilemapping
				+ ", permissions=" + permissions + "]";
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Authority> set = new HashSet<>();
		List<GrantedAuthority> authorities = new ArrayList<>();

		// Extract list of permissions (name)
		this.getPermissionList().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authorities.add(authority);
		});

		// Extract list of roles (ROLE_name)
		this.getRoleList().forEach(r -> {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
			authorities.add(authority);
		});

		return null;
	}

	public List<String> getRoleList() {
//		one of them are false will not go to next
		try {
			
			if (this.groupDescription != null && this.groupDescription.length() > 0) {
				return Arrays.asList(this.groupDescription.split(","));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<String> getPermissionList() {
		if (this.permissions != null && this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}

	@Override
	public String getUsername() {
		return String.valueOf(empId);
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return active.equalsIgnoreCase("Y");
	}

}