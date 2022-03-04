package com.lti.homeloan.bean;


public class UserDetails {
private String firstname;
	private String lastname;
	private long mobileNo;
	private String gender;
	private String primaryEmail;
	private String city;
	private String state;
	private double loanAmt;
	private float loanTenure;
	private String password;
	private String userRole;
	private String loanType;
	private int loanTypeId;
	private int ur;
	private String message;
	private String subject;
	private String formName;
	private String appStatus;
	private String approvedBy;
	private String loanTypeName;
	private String Remarks;
	private double loanEMI;
	private double interestRate;
	private double totIntPayable;
	private double totPayment;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public float getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(float loanTenure) {
		this.loanTenure = loanTenure;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(int loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	public int getUr() {
		return ur;
	}
	public void setUr(int ur) {
		this.ur = ur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getLoanTypeName() {
		return loanTypeName;
	}
	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public double getLoanEMI() {
		return loanEMI;
	}
	public void setLoanEMI(double loanEMI) {
		this.loanEMI = loanEMI;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getTotIntPayable() {
		return totIntPayable;
	}
	public void setTotIntPayable(double totIntPayable) {
		this.totIntPayable = totIntPayable;
	}
	public double getTotPayment() {
		return totPayment;
	}
	public void setTotPayment(double totPayment) {
		this.totPayment = totPayment;
	}
	public UserDetails(String firstname, String lastname, long mobileNo, String gender, String primaryEmail,
			String city, String state, double loanAmt, float loanTenure, String password, String userRole,
			String loanType, int loanTypeId, int ur, String message, String subject, String formName, String appStatus,
			String approvedBy, String loanTypeName, String remarks, double loanEMI, double interestRate,
			double totIntPayable, double totPayment) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.primaryEmail = primaryEmail;
		this.city = city;
		this.state = state;
		this.loanAmt = loanAmt;
		this.loanTenure = loanTenure;
		this.password = password;
		this.userRole = userRole;
		this.loanType = loanType;
		this.loanTypeId = loanTypeId;
		this.ur = ur;
		this.message = message;
		this.subject = subject;
		this.formName = formName;
		this.appStatus = appStatus;
		this.approvedBy = approvedBy;
		this.loanTypeName = loanTypeName;
		Remarks = remarks;
		this.loanEMI = loanEMI;
		this.interestRate = interestRate;
		this.totIntPayable = totIntPayable;
		this.totPayment = totPayment;
	}
	public UserDetails() {
//		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", mobileNo=" + mobileNo + ", gender="
				+ gender + ", primaryEmail=" + primaryEmail + ", city=" + city + ", state=" + state + ", loanAmt="
				+ loanAmt + ", loanTenure=" + loanTenure + ", password=" + password + ", userRole=" + userRole
				+ ", loanType=" + loanType + ", loanTypeId=" + loanTypeId + ", ur=" + ur + ", message=" + message
				+ ", subject=" + subject + ", formName=" + formName + ", appStatus=" + appStatus + ", approvedBy="
				+ approvedBy + ", loanTypeName=" + loanTypeName + ", Remarks=" + Remarks + ", loanEMI=" + loanEMI
				+ ", interestRate=" + interestRate + ", totIntPayable=" + totIntPayable + ", totPayment=" + totPayment
				+ "]";
	}


	
}
