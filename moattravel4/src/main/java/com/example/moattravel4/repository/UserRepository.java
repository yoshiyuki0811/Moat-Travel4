package com.example.moattravel4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moattravel4.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	public User findByEmail(String email);
}
