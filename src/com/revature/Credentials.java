package com.revature;

import static org.junit.jupiter.api.Assumptions.assumingThat;
import java.util.Scanner;

import com.revature.dao.BankDao;
import com.revature.dao.BankDaoImpl;
import com.revature.pojo.BankDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Credentials {
	private static List<String> usernames = new ArrayList<>();
	private static List<String> passwords = new ArrayList<>();
	
	private LoggingUtil log = new LoggingUtil();
	
	public Credentials() {
	/*	try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usernames.dat"))){
			
			usernames = (ArrayList<String>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("passwords.dat"))){
			
			passwords = (ArrayList<String>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public void register() {
		Scanner reader = new Scanner(System.in);
		BankDB db = new BankDB();
		BankDao bd = new BankDaoImpl();
		int validInput;
		String u, p;
		
		System.out.println("\nPlease fill out the following fields:");
		do {
			System.out.print("Username: ");
			u = reader.nextLine();
			if(u.charAt(0) == ' ') {
				validInput = 0;
				System.out.println("invalid input, please enter a Username\n");
			}
			else if(u.charAt(0) == 0) {
				validInput = 0;
				System.out.println("invalid input, please enter a Username\n");
			}
			else
				validInput = 1;
		}while(validInput == 0);
		System.out.print("Password: ");
		p = reader.nextLine();
		
		db.setUsername(u);
		db.setPassword(p);
		
		bd.createBankUserPreparedStmt(db);
		/*
		this.usernames.add(u);
		this.passwords.add(p);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usernames.dat"))){
			
			oos.writeObject(usernames);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("passwords.dat"))){
			
			oos.writeObject(passwords);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("\nSuccessfully Registered!\n");
		log.logInfo("Successfully registered " + u);
	}
	
	public void validate() {
		String u, p;
		int validLogin = 0;
		Scanner reader = new Scanner(System.in);
		BankAccount account;
		BankDB db = new BankDB();
		BankDao bd = new BankDaoImpl();

		System.out.print("Username: ");
		u = reader.nextLine();
		
		System.out.print("Password: ");
		p = reader.nextLine();
		
		try {
			if(bd.verifyUsername(u).equals(u) && bd.verifyPassword(p).equals(p)) {
				validLogin = 1;
				log.logInfo("Successfully logged user " + u + " in.");
				account = new BankAccount(u,p);
			}
		}
		catch(NullPointerException e) {
			log.logError("NullPointerException Caught in Credentials");
			validLogin = 0;
		}
		
		if(validLogin != 1) {
			System.out.println("invalid login\n");
			log.logWarn("Failed login attempt for username: " + u);
		}
		
	}

}
