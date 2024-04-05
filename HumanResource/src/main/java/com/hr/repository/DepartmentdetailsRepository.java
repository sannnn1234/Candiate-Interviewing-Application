package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hr.model.departmentdetails;

@Repository
public interface DepartmentdetailsRepository extends CrudRepository<departmentdetails, Integer>{
	
	Iterable<departmentdetails> findAllByOrderByDepartmentId();
	
	@Query(value = "select d from departmentdetails d where d.active='Y'")
	public List<departmentdetails> getDepartmentDetailsList();
}
