package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.BankDB;
import com.revature.util.ConnectionFactory;

public class BankDaoImpl implements BankDao {

	@Override
	public void createBankUser(BankDB bankDB) {
	    Connection conn = ConnectionFactory.getInstance().getConnection();
		
		try {
			Statement statement = conn.createStatement();
			
			String sql = "INSERT INTO BANK (BANK_USERNAME, BANK_PASSWORD, BANK_ACCOUNT_NUMBER, BANK_BALANCE, BANK_ISEMPLOYEE, BANK_ISADMIN, BANK_APPROVED) VALUES('" + bankDB.getUsername() + "', '" + bankDB.getPassword() + "', '" + bankDB.getAccountNumber() + "', '" + bankDB.getBalance() + "', '" + bankDB.getIsEmployee() + "', '" + bankDB.getIsAdmin() + "', '" + bankDB.getApproved() + "')";
			
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public BankDB retrieveBankUserById(int id) {
		BankDB db = new BankDB();
		
		String sql = "SELECT * FROM BANK WHERE BANK_PK = ?";
		
		try {
			PreparedStatement ps =
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				db.setId(rs.getInt(1));
				db.setUsername(rs.getString(2));
				db.setPassword(rs.getString(3));
				db.setAccountNumber(rs.getInt(4));
				db.setBalance(rs.getDouble(5));
				db.setIsEmployee(rs.getInt(6));
				db.setIsAdmin(rs.getInt(7));
				db.setApproved(rs.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return db;
	}
	
	@Override
	public BankDB retrieveBankUserByUsername(String u) {
		BankDB db = new BankDB();
		
		String sql = "SELECT * FROM BANK WHERE BANK_USERNAME = ?";
		
		try {
			PreparedStatement ps =
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, u);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				db.setId(rs.getInt(1));
				db.setUsername(rs.getString(2));
				db.setPassword(rs.getString(3));
				db.setAccountNumber(rs.getInt(4));
				db.setBalance(rs.getDouble(5));
				db.setIsEmployee(rs.getInt(6));
				db.setIsAdmin(rs.getInt(7));
				db.setApproved(rs.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return db;
	}

	@Override
	public List<BankDB> retrieveAllBankAccounts() {
		List<BankDB> dbs = new ArrayList<>();
		
		String sql = "SELECT * FROM BANK";
		
		try {
			PreparedStatement ps =
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				dbs.add(new BankDB(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbs;
	}

	@Override
	public void updateBankDB(BankDB bankDB) {
		String sql = ("UPDATE BANK SET BANK_USERNAME = ?, BANK_PASSWORD = ?, BANK_ACCOUNT_NUMBER = ?, BANK_BALANCE = ?, BANK_ISEMPLOYEE = ?, BANK_ISADMIN = ?, BANK_APPROVED = ? WHERE BANK_PK = " + bankDB.getId());
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bankDB.getUsername());
			ps.setString(2, bankDB.getPassword());
			ps.setInt(3, bankDB.getAccountNumber());
			ps.setDouble(4, bankDB.getBalance());
			ps.setInt(5, bankDB.getIsEmployee());
			ps.setInt(6, bankDB.getIsAdmin());
			ps.setInt(7, bankDB.getApproved());
	        ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteBankDB(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createBankUserPreparedStmt(BankDB bankDB) {
	    
		String sql = "INSERT INTO BANK (BANK_USERNAME, BANK_PASSWORD, BANK_ACCOUNT_NUMBER, BANK_BALANCE, BANK_ISEMPLOYEE, BANK_ISADMIN, BANK_APPROVED) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bankDB.getUsername());
			ps.setString(2, bankDB.getPassword());
			ps.setInt(3, bankDB.getAccountNumber());
			ps.setDouble(4, bankDB.getBalance());
			ps.setInt(5, bankDB.getIsEmployee());
			ps.setInt(6, bankDB.getIsAdmin());
			ps.setInt(7, bankDB.getApproved());
	        ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public String verifyUsername(String u){
		String sql = "SELECT BANK_USERNAME FROM BANK WHERE BANK_USERNAME = ?";
		String result = null;
		
		try {
			PreparedStatement ps =
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, u);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String verifyPassword(String p){
		String sql = "SELECT BANK_PASSWORD FROM BANK WHERE BANK_PASSWORD = ?";
		String result = null;
		
		try {
			PreparedStatement ps =
					ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, p);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
