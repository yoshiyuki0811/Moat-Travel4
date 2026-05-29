package com.example.moattravel4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moattravel4.Entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer> {
	
	public VerificationToken findByToken(String token);
	
	

}
