package com.example.moattravel4.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.moattravel4.Entity.House;
import com.example.moattravel4.Entity.Reservation;
import com.example.moattravel4.Entity.User;
import com.example.moattravel4.form.ReservationRegisterForm;
import com.example.moattravel4.repository.HouseRepository;
import com.example.moattravel4.repository.ReservationRepository;
import com.example.moattravel4.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	private final HouseRepository houseRepository;
	
	private final UserRepository userRepository;
	
	public void create(ReservationRegisterForm reservationRegisterForm) {
		
		Reservation reservation =new Reservation();
		
		House house =houseRepository.getReferenceById(reservationRegisterForm.getHouseId());
		
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		
		LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
		
		LocalDate checkoutDate = LocalDate.parse(reservationRegisterForm.getCheckoutDate());
		
		
		reservation.setHouse(house);
		
		reservation.setUser(user);
		
		reservation.setCheckinDate(checkinDate);
		
		reservation.setCheckoutDate(checkoutDate);
		
		reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		
		reservation.setAmount(reservationRegisterForm.getAmount());
		
		reservationRepository.save(reservation);
		
		
	}
	
	//宿泊人数が定員以下かどうかチェックする
	public boolean isWithnCapacity(Integer numberOfPeople, Integer capacity) {
		
		return numberOfPeople <= capacity;
	}
	
	//宿泊料金を計算する
	public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
		
		long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
		
		int amount = price * (int)numberOfNights;
		
		return amount;
	}

}
