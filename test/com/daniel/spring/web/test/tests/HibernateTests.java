package com.daniel.spring.web.test.tests;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.model.Address;
import com.daniel.spring.web.model.User;

/**
 * Test bed for Hibernate, allows me to see what SQL queries Hibernate is executing.
 * 
 * @author danielmoffat
 *
 */
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/daniel/spring/web/config/dao-context.xml", 
		"classpath:com/daniel/spring/web/test/config/datasource.xml",
		"classpath:com/daniel/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTests {
		
	private static final Logger logger = LogManager.getLogger(HibernateTests.class);
		
	@Autowired
	private HibernateCrudDao<User, Integer> userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		
	}
	
	@Transactional
	@Test
	public void testAdd() {
		User u = new User();
		Address a = new Address();
		
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(a);
		
		a.setLine1("26 Synehurst");
		a.setLine2("Evesham");
		a.setLine3("Badsey");
		a.setPostcode("WR117XJ");
		u.setEmail("danmofo2@gmail.com");
		u.setName("Daniel");
		u.setAddresses(addresses);
		
		Integer key = userDao.add(u);
		
		User ret = userDao.retrieve(key);
		
		List<Address> addrs = ret.getAddresses();
		
		Address addr = addrs.get(0);
		
		logger.info(addr);
		
		addr.setLine1("28 Tithe Court");
		
		userDao.update(ret);
		
		User u2 = userDao.retrieve(key);
		
		logger.info(u2);
	}
	
}
