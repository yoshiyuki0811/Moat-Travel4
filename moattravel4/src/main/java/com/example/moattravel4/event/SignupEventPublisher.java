package com.example.moattravel4.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.moattravel4.Entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignupEventPublisher {
	
	private final ApplicationEventPublisher applicationPublisher;
	
	public void publishSignupEvent(User user, String requestUrl) {
		
		applicationPublisher.publishEvent(new SignupEvent(this, user, requestUrl));
		
	}
	
	

}
