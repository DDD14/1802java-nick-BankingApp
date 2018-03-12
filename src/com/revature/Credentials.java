package com.revature;

import static org.junit.jupiter.api.Assumptions.assumingThat;
import java.util.Scanner;

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
		}
		
		System.out.println("\nSuccessfully Registered!\n");
		log.logInfo("Successfully registered " + u);
	}
	
	public void validate() {
		String u, p;
		int validLogin = 0;
		Scanner reader = new Scanner(System.in);
		BankAccount account;

		System.out.print("Username: ");
		u = reader.nextLine();
		
		System.out.print("Password: ");
		p = reader.nextLine();
		
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(u) && passwords.get(i).equals(p)) {
				validLogin = 1;
				i = usernames.size();
				log.logInfo("Successfully logged user " + u + " in.");
				account = new BankAccount(u,p);
			}
		}
		
		if(validLogin != 1) {
			System.out.println("invalid login\n");
			log.logWarn("Failed login attempt for username: " + u);
		}
		
	}

}
