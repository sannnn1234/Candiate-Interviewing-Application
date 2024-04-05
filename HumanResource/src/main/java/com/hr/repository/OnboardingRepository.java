package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hr.dto.CandidateExperianceDto;
import com.hr.model.onboarding;

@Repository
public interface OnboardingRepository extends CrudRepository<onboarding, Long> {

	
//	@Query(value = "select new com.hr.dto.CandidateDto(s.candidateUniqueNumber, s.candidateFullName)  from ScheduledInterview s where s.roundStatus = 'Selected'")
//	List<CandidateDto>getOnboardingCandidate();
	
	@Query(value = "select new com.hr.dto.CandidateExperianceDto(s.candidateUniqueNumber, s.candidateFullName, c.totalExperience)  from ScheduledInterview s inner join candidate c on s.candidateUniqueNumber = c.candidateUniqueNumber left join onboarding o on c.candidateUniqueNumber = o.candidateUniqueNumber where s.roundStatus = 'Selected' AND o.candidateUniqueNumber IS NULL And c.joiningDate IS Not NULL")
	List<CandidateExperianceDto>getOnboardingCandidate();
	
	
}
