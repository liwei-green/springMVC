package com.springdemo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.UserDao;
import com.springdemo.entity.User;
import com.springdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
}
