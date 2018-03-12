package com.revature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankAccount {
	private static List<String> usernames = new ArrayList<>();
	private static List<String> passwords = new ArrayList<>();
	private static List<Integer> accountNumbers = new ArrayList<>();
	private static List<Double> balance = new ArrayList<>();
	private static List<Boolean> isEmployee = new ArrayList<>();
	private static List<Boolean> isAdmin = new ArrayList<>();
	private static List<Boolean> approved = new ArrayList<>();
	private String validUsername, validPassword;
	
	private LoggingUtil log = new LoggingUtil();
	
	public BankAccount(String username, String password){
		deSerialize();
		this.validUsername = username;
		this.validPassword = password;
		
		try {
			for(int i = 0; i < this.usernames.size(); i++) {
				if(usernames.get(i).equals(this.validUsername) && accountNumbers.get(i) == null) {
					i = this.usernames.size();
					firstTimeBankMenu();
				}
			}
			
			bankMenu();
		}
		catch(IndexOutOfBoundsException e) {
			log.logError("IndexOutOfBoundsException caught in Credentials");
			firstTimeBankMenu();
		}
		
	}
	
	public void firstTimeBankMenu() {
		int selectionValid;
		String selection;
		Scanner reader = new Scanner(System.in);
		
		System.out.println("\nWelcome " + this.validUsername + "!\n");
		
		do {
			System.out.println("What would you like to do?\n");
			System.out.println("1. Open Account");
			System.out.println("2. Employee Login");
			System.out.println("3. Admin Login");
			System.out.println("4. Logout");
			
			selection = reader.next();
			
			switch(selection) {
			case "1": openAccount();
			        selectionValid = 1;
			        break;
			case "2": employeeLogin();
			        selectionValid = 1;
			        break;
			case "3": adminLogin();
				    selectionValid = 1;
				    break;
			case "4": selectionValid = 1;
	                break;
			default: System.out.println("invalid selection");
			         selectionValid = 0;
			}
			
		}while(selectionValid == 0);
	}
	
	public void bankMenu() {
		int selectionValid = 0;
		String selection;
		Scanner reader = new Scanner(System.in);
		
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(this.validUsername)) {
				if(approved.get(i) == false) {
					System.out.println("Account must be activated by a bank employee, please login later.");
					i = this.usernames.size();
				}
				else {
					do {
						if(isEmployee.get(i) == true) {
							System.out.println("What would you like to do?\n");
							System.out.println("1. Approve Account");
							System.out.println("2. List Accounts");
							System.out.println("3. Open an Account");
							System.out.println("4. Logout");
							
							selection = reader.next();
							
							switch(selection) {
							case "1": approveAccount();
							          selectionValid = 0;
							          break;
							case "2": listAccounts();
							          selectionValid = 0;
							          break;
							case "3": employeeAccount();
								      selectionValid = 0;
								      break;
							case "4": selectionValid = 1;
					                  break;
							default: System.out.println("invalid selection");
							         selectionValid = 0;
							}
							
						}
						else if(isAdmin.get(i) == true) {
							System.out.println("What would you like to do?\n");
							System.out.println("1. Approve Acount");
							System.out.println("2. List Acounts");
							System.out.println("3. Open an Acount");
							System.out.println("4. Withdraw from an Acount");
							System.out.println("5. Deposit into an Acount");
							System.out.println("6. Transfer between Acounts");
							System.out.println("7. Cancel Acount");
							System.out.println("8. Logout");
							
							selection = reader.next();
							
							switch(selection) {
							case "1": approveAccount();
							          selectionValid = 0;
							          break;
							case "2": listAccounts();
							          selectionValid = 0;
							          break;
							case "3": employeeAccount();
								      selectionValid = 0;
								      break;
							case "4": System.out.print("Enter username of Acount: ");
									  withdraw(reader.next());
									  selectionValid = 0;
					                  break;
							case "5": System.out.print("Enter username of Acount: ");
									  deposit(reader.next());
							          selectionValid = 0;
			                          break;
							case "6": System.out.print("Enter username of Acount: ");
							  		  transfer(reader.next());
							  		  selectionValid = 0;
							  		  break;
							case "7": System.out.print("Enter username of Acount: ");
									  cancelAcount(reader.next());
									  selectionValid = 0;
									  break;
							case "8": selectionValid = 1;
							  		  break;
							default: System.out.println("invalid selection");
							         selectionValid = 0;
							}
							
						}
						else {
							System.out.println("What would you like to do?\n");
							System.out.println("1. Deposit into Account");
							System.out.println("2. Withdraw from Account");
							System.out.println("3. Transfer funds between accounts");
							System.out.println("4. View balance");
							System.out.println("5. Open another account");
							System.out.println("6. Logout");
							
							selection = reader.next();
							
							switch(selection) {
							case "1": deposit(this.validUsername);
							          selectionValid = 0;
							          break;
							case "2": withdraw(this.validUsername);
							          selectionValid = 0;
							          break;
							case "3": transfer(this.validUsername);
								      selectionValid = 0;
								      break;
							case "4": returnAmount(this.validUsername);
									  selectionValid = 0;
									  break;
							case "5": openAnotherAccount();
								      selectionValid = 0;
					                  break;
							case "6": selectionValid = 1;
									  break;
							default: System.out.println("invalid selection");
							         selectionValid = 0;
							}
						}
					}while(selectionValid == 0);
				}
			}
		}
	}
	
	public void openAccount() {
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(this.validUsername)) {
				if(i - 1 != -1)
					accountNumbers.add(accountNumbers.get(i - 1) + 1);
				else
					accountNumbers.add(1);
				isEmployee.add(false);
				isAdmin.add(false);
				balance.add(0.00);
				approved.add(false);
				i = this.usernames.size();
			}
		}
		
		System.out.println("Application submitted for account creation!\n");
		System.out.println("Account must be activated by a bank employee, please login later.");
		serializeDB();
	}
	
	public void openAnotherAccount() {
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(this.validUsername)) {
				if(i - 1 != -1)
					accountNumbers.add(accountNumbers.get(i - 1) + 1);
				isEmployee.add(false);
				isAdmin.add(false);
				balance.add(0.00);
				approved.add(false);
				i = this.usernames.size();
			}
		}
		
		System.out.println("Application submitted for account creation!\n");
		
	}
	
	public void employeeLogin() {
		Scanner reader = new Scanner(System.in);
		String input;
		int timeout = 0;
		
		do {
			System.out.print("Enter the password given to you by administration: ");
			input = reader.next();
			
			if(input.equals("employee")) {
				System.out.println("Account sucessfully activated! Welcome to the team!\n");
				
				for(int i = 0; i < this.usernames.size(); i++) {
					if(usernames.get(i).equals(this.validUsername)) {
						accountNumbers.add(0);
						isEmployee.add(true);
						isAdmin.add(false);
						balance.add(0.00);
						approved.add(true);
						i = this.usernames.size();
				        bankMenu();
					}
				}
			}
			else {
				System.out.println("Password incorrect");
				timeout++;
			}
			
			if(timeout == 3) {
				System.out.println("Maximum attempts to login reached.");
				input = "employee";
			}
		}while(!input.equals("employee"));
		serializeDB();	
	}
	
	public void adminLogin() {
		Scanner reader = new Scanner(System.in);
		String input;
		int timeout = 0;
		
		do {
			System.out.print("Enter the password given to you by administration: ");
			input = reader.next();
			
			if(input.equals("admin")) {
				System.out.println("Account sucessfully activated!\n");
				
				for(int i = 0; i < this.usernames.size(); i++) {
					if(usernames.get(i).equals(this.validUsername)) {
						accountNumbers.add(0);
						isEmployee.add(false);
						isAdmin.add(true);
						balance.add(0.00);
						approved.add(true);
						i = this.usernames.size();
				        bankMenu();
					}
				}
			}
			else {
				System.out.println("Password incorrect");
				timeout++;
			}
			
			if(timeout >= 3) {
				System.out.println("Maximum attempts to login reached.");
				input = "admin";
			}
		}while(!input.equals("admin"));
        serializeDB();
	}
	
	public void deposit(String s) {
		Scanner reader = new Scanner(System.in);
		double amount;
		
		returnAmount(s);
		System.out.print("\nEnter amount to deposit: ");
		amount = reader.nextDouble();
		
		if(amount > 0) {
			for(int i = 0; i < usernames.size(); i++) {
				if(usernames.get(i).equals(s)) {
					balance.set(i, amount + balance.get(i));
					System.out.println("Balance set to $" + balance.get(i) + "\n");
					i = usernames.size();
				}
			}
		}
		else {
			System.out.println("Invalid input. Amount must be greater than zero.\n");
		}
		serializeDB();
		log.logInfo("Deposited $" + amount + " into user acount " + s);
	}
	
	public void withdraw(String s) {
		Scanner reader = new Scanner(System.in);
		double amount;
		
		returnAmount(s);
		System.out.print("\nEnter amount to withdraw: ");
		amount = reader.nextDouble();
		
		if(amount > 0) {
			for(int i = 0; i < usernames.size(); i++) {
				if(usernames.get(i).equals(s) && balance.get(i) - amount >= 0) {
					balance.set(i, balance.get(i) - amount);
					System.out.println("Balance set to $" + balance.get(i) + "\n");
					i = usernames.size();
				}
				else if(usernames.get(i).equals(s)) {
					System.out.println("Error: You don't have the necessary funds to withdraw that amount.\n");
					i = usernames.size();
				}
			}
		}
		else {
			System.out.println("Invalid input. Amount must be greater than zero.\n");
		}
		serializeDB();
		log.logInfo("Withdrew $" + amount + " from user acount " + s);
	}
	
	public void transfer(String s) {
		Scanner reader = new Scanner(System.in);
		double amount;
		int accountNum;
		returnAmount(s);
		System.out.print("\nEnter account number to transfer to: ");
		accountNum = reader.nextInt();
		System.out.print("\nEnter amount to transfer: ");
		amount = reader.nextDouble();
		
		if(amount > 0) {
			for(int i = 0; i < usernames.size(); i++) {
				if(usernames.get(i).equals(s) && balance.get(i) - amount >= 0) {
					balance.set(i, balance.get(i) - amount);
					System.out.println("Transfered $" + amount + " to account " + accountNum + ". Your balance remaining is: " + balance.get(i) + "\n");
					i = usernames.size();
				}
				else if(usernames.get(i).equals(s)) {
					System.out.println("Error: You don't have the necessary funds to withdraw that amount.\n");
					i = usernames.size();
				}
			}
			
			for(int i = 0; i < accountNumbers.size(); i++) {
				if(accountNumbers.get(i) == accountNum && balance.get(i) - amount >= 0) {
					balance.set(i, balance.get(i) + amount);
					i = usernames.size();
				}
			}
		}
		else {
			System.out.println("Invalid input. Amount must be greater than zero.\n");
		}
		serializeDB();
		log.logInfo("User " + s + " transfered $" + amount + " into user acount " + accountNum);
	}
	
	public void returnAmount(String s) {
		for(int i = 0; i < usernames.size(); i++) {
			if(usernames.get(i).equals(s)) {
				System.out.println("Current balance: $" + balance.get(i) + "\n");
				i = usernames.size();
			}
		}
		serializeDB();
	}
	
	public void approveAccount() {
		Scanner reader = new Scanner(System.in);
		int input, accountsLeft = 0;
		
		for(int i = 0; i < accountNumbers.size(); i++) {
			if(approved.get(i) == false)
				accountsLeft++;
		}
		
		if(accountsLeft > 0) {
			System.out.println("Select an account to approve: ");
			for(int i = 0; i < accountNumbers.size(); i++) {
				if(approved.get(i) == false) {
					System.out.println(i + ". " + accountNumbers.get(i));
				}
			}
			input = reader.nextInt();
			
			for(int i = 0; i < accountNumbers.size(); i++) {
				if(i == input) {
					approved.set(i, true);
					System.out.println("Approved account number " + accountNumbers.get(i));
					i = accountNumbers.size();
				}
			}
		}
		else
			System.out.println("No accounts to approve.\n");
		
		serializeDB();
	}
	
	public void listAccounts() {
		for(int i = 0; i < usernames.size(); i++) {
			System.out.println("Username: " + usernames.get(i) + " Account Number: " + accountNumbers.get(i) + " Balance: $" + balance.get(i));
		}
		serializeDB();
	}
	
	public void cancelAcount(String s) {
		for(int i = 0; i < accountNumbers.size(); i++) {
			if(usernames.get(i).equals(s)) {
				approved.set(i, false);
				i = accountNumbers.size();
			}
		}
		
		System.out.println("Canceled acount \"" + s + "\".\n");
		serializeDB();
	}
	
	public void employeeAccount() {
		
	}
	
	public void deSerialize() {
		log.logDebug("deSerialize() method called");
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usernames.dat"))){
			
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
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountNumbers.dat"))){
			
			accountNumbers = (ArrayList<Integer>) ois.readObject();
			
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
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("balance.dat"))){
			
			balance = (ArrayList<Double>) ois.readObject();
			
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
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("isEmployee.dat"))){
			
			isEmployee = (ArrayList<Boolean>) ois.readObject();
			
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
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("isAdmin.dat"))){
			
			isAdmin = (ArrayList<Boolean>) ois.readObject();
			
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
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("approved.dat"))){
			
			approved = (ArrayList<Boolean>) ois.readObject();
			
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
		
	}
	
	public void serializeDB() {
		log.logDebug("serialize() method called");
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
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountNumbers.dat"))){
			
			oos.writeObject(accountNumbers);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("balance.dat"))){
			
			oos.writeObject(balance);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("isEmployee.dat"))){
			
			oos.writeObject(isEmployee);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("isAdmin.dat"))){
			
			oos.writeObject(isAdmin);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("approved.dat"))){
			
			oos.writeObject(approved);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
