package com.hr.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hr.model.ProgramGroup;


public interface ProgramGroupRepository extends JpaRepository<ProgramGroup, Integer>{

	long deleteByGroupCode(Long groupCode);

	@Transactional
	@Modifying
	@Query(value = "delete from program_groupmapping where group_code = :groupCode",nativeQuery = true)
	long deleteByGroupCodeNative(long groupCode);
}
