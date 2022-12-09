package application;

import java.io.FileNotFoundException;
import java.time.LocalDate;


public class User {
	private String title;
	private String firstName;
	private String lastName;
	private String username;
	private String pin;
	private String pin2;
	private LocalDate dOfB;
	private String address;
	private String employmentStatus;
	private String chequingFunds;
	private String savingFunds;
	
	public User(){
		this.title = "";
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.pin = "";
		this.dOfB = LocalDate.of(1,1,1);
		this.address = "";
		this.employmentStatus = "";
		this.chequingFunds = "0.00";
		this.savingFunds = "0.00";
	}
	public User(String title, String firstName, String lastName, String username, String pin, 
			LocalDate dOfB,String address, String employmentStatus, 
			String chequingFunds, String savingFunds) throws InvalidInputException{
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.pin = pin;
		this.dOfB = dOfB;
		this.employmentStatus = employmentStatus;
		this.chequingFunds = chequingFunds;
		this.savingFunds = savingFunds;

	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws FileNotFoundException {
		this.username = username;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String getPin2() {
		return pin2;
	}
	
	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public LocalDate getdOfB() {
		return dOfB;
	}

	public void setdOfB(LocalDate dOfB) {
		this.dOfB = dOfB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	String getChequingFunds() {
		return chequingFunds;
	}
	void setChequingFunds(String chequingFunds) {
		this.chequingFunds = chequingFunds;
	}
	String getSavingFunds() {
		return savingFunds;
	}
	void setSavingFunds(String savingFunds) {
		this.savingFunds = savingFunds;
	}

}
