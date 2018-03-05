package com.revature;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.ArrayList;
import java.util.List;

public class Credentials {
	private static List<String> usernames = new ArrayList<>();
	private static List<String> passwords = new ArrayList<>();
	
	public Credentials() {
		super();
	}
	
	public void register(String u, String p) {
		this.usernames.add(u);
		this.passwords.add(p);
	}
	
	public boolean validate(String u, String p) {
		for(int i = 0; i < this.usernames.size(); i++) {
			if(usernames.get(i).equals(u) && passwords.get(i).equals(p))
				return true;
		}
		
		return false;
	}

}
