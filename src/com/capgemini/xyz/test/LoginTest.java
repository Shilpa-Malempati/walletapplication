package com.capgemini.xyz.test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.exception.CustomerExistsException;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.service.CustomerService;
import com.capgemini.xyz.service.CustomerServiceInterface;

public class LoginTest {

	CustomerServiceInterface service = null;

	@Before
	public void setUp() throws Exception {
		service = new CustomerService();
	}

	//right inputs
	@Test
	public void checkLogin() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 45000);
			service.createCustomer(customer);
			Customer cust = service.checkUser("8286703935","password");
			assertNotNull(cust);
		} catch (CustomerExistsException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	//wrong inputs
	// should print no user in console
	@Test
	public void checkLogin2() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 45000);
			service.createCustomer(customer);
			Customer cust2 = service.checkUser("23123123","asdasd");
			assertNotNull(cust2);
		} catch (CustomerExistsException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}	
	@After
	public void destroy() throws Exception {
		service = null;
	}
}
