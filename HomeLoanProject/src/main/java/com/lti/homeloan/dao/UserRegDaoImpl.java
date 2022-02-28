package com.lti.homeloan.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.homeloan.bean.UserRegistration;

@Repository
public class UserRegDaoImpl implements UserRegDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional
	public int addUser(UserRegistration ur) {
		
		em.persist(ur);				
		return ur.getUserResId();
	}

}
