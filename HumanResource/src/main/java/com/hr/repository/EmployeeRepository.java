package com.hr.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.dto.UserDto;
import com.hr.model.employee;


@Repository
public interface EmployeeRepository extends CrudRepository<employee, Long> {

	public Optional<employee> findByEmpIdAndPassword(Long empId, String password);

	public employee findByEmpIdAndPassword(long empId, String password);
	
	public employee findByEmpId(Long empId);
	
//	public employee findByEmail(String email);
	@Query(value = "select emp_id  from employee_master s where s.email = :email limit 1 " , nativeQuery = true)
	public Long findByEmail(String email);
	

	@Query(value = "select emp_id from employee_master where lower(email) =  lower(:email)", nativeQuery = true)
	public Long findByEmpEmailIgnoreCase(@Param("email") String email);

	@Modifying
	@Transactional
	@Query(value = "update employee_master set password = :password where emp_id = :empId", nativeQuery = true)
	int updateUserPassword(@Param(value = "empId") long empId, @Param(value = "password") String password);


	@Query(value = "select  group_code from employee_master where emp_id =:empId", nativeQuery = true)
	public String findgrpCodeByEmpId(long empId);
	
	@Query("select new com.hr.dto.UserDto(e.groupCode) from employee e where e.empId =:empId")
	public List<UserDto> findEmployeegrpCodeByEmpId(Long empId);

}
