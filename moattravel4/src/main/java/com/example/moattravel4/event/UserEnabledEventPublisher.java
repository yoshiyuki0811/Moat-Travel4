package com.example.moattravel4.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.moattravel4.Entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEnabledEventPublisher {
	
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public void publishUserEnabledEvent(User user) {
		
        applicationEventPublisher.publishEvent(new UserEnabledEvent(this, user));
    }
	

}
