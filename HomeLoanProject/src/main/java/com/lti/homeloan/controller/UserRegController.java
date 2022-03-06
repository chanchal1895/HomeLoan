package com.lti.homeloan.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.bean.UserRegistration;
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
	
		
		//http://localhost:8080/userregistration/updateUserDetails
		@PutMapping(path = "/updateUserDetails")
		public int updateUserDetails(@RequestBody UserDetails userRegistration) {

			//System.out.println("userResId :" + userResId);
			
			return userService.updateUserDetails(userRegistration);
		}	
		
		//file upload
		
		 @PostMapping("/upload")
		  public void upload(@RequestParam MultipartFile file) throws IOException {
			  
			  System.out.println(" inside controller ");
			  userService.upload(file);
		  }
		  
	@GetMapping("/userList")
	public List<UserDetails> getAllUsers() {
		return userService.getDetsForAdmin();
	}
		

}
