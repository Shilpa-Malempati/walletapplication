package com.capgemini.xyz.service;

import java.util.List;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Transaction;
import com.capgemini.xyz.exception.CustomerExistsException;
import com.capgemini.xyz.exception.CustomerNotFoundException;
import com.capgemini.xyz.exception.IllegalFormatException;
import com.capgemini.xyz.exception.InsufficientBalanceException;

public interface CustomerServiceInterface {

	String userNamePattern="[A-Z][a-z]{1,8}";
	String usermobilePattern="(0/91)?[7-9][0-9]{9}";
	String useremailPattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String userpasswordPattern="[a-zA-Z0-9,.@_]{6,12}";
	String userHomeChoice="[1-3]{1}";
	String userMenuChoice="[1-6]{1}";
	String userAmount="[0-9]{2,10}";
	
	 boolean validateName(String name) throws IllegalFormatException;
	 boolean validateMobNo(String mobileno) throws IllegalFormatException;
	 boolean validateEmail(String email) throws IllegalFormatException;
	 boolean validatePassword(String password) throws IllegalFormatException;
	 boolean validateHomeChoice(String userHChoice) throws IllegalFormatException;
	 boolean validateMenuChoice(String userHChoice) throws IllegalFormatException;
	 boolean validateAmount(String useramt) throws IllegalFormatException;
	 
	String withDraw(String mobileNumber,double amount) throws InsufficientBalanceException;
	
	String deposit(String mobileNumber,double amount) throws CustomerNotFoundException;
	
	String[] fundTransfer(String fromCust,String toCust,double amount) throws InsufficientBalanceException,CustomerNotFoundException;
	
	List<Transaction> printTransaction(Customer customer);
	
	Customer createCustomer(Customer customer) throws CustomerExistsException;
	
	Customer checkUser(String username, String password) throws CustomerNotFoundException;
	
	double checkBalance(Customer customer);
	
	boolean isValidUser(String mobileNumber) throws CustomerNotFoundException;
}
