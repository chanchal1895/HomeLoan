package com.lti.homeloan.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT_US_MST")
public class ContactUs {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CONTACT_US_ID")
	private int pn;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="EMAIL_ID")
	private String emailId;
	@Column(name="MESSAGE")
	private String message;
	@Column(name="SUBJECT")
	private String subject;
	
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public ContactUs(String firstName, String lastName, String emailId, String message, String subject) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.message = message;
		this.subject = subject;
	}
	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ContactUs [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", message="
				+ message + ", subject=" + subject + "]";
	}
	
}
