package application;

import java.util.ArrayList;

public class User {
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private ArrayList<Account> accounts;
	
	public User(String firstName, String lastName, String username, String password, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		
		this.accounts = new ArrayList<Account>();
		
		System.out.println("new user " + firstName + "created");
		
	}
	

}
