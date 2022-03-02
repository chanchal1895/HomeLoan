package com.lti.homeloan.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.homeloan.bean.ContactUs;
import com.lti.homeloan.bean.LoanTransaction;
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
//		return ur.getUserResId();
//	}
//	@Override
//	@Transactional
//	public int saveLoginUser(UserDetails userdtl) {
		
		UserLogin ul= new UserLogin();
		ul.setUr(ur);
		ul.setUserPassword(userdtl.getPassword());
		ul.setUserRole("User");
		em.persist(ul);				
//		return ul.getUserId();
//	}
//	@Override
//	@Transactional
//	public int saveLoanTransaction(UserDetails userdtl) {

		LoanTransaction lt = new LoanTransaction();
		lt.setUr(ur);
		lt.setLoanAmount(userdtl.getLoanAmt());
		lt.setTenure(userdtl.getLoanTenure());
		lt.setAppStatus("Ackn");
		lt.setLoanType(1);
		
		em.persist(lt);				
		return lt.getLoanTransactionId();
	}


	@Override
	@Transactional
	public String saveContactUsInfo(UserDetails userdtl) {
		ContactUs contactUs =new ContactUs();
		if(null != userdtl) {
			if(userdtl.getFirstname()!=null) {
				contactUs.setFirstName(userdtl.getFirstname());
			}
			if(userdtl.getLastname()!=null) {
				contactUs.setLastName(userdtl.getLastname());
			}
			if(userdtl.getPrimaryEmail()!=null) {
				contactUs.setEmailId(userdtl.getPrimaryEmail());
			}
			if(userdtl.getMessage()!=null) {
				contactUs.setMessage(userdtl.getMessage());
			}
			if(userdtl.getSubject()!=null) {
				contactUs.setSubject(userdtl.getSubject());
			}
					
					
		}
		
		 em.persist(contactUs);
		 
		// TODO Auto-generated method stub
		return null;
	}

}
