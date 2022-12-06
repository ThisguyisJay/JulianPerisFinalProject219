package application;

import java.util.Date;

public class User {
	String title;
	String firstName;
	String lastName;
	String username;
	String pin;
	Date dOfB;
	String address;
	String employmentStatus;
	
	public User(){
		this.title = "";
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.pin = "";
		this.dOfB = new Date();
		this.address = "";
		this.employmentStatus = "";
	}
	public User(String title, String firstName, String lastName, String username, String pin, Date dOfB,
			String address, String employmentStatus) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.pin = pin;
		this.dOfB = dOfB;
		this.address = address;
		this.employmentStatus = employmentStatus;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.equals(null)) {
			this.title = null;
		}else {
			this.title = title;
		}
		
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Date getdOfB() {
		return dOfB;
	}

	public void setdOfB(Date dOfB) {
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

}
