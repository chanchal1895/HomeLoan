package com.lti.homeloan.dao;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
		if(userdtl.getFirstname()!=null && userdtl.getFirstname()!="") {		
		ur.setFirstname(userdtl.getFirstname());
		}
		if(userdtl.getLastname()!=null && userdtl.getLastname()!="")
		ur.setLastname(userdtl.getLastname());
		if(userdtl.getGender()!=null && userdtl.getGender()!="")
		ur.setGender(userdtl.getGender());
		if(userdtl.getMobileNo()!=0)
		ur.setMobileNo(userdtl.getMobileNo());
		if(userdtl.getPrimaryEmail()!=null && userdtl.getPrimaryEmail()!="")
		ur.setPrimaryEmail(userdtl.getPrimaryEmail());
		if(userdtl.getState()!=null && userdtl.getState()!="")
		ur.setState(userdtl.getState());
		em.persist(ur);
//		return ur.getUserResId();
//	}
//	@Override
//	@Transactional
//	public int saveLoginUser(UserDetails userdtl) {

		UserLogin ul = new UserLogin();
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
		ContactUs contactUs = new ContactUs();
		if (null != userdtl) {
			if (userdtl.getFirstname() != null && userdtl.getFirstname() != "") {
				contactUs.setFirstName(userdtl.getFirstname());
			}
			if (userdtl.getLastname() != null && userdtl.getLastname() != "") {
				contactUs.setLastName(userdtl.getLastname());
			}
			if (userdtl.getPrimaryEmail() != null && userdtl.getPrimaryEmail() != "") {
				contactUs.setEmailId(userdtl.getPrimaryEmail());
			}
			if (userdtl.getMessage() != null && userdtl.getMessage() != "") {
				contactUs.setMessage(userdtl.getMessage());
			}
			if (userdtl.getSubject() != null && userdtl.getSubject() != "") {
				contactUs.setSubject(userdtl.getSubject());
			}

		}

		em.persist(contactUs);

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int validate(UserDetails userDtls) {
		int message = 1;///false

		System.out.println(userDtls.getFormName() + userDtls.getPrimaryEmail() + userDtls.getPassword());
		if (userDtls.getFormName().equals("Login")) {
			Query query = em.createQuery("SELECT a.primaryEmail,a.userResId," + " b.userPassword "
					+ " FROM UserRegistration a , UserLogin b\r\n" + " WHERE a.userResId = b.ur");
			List<Object[]> userDetsList = (List<Object[]>) query.getResultList();
			if(userDetsList !=null) {
			for (int i = 0; i < userDetsList.size(); i++) {
				System.out.println(userDetsList.get(i)[0] + ""+userDetsList.get(i)[1] +""+ userDetsList.get(i)[2]);

				if(userDetsList.get(i)[0] != null && userDetsList.get(i)[0] !="" 
						&& userDetsList.get(i)[2] != null && userDetsList.get(i)[2] !="" ) {
				if (userDetsList.get(i)[0].equals(userDtls.getPrimaryEmail())
						&& userDetsList.get(i)[2].equals(userDtls.getPassword())) {
					message = (int) userDetsList.get(i)[1];
				}}
			}}
		} else if (userDtls.getFormName().equals("Registration")) {
			Query query = em.createQuery("SELECT a.primaryEmail" + " FROM UserRegistration a \r\n");
			List<String> userDetsList = (List<String>) query.getResultList();

			for (int i = 0; i < userDetsList.size(); i++) {
				if (null != userDetsList.get(i) && userDetsList.get(i).equals(userDtls.getPrimaryEmail())) {
					message = 0;/////true///entry present
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
					message = 0;///entry present
				}
			}

		}

		return message;
	}
	@Override
	public UserDetails getUserLoanDetails(String userid) {

		double rate = 0;
		DecimalFormat df = new DecimalFormat("#.####");
		UserDetails userDtls = new UserDetails();
		Query query = em.createQuery(
				"select a.firstname,a.lastname,a.primaryEmail,a.mobileNo,a.gender, a.state, b.appStatus, b.approvedBy,b.loanAmount,c.loanTypeName,b.tenure,b.Remarks \r\n"
						+ "from UserRegistration a, LoanTransaction b, LoanType c\r\n"
						+ "where a.userResId = b.ur and a.userResId = :userid \r\n" + "and c.loanTypeId = b.loanType")
				.setParameter("userid", Integer.parseInt(userid));
		List<Object> userDetsList = (List<Object>) query.getResultList();
		Iterator itr = userDetsList.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			userDtls.setUr(Integer.parseInt(userid));
			if (String.valueOf(obj[0]) != null && String.valueOf(obj[0]) != "")

				userDtls.setFirstname(String.valueOf(obj[0]) );
			if (String.valueOf(obj[1]) != null && String.valueOf(obj[0]) != "")
				userDtls.setLastname(String.valueOf(obj[1]));
			if (String.valueOf(obj[2]) != null)
				userDtls.setPrimaryEmail(String.valueOf(obj[2]));
			if (String.valueOf(obj[3]) != null)
				userDtls.setMobileNo((long) (obj[3])); //
			if (String.valueOf(obj[4]) != null) {
				userDtls.setGender(String.valueOf(obj[4])); //
			}
			if (String.valueOf(obj[5]) != null) {
				userDtls.setState(String.valueOf(obj[5])); //
			}
			if (String.valueOf(obj[6]) != null)
				userDtls.setAppStatus(String.valueOf(obj[6])); //
			if (String.valueOf(obj[7]) != null) {
				userDtls.setApprovedBy(String.valueOf(obj[7])); //
			}
			if (String.valueOf(obj[8]) != null) {
				userDtls.setLoanAmt(((double) (obj[8])));
				; //
			}
			if (String.valueOf(obj[9]) != null) {
				userDtls.setLoanType(String.valueOf(obj[9]));
			}
			if (String.valueOf(obj[10]) != null) {
				userDtls.setLoanTenure((float) (obj[10]));
			}
			if (String.valueOf(obj[11]) != null) {
				userDtls.setRemarks(String.valueOf(obj[11]));
			}
			rate = 10;
			userDtls.setInterestRate(rate);

		}
		//System.out.println(userDtls.getAppStatus().equals("Ackn"));
		if (userDtls.getAppStatus().equals("Ackn")) {

			rate = rate / (12 * 100);
			rate = Math.round(rate * 10000) / 10000.0d;
			System.out.println(rate);
			float tenure = (float) (userDtls.getLoanTenure() * 12);
			System.out.println(tenure);
			double loanEmi = (double) ((userDtls.getLoanAmt() * rate * Math.pow(1 + rate, tenure))
					/ (Math.pow(1 + rate, tenure) - 1));
			loanEmi = Math.round(loanEmi * 10000) / 10000.0d;
			double payment = (double) (tenure * loanEmi);
			payment = Math.round(payment * 10000) / 10000.0d;

			double totIntPayable = (double) (payment - userDtls.getLoanAmt());
			totIntPayable = Math.round(totIntPayable * 10000) / 10000.0d;

			System.out.println(loanEmi + " " + totIntPayable + " " + payment);
			userDtls.setLoanEMI(loanEmi);
			userDtls.setTotIntPayable(totIntPayable);
			userDtls.setTotPayment(payment);
			

		}

		return userDtls;
	}

	@Override
	@Transactional
	public int updateUserDetails(UserDetails userRegistration) {

		UserRegistration userRegistration1 = em.find(UserRegistration.class, userRegistration.getUr());

		if (userRegistration != null) {
			userRegistration1.setMobileNo(userRegistration.getMobileNo());
			em.persist(userRegistration1);

		}

		return 0;

	}

	@Override
	public List<UserDetails> getDetsForAdmin(String status) {

		List<UserDetails> ud = new ArrayList<UserDetails>();
		Query qry = null;
		if(status.equals("aprv")) {
		 qry = em
				.createQuery("select a.firstname,a.lastname,a.primaryEmail,a.mobileNo, a.state,"
						+ "b.loanAmount,b.tenure, b.appStatus,b.approvedBy,b.Remarks, c.loanTypeName,d.loanTransactionId "
						+ "from UserRegistration a, LoanTransaction b, LoanType c, LoanTransaction d "
						+ "where a.userResId = b.ur and  a.userResId = d.ur and b.appStatus=:ackn and b.loanType= c.loanTypeId")
				.setParameter("ackn", "Aprv");
		
		}
		else if(status.equals("rjct")) {
			 qry = em
					.createQuery("select a.firstname,a.lastname,a.primaryEmail,a.mobileNo, a.state,"
							+ "b.loanAmount,b.tenure, b.appStatus,b.approvedBy,b.Remarks, c.loanTypeName,d.loanTransactionId "
							+ "from UserRegistration a, LoanTransaction b, LoanType c,  LoanTransaction d  "
							+ "where a.userResId = b.ur   and  a.userResId = d.ur and b.appStatus=:ackn and b.loanType= c.loanTypeId")
					.setParameter("ackn", "Rjct");
		}
		else {
			 qry = em
					.createQuery("select a.firstname,a.lastname,a.primaryEmail,a.mobileNo, a.state,"
							+ "b.loanAmount,b.tenure, b.appStatus,b.approvedBy,b.Remarks, c.loanTypeName,d.loanTransactionId "
							+ "from UserRegistration a, LoanTransaction b, LoanType c, LoanTransaction d  "
							+ "where a.userResId = b.ur   and  a.userResId = d.ur and b.appStatus=:ackn and b.loanType= c.loanTypeId")
					.setParameter("ackn", "Ackn");
		}
		List<Object[]> userDetsList = (List<Object[]>) qry.getResultList();

//		Map <String,UserDetails> map = new HashMap<String,UserDetails>();
//		UserDetails userDtls = new UserDetails();
//		for(UserDetails i : userDetsList) {
//			
//			map.put("name", i.getMobileNo());
//			ud.add(userDtls);
//			System.out.println(map);
//			
//		}
		for (int i = 0; i < userDetsList.size(); i++) {
			System.out.println(userDetsList.size());
			
			System.out.println(userDetsList.get(i)[11] +"  "+userDetsList.get(i)[0]+"  "+userDetsList.get(i)[1]+"  "+userDetsList.get(i)[2]+"  "
								+userDetsList.get(i)[3]+"  "+userDetsList.get(i)[4]+"  "+userDetsList.get(i)[5] );
			UserDetails userDtls = new UserDetails();
			if(userDetsList.get(i)[0]!= null&& userDetsList.get(i)[0]!="") {
				userDtls.setFirstname(userDetsList.get(i)[0].toString());
			}if(userDetsList.get(i)[1]!= null&& userDetsList.get(i)[1]!="") {
				userDtls.setLastname(userDetsList.get(i)[1].toString());
			}if(userDetsList.get(i)[2]!= null&& userDetsList.get(i)[2]!="") {
				userDtls.setPrimaryEmail(userDetsList.get(i)[2].toString());
			}if(userDetsList.get(i)[3]!=null) {
				userDtls.setMobileNo((long) userDetsList.get(i)[3]);
			}if(userDetsList.get(i)[4]!= null && userDetsList.get(i)[4]!="") {
				userDtls.setState(userDetsList.get(i)[4].toString());
			}if(userDetsList.get(i)[5]!= null) {
				userDtls.setLoanAmt((double) userDetsList.get(i)[5]);
			}if(userDetsList.get(i)[6]!= null) {
				userDtls.setLoanTenure((float) userDetsList.get(i)[6]);
			}if(userDetsList.get(i)[7].toString()!= null&& userDetsList.get(i)[7].toString()!="") {
				userDtls.setAppStatus(userDetsList.get(i)[7].toString());
			}if(userDetsList.get(i)[10].toString()!= null&& userDetsList.get(i)[10].toString()!="") {
				userDtls.setLoanTypeName(userDetsList.get(i)[10].toString());
			}if(userDetsList.get(i)[11]!= null) {
				userDtls.setUr((int) userDetsList.get(i)[11]);
			}


			ud.add(userDtls);
//			System.out.println(ud.get(0)+" "+ud.get(1)+" "+ud.get(2)+" "+ud.get(3));
		}

		return ud;

	}
	
	@Override
	@Transactional
	public int updateUser(UserDetails userDtls) {

		LoanTransaction lt = em.find(LoanTransaction.class, userDtls.getUr());

		if (userDtls != null) {
			System.out.println("userDtls.getRemarks()" + userDtls.getRemarks());
			lt.setAppStatus(userDtls.getAppStatus());
			if(userDtls.getRemarks() != null)
				lt.setRemarks(userDtls.getRemarks());
			else
				lt.setRemarks(userDtls.getRemark());
			em.persist(lt);

		}

		return 0;

	}
	
	// file upload
	@Override
	public void upload(MultipartFile file) throws IOException {
		// file.transferTo(new File("D:\\Learning\\"+file.getOriginalFilename()));
		file.transferTo(new File("C:\\Users\\chanc\\OneDrive\\Desktop\\GIT\\" + file.getOriginalFilename()));

	}

	@Override
	public List<UserDetails> getDetsForAdmin() {

		List<UserDetails> ud = new ArrayList<UserDetails>();
		Query qry = em
				.createQuery("select a.firstname,a.lastname,a.primaryEmail,a.mobileNo, a.city, a.state,"
						+ "b.loanAmount,b.tenure, b.appStatus,b.approvedBy,b.Remarks, c.loanTypeName "
						+ "from UserRegistration a, LoanTransaction b, LoanType c "
						+ "where a.userResId = b.ur and b.appStatus=:ackn and b.loanType= c.loanTypeId")
				.setParameter("ackn", "Ackn");
		List<Object[]> userDetsList = (List<Object[]>) qry.getResultList();

		for (int i = 0; i < userDetsList.size(); i++) {
			System.out.println(userDetsList.size());

			System.out.println(userDetsList.get(i)[0] + "  " + userDetsList.get(i)[1] + "  " + userDetsList.get(i)[2]
					+ "  " + userDetsList.get(i)[3] + "  " + userDetsList.get(i)[4] + "  " + userDetsList.get(i)[5]);
			UserDetails userDtls = new UserDetails();
			if (userDetsList.get(i)[0].toString() != null) {
				userDtls.setFirstname(userDetsList.get(i)[0].toString());
			}
			if (userDetsList.get(i)[1].toString() != null) {
				userDtls.setLastname(userDetsList.get(i)[1].toString());
			}
			if (userDetsList.get(i)[2].toString() != null) {
				userDtls.setPrimaryEmail(userDetsList.get(i)[2].toString());
			}
			if (userDetsList.get(i)[3] != null) {
				userDtls.setMobileNo((long) userDetsList.get(i)[3]);
			}
//				userDtls.setCity(userDetsList.get(i)[4].toString());
			if (userDetsList.get(i)[5] != null) {
				userDtls.setState(userDetsList.get(i)[5].toString());
			}
			if (userDetsList.get(i)[6] != null) {
				userDtls.setLoanAmt((double) userDetsList.get(i)[6]);
			}
			if (userDetsList.get(i)[7] != null) {
				userDtls.setLoanTenure((float) userDetsList.get(i)[7]);
			}
			if (userDetsList.get(i)[8] != null) {
				userDtls.setAppStatus(userDetsList.get(i)[8].toString());
			}
//				userDtls.setApprovedBy(userDetsList.get(i)[9].toString());
//				userDtls.setRemarks(userDetsList.get(i)[10].toString());
			if (userDetsList.get(i)[11] != null) {
				userDtls.setLoanTypeName(userDetsList.get(i)[11].toString());
			}

			ud.add(userDtls);
//			System.out.println(ud.get(0)+" "+ud.get(1)+" "+ud.get(2)+" "+ud.get(3));
		}

		return ud;

	}

}
