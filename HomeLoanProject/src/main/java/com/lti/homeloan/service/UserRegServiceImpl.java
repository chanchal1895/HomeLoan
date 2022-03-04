package com.lti.homeloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.homeloan.bean.ContactUs;
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
//			userDts.setUr(i);
//		if(i>0) {
//		dao.saveLoginUser(userDts);
//		dao.saveLoanTransaction(userDts);
//		}
			}
		
		
		return i;
	}



	@Override
	public String saveContactUsInfo(UserDetails  contactUs) {
		String email="";
		if(!contactUs.equals(null)){
			 email=dao.saveContactUsInfo(contactUs);		
				}
	
		return email;

	}
	
	@Override
	public int validate(UserDetails userDtls) {
	
		int message = dao.validate(userDtls);
		return message;
	}



	@Override
	public UserDetails getUserLoanDetails(String userid) {
	UserDetails userDtls = new UserDetails();
	if(!userDtls.equals(null)) {
	userDtls = dao.getUserLoanDetails(userid);}
	return userDtls;
	}
}
