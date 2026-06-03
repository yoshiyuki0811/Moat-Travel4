package com.example.moattravel4.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.moattravel4.Entity.Coupon;
import com.example.moattravel4.Entity.House;
import com.example.moattravel4.Entity.Reservation;
import com.example.moattravel4.Entity.User;
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
	
	@Transactional
	public void create(Map<String, String>paymentIntentObject) {
		
		Reservation reservation =new Reservation();
		
		Integer houseId = Integer.valueOf(paymentIntentObject.get("houseId"));
		
		Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));
		
		House house =houseRepository.getReferenceById(houseId);
		
		User user = userRepository.getReferenceById(userId);
		
		LocalDate checkinDate = LocalDate.parse(paymentIntentObject.get("checkinDate"));
		
		LocalDate checkoutDate = LocalDate.parse(paymentIntentObject.get("checkoutDate"));
		
		Integer numberOfPeople = Integer.valueOf(paymentIntentObject.get("numberOfPeople"));
		
		Integer amount = Integer.valueOf(paymentIntentObject.get("amount")); 
		
		reservation.setHouse(house);
		
		reservation.setUser(user);
		
		reservation.setCheckinDate(checkinDate);
		
		reservation.setCheckoutDate(checkoutDate);
		
		reservation.setNumberOfPeople(numberOfPeople);
		
		reservation.setAmount(amount);
		
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
	
	//クーポン使用時の料金計算
	public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price, Coupon coupon) {
		
		int baseAmount = calculateAmount(checkinDate, checkoutDate, price);
		
		if (coupon == null) {
			return baseAmount;
		}
		
		int discountedAmount = baseAmount * (100 - coupon.getDiscountRate()) / 100;
		
		return discountedAmount;
		
		
	}
}
