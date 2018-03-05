package com.revature;

import java.util.Scanner;

public class BankingApp {

	public static void main(String[] args) {
		initialize();

	}
	
	public static void initialize() {
		int selection, selectionValid;
		Credentials creds = new Credentials();
		Scanner reader = new Scanner(System.in);
		do {
			System.out.println("Welcome to the Bank!\n");
			System.out.println("Please register or enter your credentials");
			System.out.println("\n1. Login\n2. Register");
			
			selection = reader.nextInt();
			
			do {
				switch(selection) {
				case 0: selectionValid = 1;
				        break;
				case 1: creds.validate();
				        selectionValid = 1;
				        break;
				case 2: creds.register();
				        System.out.println("Now please login\n");
				        selectionValid = 1;
				        break;
				default: System.out.println("invalid selection");
				         selectionValid = 0;
				}
			} while(selectionValid == 0);
		} while(selection != 0);
	}
}
