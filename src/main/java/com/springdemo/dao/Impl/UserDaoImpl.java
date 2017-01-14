package com.springdemo.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.dao.UserDao;
import com.springdemo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User saveUser(User user) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

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

	@SuppressWarnings("unchecked")
	public User findByUserName(String userName) {
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(" select bean from User bean where 1=1 ");
		Session session = null;
		session = sessionFactory.openSession();
		if (userName != "") {
			hqlQuery.append(" and bean.name ='" + userName + "'");
		}
		Query query = session.createQuery(hqlQuery.toString());
		List<User> list = query.list();
		User user = null;
		if(list.isEmpty()){
			System.out.println("no user");
		}else{
			user=list.get(0);
		}
		System.out.println(list.toString());
		
		
		return user;
	}



}
