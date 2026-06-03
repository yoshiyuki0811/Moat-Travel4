package com.example.moattravel4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moattravel4.Entity.UsedCoupon;

public interface UsedCouponRepository extends JpaRepository<UsedCoupon, Long> {
	
	// 指定したユーザーが、指定したクーポンをすでに使っているか確認する
    boolean existsByUserIdAndCouponId(Integer userId, Long couponId);

}
