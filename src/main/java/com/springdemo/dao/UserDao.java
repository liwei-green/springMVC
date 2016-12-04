package com.springdemo.dao;


import com.springdemo.entity.User;


public interface UserDao {
	public User findById(Integer id);

}
