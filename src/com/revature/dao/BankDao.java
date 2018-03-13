package com.revature.dao;

import java.util.List;

import com.revature.pojo.BankDB;

public interface BankDao {
    public void createBankUser(BankDB bankDB);
	
	public BankDB retrieveBankUserById(int id);
	
	public BankDB retrieveBankUserByUsername(String u);
	
	public List<BankDB> retrieveAllBankAccounts();
	
	public void updateBankDB(BankDB bankDB);
	
	public void deleteBankDB(int id);
	
	public void createBankUserPreparedStmt(BankDB bankDB);
	
	public String verifyUsername(String u);
	
	public String verifyPassword(String p);
}
