package com.lti.homeloan.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lti.homeloan.bean.ContactUs;
import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.bean.UserLogin;
import com.lti.homeloan.bean.UserRegistration;

public interface UserRegDao {

	public abstract int saveRegisterUser(UserDetails userdts);
	public abstract String saveContactUsInfo(UserDetails  contactUs);
//	public abstract int saveLoginUser(UserDetails userdts);
//	public abstract int saveLoanTransaction(UserDetails userdts);
	public int validate(UserDetails userDtls);
	public abstract UserDetails getUserLoanDetails(String userid);
	public int updateUserDetails(UserDetails userRegistration);
	void upload(MultipartFile file) throws IOException;
	public List<UserDetails> getDetsForAdmin();
	
}
