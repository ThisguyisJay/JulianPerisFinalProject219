package application;
 
import java.util.ArrayList;
 
public class Account {
	private String firstName;
	private String lastName;
	private String pin;
	private ArrayList<Account> accounts = new ArrayList<Account>();
//	private Integer accountType;
	 
	Account(String firstName, String lastName, String username, String pin){
		this.setFirstName(firstName);
		this.setLastName(lastName);
//		this.setAccountType(accountType);
		this.setPin(pin);
	}
	public void addAcount(Account account) {
		accounts.add(account);
		getAccounts();
	//	System.out.println(accounts.get(0).firstName);
	}
	public void getAccounts() {
		System.out.println(accounts);
	}
	
	String getFirstName() {
		return firstName;
	}
	void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	String getLastName() {
		return lastName;
	}
	void setLastName(String lastName) {
		this.lastName = lastName;
	}
	String getPin() {
		return pin;
	}
	void setPin(String pin) {
		this.pin = pin;
	}
  

}
