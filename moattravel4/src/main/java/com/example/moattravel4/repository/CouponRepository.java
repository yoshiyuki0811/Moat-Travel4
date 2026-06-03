package com.example.moattravel4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel4.Entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	// コードが一致し、かつ有効なクーポンを1件取得する
    Optional<Coupon> findByCodeAndIsActiveTrue(String code);

}
