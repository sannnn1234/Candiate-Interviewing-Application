package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hr.dto.DepartmentProfileDto;
import com.hr.model.DepartmentProfile;

@Repository
public interface DepartProfileRepository extends CrudRepository<DepartmentProfile, Long>{

	Iterable<DepartmentProfile> findAllByOrderByDepartmentProfileId();
	
	@Query("select new com.hr.dto.DepartmentProfileDto(dp.departmentProfileId,dp.departmentId, dp.profileId, d.department, p.profile, dp.active) from departmentdetails d inner join DepartmentProfile dp on d.departmentId = dp.departmentId inner join profiledetails p  on dp.profileId = p.profileId and dp.active='Y' order by dp.departmentProfileId")
	List<DepartmentProfileDto> getDepartmentProfileDetails();
	
	@Query("select new com.hr.dto.DepartmentProfileDto(dp.departmentProfileId,dp.departmentId, dp.profileId, d.department, p.profile, dp.active) from departmentdetails d inner join DepartmentProfile dp on d.departmentId = dp.departmentId inner join profiledetails p  on dp.profileId = p.profileId and dp.active='Y' where dp.departmentId =:departmentId order by dp.departmentProfileId")
	List<DepartmentProfileDto> getDepartmentProfileMapping(Integer departmentId);
	
	@Query("select new com.hr.dto.DepartmentProfileDto(dp.departmentProfileId, dp.departmentId, dp.profileId, d.department, p.profile, dp.active) from departmentdetails d inner join DepartmentProfile dp on d.departmentId = dp.departmentId inner join profiledetails p  on dp.profileId = p.profileId where dp.departmentProfileId =:departmentProfileId")
	List<DepartmentProfileDto> getDepartmentProfileDetailsById(Long departmentProfileId);


}
