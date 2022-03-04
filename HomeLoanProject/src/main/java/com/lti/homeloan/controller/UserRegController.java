package com.lti.homeloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.dao.UserRegDaoJPARepository;
import com.lti.homeloan.service.UserRegServiceImpl;
@CrossOrigin("*")
@RestController
@RequestMapping("/userregistration")
public class UserRegController {
	
	@Autowired
	private UserRegServiceImpl userService;
	@Autowired
	private UserRegDaoJPARepository userRegDaoJPARepository;

	
	@PostMapping("/addUser")
	public int addUser(@RequestBody UserDetails userDts) {
		
		System.out.println("UserRegistration"+userDts);
		
		return userService.registerUser(userDts);	
	}	
	@PostMapping("/validate")
	public int validate(@RequestBody UserDetails userDts) {
		
		System.out.println("UserRegistration"+userDts);
		
		return userService.validate(userDts);	
	}

	@PostMapping("/savecontactusinfo")
	public String addContactUsInfo(@RequestBody UserDetails  contactUs) {
		
		System.out.println("ContactUs"+contactUs);
		
		return userService.saveContactUsInfo(contactUs);	
	}
		@GetMapping("/userHome")
	@ResponseBody
	public UserDetails getUserLoanDetails(@RequestParam("id") String userid) {

		System.out.println("userHome :" + userid);

		return userService.getUserLoanDetails(userid);
	}
	
		
		//@Override
		@PutMapping(path = "/updateUserDetails")
		public int updateUserDetails(int userResId, long mobileNo) {
			
			//return dao.updateSalary(empNo, sal);
			return 0;
		}	

}
