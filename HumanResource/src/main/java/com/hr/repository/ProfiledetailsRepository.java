package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hr.model.profiledetails;

@Repository
public interface ProfiledetailsRepository extends CrudRepository<profiledetails, Integer> {

	Iterable<profiledetails> findAllByOrderByProfileId();

	public profiledetails findByProfileId(int profileId);
	
	@Query(value = "select p from profiledetails p where p.active='Y'")
	public List<profiledetails> getProfileList();
}
