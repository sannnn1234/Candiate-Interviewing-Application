package com.hr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.dto.RoundListDto;
import com.hr.dto.SelectionCriteriaDto;
import com.hr.model.department;


@Repository
public interface departmentroundrepository  extends CrudRepository<department, Long> {

	
	@Query("select new com.hr.dto.RoundListDto(s.roundId, s.roundName)from Round s")
	List<RoundListDto> getRoundDetails();
	
	@Query("select new com.hr.dto.SelectionCriteriaDto(d.itemNo, d.departmentId, dd.department, d.profileId, p.profile, d.roundNo, d.interviewLengthMins) from department d inner join departmentdetails dd on d.departmentId = dd.departmentId inner join profiledetails p on d.profileId = p.profileId")
	List<SelectionCriteriaDto> getSelectionCriteriaDetails();
	
	
}
