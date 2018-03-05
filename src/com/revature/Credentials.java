package com.revature;

import static org.junit.jupiter.api.Assumptions.assumingThat;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Credentials {
	private static List<String> usernames = new ArrayList<>();
	private static List<String> passwords = new ArrayList<>();
	
	public Credentials() {
		super();
	}
	
	public void register() {
		Scanner reader = new Scanner(System.in);
		int validInput;
		String u, p;
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Please fill out the following fields:");
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
		
		System.out.println("\nSuccessfully Registered!\n");
	}
	
	public void validate() {
		String u, p;
		int validLogin = 0;
		Scanner reader = new Scanner(System.in);

		System.out.print("Username: ");
		u = reader.nextLine();
		
		System.out.print("Password: ");
		p = reader.nextLine();
		
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(u) && passwords.get(i).equals(p)) {
				System.out.println("Welcome " + u + "!");
				validLogin = 1;
			}
		}
		
		if(validLogin != 1)
			System.out.println("invalid login");
		
	}

}
