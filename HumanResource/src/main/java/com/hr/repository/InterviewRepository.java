package com.hr.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.Interview;


@Repository
public interface InterviewRepository extends CrudRepository<Interview, Integer>  {
	
	public Interview findByInterviewId(int interviewId);
	

}
