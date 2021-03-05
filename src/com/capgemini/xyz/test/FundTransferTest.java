package com.capgemini.xyz.test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.exception.CustomerExistsException;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.InsufficientBalanceException;
import com.capgemini.xyz.service.CustomerService;
import com.capgemini.xyz.service.CustomerServiceInterface;

public class FundTransferTest {

	CustomerServiceInterface service = null;

	@Before
	public void setUp() throws Exception {
		service = new CustomerService();
	}

	// right inputs
	@Test
	public void checkFundTransfer() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			Customer customer2 = new Customer("Aditya", "a@g.c", "9892622745",
					"password", 5000);
			service.createCustomer(customer);
			service.createCustomer(customer2);

			String[] result = service.fundTransfer(customer.getMobileNumber(),
					customer2.getMobileNumber(), 2000);
			assertNotNull(result);
		} catch (CustomerExistsException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// wrong inputs
	// should print insufficient balance in console
	@Test
	public void checkFundTransfer2() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			Customer customer2 = new Customer("Aditya", "a@g.c", "9892622745",
					"password", 5000);
			service.createCustomer(customer);
			service.createCustomer(customer2);

			String[] result = service.fundTransfer(customer.getMobileNumber(),
					customer2.getMobileNumber(), 9000);
			assertNotNull(result);
		} catch (CustomerExistsException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	//wrong inputs
	//should give invalid mobile number
	@Test
	public void checkFundTransfer3() {
		try {
			Customer customer = new Customer("Tushar", "t@g.c", "8286703935",
					"password", 5000);
			Customer customer2 = new Customer("Aditya", "a@g.c", "9892622745",
					"password", 5000);
			service.createCustomer(customer);
			service.createCustomer(customer2);

			String[] result = service.fundTransfer(customer.getMobileNumber(),
					"9867480638", 2000);
			assertNotNull(result);
		} catch (CustomerExistsException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e) {
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
