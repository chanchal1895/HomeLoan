package com.lti.homeloan.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_REGISTRATION_MST")
public class UserRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REGISTRATION_USER_MST_ID")
	private int userResId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "MOBILE_NUMBER")
	private long mobileNo;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "CITY")
	private String city;
	@Column(name = "STATE")
	private String state;
	@Column(name = "APPLICATION_NUMBER")
	private int applicationNo;
	@OneToOne
	@JoinColumn(name="userId")
	private UserLogin userLogin;
	
	
	

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public int getUserResId() {
		return userResId;
	}

	public void setUserResId(int userResId) {
		this.userResId = userResId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public int getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}

	
	public UserRegistration(int userResId, String firstName, String lastName, long mobileNo, String gender,
			String emailId, String city, String state, int applicationNo, UserLogin userLogin) {
		super();
		this.userResId = userResId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.emailId = emailId;
		this.city = city;
		this.state = state;
		this.applicationNo = applicationNo;
		this.userLogin = userLogin;
	}

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRegistration [userResId=" + userResId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", gender=" + gender + ", emailId=" + emailId + ", city=" + city
				+ ", state=" + state + ", applicationNo=" + applicationNo + ", userLogin=" + userLogin + "]";
	}


}
