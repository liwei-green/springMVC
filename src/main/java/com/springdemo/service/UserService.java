package com.springdemo.service;


import com.springdemo.entity.User;


public interface UserService {
	
	public User saveUser(User user);
	
	public User findByUserName(String userName);

}
