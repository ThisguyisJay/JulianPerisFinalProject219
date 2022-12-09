package application;

public class Transaction {
	private String username;
	private String account;
	private String type;
	private String initialBalance;
	private String finalBalance;
	private String amount;
	private String usernameRecieved;
	private String timeStamp;
	
	public Transaction() {
		this.username = "";
		this.type = "";
		this.initialBalance = "";
		this.amount = "";
		this.setUsernameRecieved("");
		this.finalBalance = "";
		this.timeStamp = null;
	}
	
	public Transaction(String username, String account, String type, String initialBalance, 
			String amount, String usernameRecieved,String finalBalance, String timeStamp) {
		this.username = username;
		this.setAccount(account);
		this.type = type;
		this.initialBalance = initialBalance;
		this.amount = amount;
		this.setUsernameRecieved(usernameRecieved);
		this.finalBalance = finalBalance;
		this.timeStamp = timeStamp;
	}
	 public String toString() {
	        return String.format("username: " + username + "\n"+  type + "\n" + initialBalance + "\n" +
	        		amount +"\n" + finalBalance +"\n" + timeStamp);
	     
	    }
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(String initialBalance) {
		this.initialBalance = initialBalance;
	}

	public String getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(String finalBalance) {
		this.finalBalance = finalBalance;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) throws InvalidInputException {
		boolean valid = amount.matches("^(\\$|)([1-9]\\d{0,2}(\\,\\d{3})*|([1-9]\\d*))(\\.\\d{2})?$");
		try {
			double amountAsDouble = Double.parseDouble(amount);
			if(amountAsDouble > 0) {
				if(valid) {
					this.amount = amount;
				}else {
					throw new InvalidInputException("Amount entered must be a dollar value. i.e (X.XX)");
				}
			}else {
				throw new InvalidInputException("Amount entered must be greater than 0.");
			}
				
		}catch (NumberFormatException nfe) {
			throw new InvalidInputException("Amount entered must not contain any letters & \n"
					+ "may contain one decimal.");
		}

	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUsernameRecieved() {
		return usernameRecieved;
	}

	public void setUsernameRecieved(String usernameRecieved) {
		this.usernameRecieved = usernameRecieved;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	
}
