package application;
 
import java.util.ArrayList;

 
public class Account implements java.io.Serializable {
	
	private String firstName;
	private String lastName;
	private String pin;
	private String username;
	private String title;
	private String employmentStatus;
	private String address;
	private ArrayList<Account> accounts = new ArrayList<Account>();
//	private Integer accountType;
	 
	// for Create account
	Account(String title, String firstName, String lastName, String username, String pin){
		this.setTitle(title);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPin(pin);
	}
	Account(String username){
		this.setUsername(username);
	}
	private void setTitle(String title) {
		this.title = title;
	
	}
	
	// adds created account to arrayList accounts... doesn't store more than one right now
	public void addAcount(Account account) {
		this.accounts.add(account);
	//	System.out.println(accounts.get(0).firstName);
	}
	// returns array list accounts with Account objects
	public ArrayList<Account> getAccounts(){
		System.out.println(this.accounts);
		return this.accounts;
		
	}
	// deposit
//	updateAccount(id, amount){
		
//	}
//	// transfer
//	updateAccount(id,id,amount){
//		
//	}
	
	// checks if log in credentials exist in accounts ArrayList
	public void checkLogIn(String username, String pin) {
		String storedUserFirst = this.accounts.get(0).firstName;
		System.out.println(storedUserFirst);
		
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
	String getUsername() {
		return username;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	String getEmploymentStatus() {
		return employmentStatus;
	}
	void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	private String getAddress() {
		return address;
	}
	private void setAddress(String address) {
		this.address = address;
	}
  

}
