package com.capgemini.xyz.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Transaction;
import com.capgemini.xyz.exception.CustomerExistsException;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.InsufficientBalanceException;

public class CustomerDAO implements ICustomerDAO {

	// to store customer data
	ArrayList<Customer> database = new ArrayList<Customer>();

	// to store transaction data
	ArrayList<Transaction> transactionDB = new ArrayList<Transaction>();

	// to generate id of customer
	long newID = ids;

	// withdraw money
	@Override
	public String withDraw(String mobileNumber, double amount)
			throws InsufficientBalanceException {

		// find customer
		//Customer customer = null;
		Customer customer = database.stream()
				.filter(x -> x.getMobileNumber().equals(mobileNumber))
				.findFirst().get();

		// withdraw logic
		// minimum balance should be atleast Rs.100
		if (amount <= customer.getBalance() - 100) {
			customer.setBalance(customer.getBalance() - amount);

			// store transaction history
			Transaction recordTrans = new Transaction(mobileNumber, "DB",
					amount, customer.getBalance());
			transactionDB.add(recordTrans);

			// return success data in string format
			return "Rs." + amount + " debited from account "
			+ customer.getCustomerId() + " on " + LocalDateTime.now()
			+ "\nNew Balance is Rs." + customer.getBalance();
		} else

			// throws exception if balance is below 1000
			// will be cached by service class
			throw new InsufficientBalanceException("You Have Insufficient Amount.");
	}

	@Override
	public String deposit(String mobileNumber, double amount) throws CustomerNotFoundException{

		try {
			// find customer
			Customer customer = database.stream()
					.filter(x -> x.getMobileNumber().equals(mobileNumber))
					.findFirst().get();
			customer.setBalance(customer.getBalance() + amount);

			// store transaction history
			Transaction recordTrans = new Transaction(mobileNumber, "CR", amount,
					customer.getBalance());
			transactionDB.add(recordTrans);

			// return success data in string format
			return "Rs." + amount + " credited on account "
			+ customer.getCustomerId() + " on " + LocalDateTime.now()
			+ "\nNew Balance is Rs." + customer.getBalance();
		} catch (Exception e) {
			//if user not found
			throw new CustomerNotFoundException("Invalid Mobile Number");
		}
	}

	@Override
	public List<Transaction> printTransaction(Customer customer) {

		// create a list to return transaction history of a user
		List<Transaction> summaryList = new ArrayList();

		// find summary of the user
		//Missing code

		for(int i = 0;i<=transactionDB.size()-1;i++) {
			Transaction transaction = transactionDB.get(i);
			String phoneNo = transaction.getMobileNo();
			if(customer.getMobileNumber().equals(phoneNo)) {
				summaryList.add(transaction);
			}
		}
		return summaryList;
	}

	@Override
	public Customer createCustomer(Customer customer) throws CustomerExistsException {

		// check if user exists
		// count should be 0
		long count = database
				.stream()
				.filter(x -> x.getMobileNumber().equals(
						customer.getMobileNumber())).count();
		if (count < 1) {
			// add customer
			customer.setCustomerId(++newID);
			database.add(customer);

			// store its first transaction
			Transaction recordTrans = new Transaction(
					customer.getMobileNumber(), "CR", customer.getBalance(),
					customer.getBalance());
			transactionDB.add(recordTrans);
			return customer;
		} else
			// if customer exists then throw error
			throw new CustomerExistsException("User Already Exists");
	}

	@Override
	public Customer checkUser(String username, String password)
			throws CustomerNotFoundException {

		// to login
		// check username which is mobile number and password
		try {
			Customer customer = database
					.stream()
					.filter(x -> x.getMobileNumber().equals(username)
							&& x.getPassword().equals(password)).findFirst()
					.get();
			return customer;
		} catch (Exception e) {

			// if invalid credentials
			throw new CustomerNotFoundException("No User Found");
		}
	}

	@Override
	public double checkBalance(Customer customer) {

		// fetch balance of user
		double balance = customer.getBalance();
		return balance;
	}

	public boolean isValidUser(String mobileNumber) throws CustomerNotFoundException{

		try {
			return true;
		} catch (Exception e) {
			//if user not found
			throw new CustomerNotFoundException("Invalid Mobile Number");
		}
	}
}
