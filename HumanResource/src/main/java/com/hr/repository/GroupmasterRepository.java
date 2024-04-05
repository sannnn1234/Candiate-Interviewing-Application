package com.hr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hr.model.Groupmaster;





	@Repository
	public interface GroupmasterRepository extends CrudRepository<Groupmaster, Long>  {
		
		public Groupmaster findByGroupdesc(String groupdesc);
		
		@Query(value = "select g from Groupmaster g where g.active='Y'")
		public List<Groupmaster> getGroupMasterList();
		
		@Query(value = "select * from employee_master  where  group_code  in (select group_code from program_groupmapping  group by  group_code)", nativeQuery = true)
		public List<Groupmaster> findAllMappedGroup();
		
		@Query(value = "select *  from employee_master where  group_code not in (select group_code from program_groupmapping group by  group_code)", nativeQuery = true)
		public List<Groupmaster> findAllNotMappedGroup();
	}
    
	
