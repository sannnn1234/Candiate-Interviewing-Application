package com.hr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.dto.CandidateDto;
import com.hr.dto.InterviewerHrDto;
import com.hr.model.ScheduledInterview;
import com.hr.model.candidate;

@Repository
public interface candidaterepository extends CrudRepository<candidate, Long> {

	public candidate findByCandidateUniqueNumber(@Param("candidateUniqueNumber") String candidateUniqueNumber);
	
//	public candidate findByEmailAddress(String emailAddress);

	@Query(value = "select candidate_unique_number  from candidate_information_master s where s.email_address = :emailAddress limit 1 " , nativeQuery = true)
	public String findByEmailAddress(String emailAddress);
	
	@Query(value="select new com.hr.dto.InterviewerHrDto(s.createdBy, e.fullName, e.email, e.empPhone) from candidate s inner join employee e on s.createdBy = e.empId  where s.candidateUniqueNumber = :candidateUniqueNumber")
	InterviewerHrDto getHrList(@Param("candidateUniqueNumber") String candidateUniqueNumber);
		
	
//    public Boolean existsByCandidateFullNameAndContactNumberAndEmailAddressAndCreatedTimeGreaterThanEqual(String candidateFullName , Long contactNumber ,String emailAddress, Date createdTime);
	
    public Boolean existsByCandidateFullNameAndDateOfBirthAndCreatedTimeGreaterThanEqual(String candidateFullName , Date DateOfBirth , Date createdTime);
    public Boolean existsByCandidateFullNameAndCreatedTimeGreaterThanEqual(String candidateFullName , Date createdTime);
	
	
	@Query(value = "select count(distinct s.candidateFullName) from candidate s")
	public Long getCountTotalCandidate();

	@Query(value = "select s.cities from Location s order by s.cities asc")
	List<String> getListOfLocation();
    
	@Query(value = "select s from ScheduledInterview s")
	List<ScheduledInterview> getSchedulelist();
	
	
	@Query(name = "getCandidateList", nativeQuery = true)
	List<CandidateDto>getShorlistedCandidate();
	
	
}