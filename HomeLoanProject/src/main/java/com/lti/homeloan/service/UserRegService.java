package com.lti.homeloan.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.bean.UserRegistration;



public interface UserRegService {
	
	public abstract int registerUser(UserDetails userDtls);
	public abstract String saveContactUsInfo(UserDetails contactUs);	
	public abstract UserDetails getUserLoanDetails(String userid);	
	public int validate(UserDetails userDtls);
	void upload(MultipartFile file) throws IOException;
	public int updateUserDetails(UserDetails userRegistration);
	public List<UserDetails> getDetsForAdmin() ;
	
}
