package com.lti.homeloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.homeloan.bean.UserRegistration;
import com.lti.homeloan.dao.UserRegDao;
@Service("userService")
public class UserRegServiceImpl implements UserRegService{
	
	@Autowired
	UserRegDao dao;
	
	
	
	public UserRegDao getDao() {
		return dao;
	}



	public void setDao(UserRegDao dao) {
		this.dao = dao;
	}



	@Override
	public int addUser(UserRegistration ur){
		System.out.println("service layer");	
		int userId=dao.addUser(ur);		
		return userId;
	}

}
