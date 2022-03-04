package com.lti.homeloan.dao;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.homeloan.bean.ContactUs;
import com.lti.homeloan.bean.LoanTransaction;
import com.lti.homeloan.bean.LoanType;
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
		LoanType l = em.find(LoanType.class, userdtl.getLoanTypeId());



		System.out.println(l);
		lt.setLoanType(l);
		
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


	@Override
	public int validate(UserDetails userDtls) {
	int message = 1;



	System.out.println(userDtls.getFormName() + userDtls.getPrimaryEmail() + userDtls.getPassword());
	if (userDtls.getFormName().equals("Login")) {
	Query query = em.createQuery("SELECT a.primaryEmail,a.userResId," + " b.userPassword "
	+ " FROM UserRegistration a , UserLogin b\r\n" + " WHERE a.userResId = b.ur");
	List<Object[]> userDetsList = (List<Object[]>) query.getResultList();



	for (int i = 0; i < userDetsList.size(); i++) {
	if (userDetsList.get(i)[0].equals(userDtls.getPrimaryEmail())
	&& userDetsList.get(i)[2].equals(userDtls.getPassword())) {
	message = (int) userDetsList.get(i)[1];
	}
	}
	} else if (userDtls.getFormName().equals("Registration")) {
	Query query = em.createQuery("SELECT a.primaryEmail" + " FROM UserRegistration a \r\n");
	List<String> userDetsList = (List<String>) query.getResultList();



	for (int i = 0; i < userDetsList.size(); i++) {
	if (userDetsList.get(i).equals(userDtls.getPrimaryEmail())) {
	message = 0;
	}
	}
	} else {
	Query query = em
	.createQuery("SELECT a.primaryEmail,b.userPassword FROM UserRegistration a , UserLogin b\r\n"
	+ "WHERE a.userResId = b.ur and b.userRole = :role \n" + "")
	.setParameter("role", "Admin");



	List<Object[]> userDetsList = (List<Object[]>) query.getResultList();



	for (int i = 0; i < userDetsList.size(); i++) {
	if (userDetsList.get(i)[0].equals(userDtls.getPrimaryEmail())
	&& userDetsList.get(i)[1].equals(userDtls.getPassword())) {
	message = 0;
	}
	}



	}



	return message;
	}
	
	
	@Override
	public UserDetails getUserLoanDetails(String userid) {



	double rate =0;
	DecimalFormat df = new DecimalFormat("#.####");
	UserDetails userDtls = new UserDetails();
	Query query = em.createQuery(
	"select a.firstname,a.lastname,a.primaryEmail,a.mobileNo, b.appStatus, b.approvedBy,b.loanAmount,c.loanTypeName,b.tenure,b.Remarks \r\n"
	+ "from UserRegistration a, LoanTransaction b, LoanType c\r\n"
	+ "where a.userResId = b.ur and a.userResId = :userid \r\n" + "and c.loanTypeId = b.loanType")
	.setParameter("userid",Integer.parseInt( userid));
	List<Object> userDetsList = (List<Object>) query.getResultList();
	Iterator itr = userDetsList.iterator();
	while (itr.hasNext()) {
	Object[] obj = (Object[]) itr.next();
	userDtls.setUr(Integer.parseInt(userid));
	userDtls.setFirstname(String.valueOf(obj[0]));
	userDtls.setLastname(String.valueOf(obj[1]));
	userDtls.setPrimaryEmail(String.valueOf(obj[2]));
	userDtls.setMobileNo((long) (obj[3])); //
	userDtls.setAppStatus(String.valueOf(obj[4])); //
	userDtls.setApprovedBy(String.valueOf(obj[5])); //
	userDtls.setLoanAmt((double) (obj[6])); //
	userDtls.setLoanTypeName(String.valueOf(obj[7])); //
	userDtls.setLoanTenure((float) (obj[8])); //
	userDtls.setRemarks(String.valueOf(obj[9]));
	rate = 10;
	userDtls.setInterestRate(rate);

	}
	System.out.println(userDtls.getAppStatus().equals("Ackn"));
	if (userDtls.getAppStatus().equals("Ackn")) {



	rate = rate / (12 * 100);
	rate = Math.round(rate*10000)/10000.0d;
	System.out.println(rate);
	float tenure = (float) (userDtls.getLoanTenure() * 12);
	System.out.println(tenure);
	double loanEmi = (double) ((userDtls.getLoanAmt() * rate * Math.pow(1 + rate, tenure))
	/ (Math.pow(1 + rate, tenure) - 1));
	loanEmi = Math.round(loanEmi*10000)/10000.0d;
	double payment = (double) (tenure * loanEmi);
	payment = Math.round(payment*10000)/10000.0d;



	double totIntPayable = (double) (payment - userDtls.getLoanAmt());
	totIntPayable = Math.round(totIntPayable*10000)/10000.0d;



	System.out.println(loanEmi + " " + totIntPayable + " " + payment);
	userDtls.setLoanEMI(loanEmi);
	userDtls.setTotIntPayable(totIntPayable);
	userDtls.setTotPayment(payment);
	// Total interest payable = total payment- loan amt
	// total payment = loan tenure(months)* loan emi



	}



	return userDtls;
	}

}
