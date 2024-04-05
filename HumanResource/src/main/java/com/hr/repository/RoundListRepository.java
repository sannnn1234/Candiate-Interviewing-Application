package com.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.Round;

@Repository
public interface RoundListRepository extends CrudRepository<Round, Integer> {

}
