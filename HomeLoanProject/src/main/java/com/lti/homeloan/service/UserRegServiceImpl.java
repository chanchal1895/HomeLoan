package com.lti.homeloan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lti.homeloan.bean.UserDetails;
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



	@Override
	public int updateUserDetails(UserDetails userRegistration) {
		// TODO Auto-generated method stub
		return dao.updateUserDetails(userRegistration);
	}
	
	//File upload
	
	@Override
	public void upload(MultipartFile file) throws IOException {
		dao.upload(file);
		
	}
	
	@Override
    public List<UserDetails> getDetsForAdmin() {
        List userDtls = new ArrayList<UserDetails>();

        userDtls =  dao.getDetsForAdmin();
        return userDtls;
    }



}
