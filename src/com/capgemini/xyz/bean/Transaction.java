package com.capgemini.xyz.bean;

import java.io.Serializable;

public class Transaction implements Serializable {

	private String mobileNo;
	private String type;
	private double amount;
	private double balance;

	// getters setters
	public String getMobileNo() {
		return mobileNo;
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	// parameterized constructor
	public Transaction(String mo, String type, double amount, double balance) {
		this.mobileNo = mo;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
	}

	// to string method
	@Override
	public String toString() {
		return "Transaction [mobileNo=" + mobileNo + ", type=" + type
				+ ", amount=" + amount + ", balance=" + balance + "]";
	}

}
