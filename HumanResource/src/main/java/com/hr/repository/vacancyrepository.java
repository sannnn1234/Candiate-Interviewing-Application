package com.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.vacancydetails;



@Repository
public interface vacancyrepository extends CrudRepository<vacancydetails, Long> {

}
