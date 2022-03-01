package com.lti.homeloan.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.bean.UserLogin;
import com.lti.homeloan.bean.UserRegistration;

@Repository
public class UserRegDaoImpl implements UserRegDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional
	public int saveRegisterUser(UserDetails userdtl) {
		UserRegistration ur = new UserRegistration();
		ur.setFirstname(userdtl.getFirstname());
		ur.setLastname(userdtl.getLastname());
		ur.setGender(userdtl.getGender());
		ur.setMobileNo(userdtl.getMobileNo());
		ur.setPrimaryEmail(userdtl.getPrimaryEmail());
		ur.setState(userdtl.getState());
		em.persist(ur);			
		return ur.getUserResId();
	}
	@Override
	@Transactional
	public int saveLoginUser(UserDetails userdtl) {
		
		em.persist(userLogin);				
		return userLogin.getUserId();
	}
	@Override
	public int saveLoanTransaction(UserDetails userdtl) {
		// TODO Auto-generated method stub
		return 0;
	}

}
