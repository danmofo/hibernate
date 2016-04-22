package com.daniel.spring.web.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.model.User;


/**
 * User DAO using Hibernate.
 * 
 * todo: check book @ work for correct method syntax, there is a mix of hql and using
 * createCriteria directly
 * todo: catch HibernateException to verify success of operations
 * 
 * @author dan
 *
 */
@Repository("userDao")
@Transactional
public class HibernateUserDaoImpl implements HibernateCrudDao<User, Integer> {

	private static final Logger logger = LogManager.getLogger(HibernateUserDaoImpl.class);
		
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	private Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Integer add(User model) {		
		return (Integer) session().save(model);
	}

	@Override
	public User retrieve(Integer key) {
		return session().get(User.class, key);
	}

	// docs: https://docs.jboss.org/hibernate/orm/5.0/userGuide/en-US/html_single/#d5e1774
	@SuppressWarnings("unchecked")
	@Override
	public List<User> list(int limit) {
		List<User> results = session()
								.createCriteria(User.class)
								.setMaxResults(limit)
								.list();
		
		return results;
	}

	@Override
	public List<User> list() {
		return list(10);
	}

	@Override
	public void update(User model) {
		session().update(model);
	}

	@Override
	public void delete(User model) {
		session().delete(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> orderBy(Order order) {
		List<User> results = session()
				.createCriteria(User.class)
				.addOrder(order)
				.list();
		
		return results;
	}

}
