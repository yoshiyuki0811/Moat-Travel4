package com.example.moattravel4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel4.Entity.Reservation;
import com.example.moattravel4.Entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	public Page<Reservation> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

}
