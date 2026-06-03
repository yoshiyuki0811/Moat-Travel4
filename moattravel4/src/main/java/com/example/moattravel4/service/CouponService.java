package com.example.moattravel4.service;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.moattravel4.Entity.Coupon;
import com.example.moattravel4.Entity.User;
import com.example.moattravel4.repository.CouponRepository;
import com.example.moattravel4.repository.UsedCouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
	
	private final CouponRepository couponRepository;
	
	private final UsedCouponRepository usedCouponRepository;
	
	
	@Transactional
	public Coupon verifyAndGetCoupon(String code, User user) {
		
		//クーポンが有効でクーポンコードが一致するものを探す
		Optional<Coupon> couponOpt = couponRepository.findByCodeAndIsActiveTrue(code);
		
		//上記で拾えない
		if(couponOpt.isEmpty()) {
			
			return null;
		}
		
		Coupon coupon = couponOpt.get();
		
		//クーポン使用履歴からuserIdとcouponIdもとにチェックする
		boolean isAlreaddyUsed = usedCouponRepository.existsByUserIdAndCouponId(user.getId(), coupon.getId());
		
		if(isAlreaddyUsed) {
			
			return null;
		}
		
		return coupon;
		
	}

}
