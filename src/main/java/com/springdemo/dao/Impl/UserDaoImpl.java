package com.springdemo.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import mysql.ConnectUitl;












import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;

import com.springdemo.dao.UserDao;
import com.springdemo.entity.User;


@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SessionFactory sessionFactory;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User saveUser(User user) {
		Session session = null;
		try {
			session =  sessionFactory.openSession();

			session.beginTransaction();
			session.save(user);


			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
			}
		}
		return user;
	}

}
