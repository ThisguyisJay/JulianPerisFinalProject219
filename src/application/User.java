package application;


import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;


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
	private String funds;
	
	public User(){
		this.title = "";
		this.firstName = "";
		this.lastName = "";
		this.username = "";
		this.pin = "";
		this.dOfB = LocalDate.of(1,1,1);
		this.address = "";
		this.employmentStatus = "";
		this.funds = "0.0";
	}
	public User(String title, String firstName, String lastName, String username, String pin, 
			String pin2, LocalDate dOfB,String address, String employmentStatus, 
			String funds) throws InvalidInputException{
		if (title == "error" || firstName.equals("empty")||lastName.equals("empty")||username.equals("empty")||
				pin.equals("empty")||pin2.equals("empty")||dOfB == LocalDate.of(1, 1, 1)||address.equals("empty")
				|| employmentStatus.equals("error")){
			throw new InvalidInputException("Mandatory field(s) left blank");
		}
		if(title == "error") {
			throw new InvalidInputException("Title choicebox left blank");
		}else {
			this.title = title;
		}
		
		if(!firstName.equals("empty")) {
			if(!firstName.equals("error")) {
				this.firstName = firstName;
			}else {
				throw new InvalidInputException("First name: Alphabetic characters only");
			}
		}else {
			throw new InvalidInputException("First name field left blank");
		}
		
		if(!lastName.equals("empty")) {
			if(!lastName.equals("error")) {
				this.lastName = lastName;
			}else {
				throw new InvalidInputException("Last name: Alphabetic characters only");
			}
		}else {
			throw new InvalidInputException("Last name field left blank");
		}
		
		if(!username.equals("empty")) {
			if(!username.equals("taken")) {
				if(username.length() <= 10) {
					this.username = username;
				}else {
					throw new InvalidInputException("Username cannot be more than 10 characters");
				}
			}else {
				throw new InvalidInputException("Username taken, please try another");
			}
			
		}else {
			throw new InvalidInputException("Username field left blank");
		}
		
		if(!pin.equals("empty")) {
			if(!pin.equals("error")) {
				if (!pin.equals("not4")) {
					if(pin.equals(pin2)) {
						this.pin = pin;
					}else {
						System.out.println(pin);
						System.out.println(pin2);
						throw new InvalidInputException("Pins do not match");
					}
				}else {
					throw new InvalidInputException("Pin must be 4 numbers");
				}
			}else {
				throw new InvalidInputException("Pin can be numeric only");
			}
		}else {
			throw new InvalidInputException("Pin field left blank");
		}
		
		if(dOfB != LocalDate.of(1,1,1)) {
			this.dOfB =dOfB;
		}else {
			throw new InvalidInputException("Date of birth not selected");
		}
		
		if (!address.equals("empty")) {
			this.address = address;
		}else {
			throw new InvalidInputException("Address field left blank");
		}
		
		if(!employmentStatus.equals("error")) {
			this.employmentStatus = employmentStatus;
		}else {
			throw new InvalidInputException("Employment Status not selected");
		}
		this.funds = funds;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null) {
			this.title = "error";
		}else {
			this.title = title;
		}
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.equals("")) {
			this.firstName = "empty";
		}
		Pattern p = Pattern.compile("[^a-zA-Z]");
		boolean hasSpecialChar = p.matcher(firstName).find();
		if(hasSpecialChar) {
			this.firstName = "error";
		}else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.equals("")) {
			this.lastName = "empty";
		}
		Pattern p = Pattern.compile("[^a-zA-Z]");
		boolean hasSpecialChar = p.matcher(lastName).find();
		if (hasSpecialChar) {
			this.lastName = "error";
		}else {
			this.lastName = lastName;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Scanner read = new Scanner("users.txt"); 
		int noOfLines=0; // count how many lines in the file
		while(read.hasNextLine()){
		      noOfLines++;
		      read.nextLine();
		}
		read.close();
		Scanner read2 = new Scanner("users.txt");

		for(int i=0; i < noOfLines; i++){
		   if(read2.nextLine().equals(username)) {
			   this.username = "taken";
		   }
		}read2.close();

		if(username.equals("")) {
			this.username = "empty";
		}
		if(username.length() <= 10 && !username.equals("empty")) {
			this.username = username;
		}else {
			this.username = "error";
		}	
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		if(pin.equals("")) {
			this.pin = "empty";
		}
		if (pin.matches("[0-9]+")) {
			if(pin.length() == 4) {
				this.pin = pin;	
			}else {
				this.pin = "not4";
			}
			
		}else {
			this.pin = "error";
		}

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
		if (dOfB == null) {
			this.dOfB = LocalDate.of(1, 1, 1);
		}
		this.dOfB = dOfB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address.equals("")) {
			this.address = "empty";
		}else {
			this.address = address;
		}
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		if(employmentStatus == null) {
			this.employmentStatus = "error";
		}else {
		this.employmentStatus = employmentStatus;
		}
	}
	String getFunds() {
		return funds;
	}
	void setFunds(String funds) {
		this.funds = funds;
	}

}
