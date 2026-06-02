package com.example.moattravel4.event;

import org.springframework.context.ApplicationEvent;

import com.example.moattravel4.Entity.User;

import lombok.Getter;

@Getter
public class UserEnabledEvent extends ApplicationEvent {
	
	private final User user;
	
	public UserEnabledEvent(Object source, User user) {
		
		super(source);
		
		this.user = user;
	}

}
