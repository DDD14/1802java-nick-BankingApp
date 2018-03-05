package com.revature;

import java.util.Scanner;

public class BankingApp {

	public static void main(String[] args) {
		initialize();

	}
	
	public static void initialize() {
		Scanner reader = new Scanner(System.in);
		String username, password;
		Credentials creds = new Credentials();
		
		System.out.println("Welcome to the Bank!\n");
		System.out.println("Please register or enter your credentials");
		System.out.print("Username: ");
		username = reader.nextLine();
		
		System.out.print("Password: ");
		password = reader.nextLine();
		
		creds.register("David", "password");
		if(creds.validate(username, password))
			System.out.println("Welcome " + username + "!");
		else
			System.out.println("invalid login");
		
	}

}
