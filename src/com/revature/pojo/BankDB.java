package com.revature.pojo;

public class BankDB {
	int id;
	String username;
	String password;
	int accountNumber;
	double balance;
	int isEmployee, isAdmin, approved;
	public BankDB() {
		super();
	}
	@Override
	public String toString() {
		return "BankDB [id=" + id + ", username=" + username + ", password=" + password + ", accountNumber="
				+ accountNumber + ", balance=" + balance + ", isEmployee=" + isEmployee + ", isAdmin=" + isAdmin + "]";
	}
	public BankDB(int id, String username, String password, int accountNumber, double balance, int isEmployee, int isAdmin, int approved) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.isEmployee = isEmployee;
		this.isAdmin = isAdmin;
		this.approved = approved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getIsEmployee() {
		return isEmployee;
	}
	public void setIsEmployee(int isEmployee) {
		this.isEmployee = isEmployee;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
}
