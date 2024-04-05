package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.Agreement;



@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer>{
	

	Iterable<Agreement> findAllByOrderByAgreementId();
	
	@Query(value = "select a from Agreement a where a.active='Y'")
	public List<Agreement> getAgreementList();
}
