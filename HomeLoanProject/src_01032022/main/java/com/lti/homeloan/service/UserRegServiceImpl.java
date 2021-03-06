package com.lti.homeloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.homeloan.bean.LoanTransaction;
import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.bean.UserLogin;
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
	public int registerUser(UserDetails userDts) {
		// TODO Auto-generated method stub
		int i=0;
		if(!userDts.equals(null)){
			i=dao.saveRegisterUser(userDts);		
		
		if(i>0) {
		dao.saveLoginUser(userDts);
		dao.saveLoanTransaction(userDts);
		}}
		
		
		return i;
	}



	

}
