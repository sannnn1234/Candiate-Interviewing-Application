package com.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.PasswordResetToken;
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

	public PasswordResetToken findByToken(String token);
}
