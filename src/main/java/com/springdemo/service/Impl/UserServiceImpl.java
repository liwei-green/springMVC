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
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

}
