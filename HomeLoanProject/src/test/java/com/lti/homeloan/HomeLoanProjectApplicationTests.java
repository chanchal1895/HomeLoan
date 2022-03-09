package com.lti.homeloan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.homeloan.bean.UserDetails;
import com.lti.homeloan.service.UserRegServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HomeLoanProjectApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserRegServiceImpl userService;

	@Test
	public void userRegister() throws Exception {

		UserDetails u1 = new UserDetails(1, "Jasmin", "Barett", 8282658575l, "F", "Jasmin@gmail.com", "CA", 100000, 25,
				"jas123", 2);
		Mockito.when(userService.registerUser(u1)).thenReturn(u1.getUr());
	}

	@Test
	public void validateUser() throws Exception {
		UserDetails u1 = new UserDetails(1, "Jasmin", "Barett", 8282658575l, "F", "Jasmin@gmail.com", "CA", 100000, 25,
				"jas123", 2);

		Mockito.when(userService.validate(u1)).thenReturn(u1.getUr());

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("http://localhost:8080/userregistration/validate")
						.content(asJsonString(new UserDetails("Jasmin@gmail.com", "jasmin123")))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void getAllUsersList() throws Exception {
		
		List<UserDetails> userList=new ArrayList<UserDetails>();
		UserDetails u1 = new UserDetails(1, "Jasmin", "Barett", 8282658575l, "F", "Jasmin@gmail.com", "CA", 1500000, 25,
				"Jasmin123", 2);
		UserDetails u2 = new UserDetails(2, "Jacob", "Christopher", 6585754585l, "M", "Jacob@gmail.com", "AL", 2000000, 25,
				"Jacob123", 2);
		UserDetails u3 = new UserDetails(3, "Peter", "Jackson", 9878458565l, "M", "Peter@gmail.com", "TN", 6500000, 25,
				"Peter123", 2);
		
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		
		Mockito.when(userService.getDetsForAdmin()).thenReturn(userList);
		this.mockMvc.perform(get("http://localhost:8080/userregistration/userList")).andExpect(status().isOk());
	}
	
	@Test
	public void addContactUsInfo() {
		UserDetails us= new UserDetails("Jasmin","Barett","Jasmin@gmail.com","Enquiry regarding interest rate and EMI","Regarding EMI");
		Mockito.when(userService.saveContactUsInfo(us)).thenReturn(us.getFirstname());		
	}	
}
