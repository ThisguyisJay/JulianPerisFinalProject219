package application;



public class Transaction {
	private String username;
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
		this.usernameRecieved = "";
		this.finalBalance = "";
		this.timeStamp = null;
	}
	
	public Transaction(String username, String type, String initialBalance, String amount, String usernameRecieved,
			String finalBalance, String timeStamp){ 
		
		this.username = username;
		this.type = type;
		this.initialBalance = initialBalance;
		this.amount = amount;
		this.usernameRecieved = usernameRecieved;
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

	public void setAmount(String amount) {
		this.amount = amount;
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

}
