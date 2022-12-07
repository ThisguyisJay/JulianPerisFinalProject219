package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SavingsAccountController extends DashboardController{
	private Scene scene;
	private Parent root;
	
    @FXML
    private Label initialSavingsLabel;

    @FXML
    private Button depositToChequingBtn;

    @FXML
    private Label savingsAfterInterestLabel;

    @FXML
    private TextField monthsTextfield;

    @FXML
    private Button depositBtn;

    @FXML
    private TextField initialSavingsTextfield;

    @FXML
    private Button calculateInterestBtn;

    @FXML
    private Label arrowLabel;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private Label totalInterestLabel;
    
    @FXML
    private Label monthsLabel;
    
    private User user;
    
    public void getUser(User user) {
    	this.user = user;
    }
    public void setUsername() {
    	this.username = user.getUsername();
    }
    
    public String getCurrentFunds() {
    	return user.getFunds();
    }
    
    public void setInvis() {
    	monthsTextfield.setVisible(false);
    	calculateInterestBtn.setVisible(false);
    	monthsLabel.setVisible(false);
    }

    @FXML
    void deposit(ActionEvent event) {
    	try {
    		monthsLabel.setVisible(true);
    		monthsTextfield.setVisible(true);
    		calculateInterestBtn.setVisible(true);
    		double amount = Double.parseDouble(initialSavingsTextfield.getText());
    		String amountAsString = String.format("%.2f", amount);
        	initialSavingsLabel.setText("$ " + amountAsString);
        	messageLabel.setText("Deposit successful");
    	}catch(NumberFormatException nfe) {
    		messageLabel.setText("INVALID AMOUNT TO DEPOSIT: \n Enter amount as a dollar value (x.xx)");
    	}
    	
    }

    @FXML
    void calculateInterest(ActionEvent event) {
    	try {
    		double months = Double.parseDouble(monthsTextfield.getText());
    		double current = Double.parseDouble(initialSavingsLabel.getText().substring(2));
    		double rate = 5.00;
    		double interest5Months = current * (Math.pow((1 + rate/100), (5*1)));
    		double totalGain = interest5Months - current;
    		String totalGainAsString = String.format("%.2f", totalGain);
    	
    		String interest5MonthsString = String.format("%.2f", interest5Months);
    		if (months == 5) {
    			arrowLabel.setVisible(true);
    			savingsAfterInterestLabel.setText("$ " + interest5MonthsString);
    			messageLabel.setText("Interest calculated successfully");
    			totalInterestLabel.setText("You gained $ " + totalGainAsString + " over a period of 5 months");
    		}
    		if (months > 5) {
    			arrowLabel.setVisible(true);
    			rate = 2.50;
    			months = months - 5;
    			double interestSubsequentMonths = interest5Months * (Math.pow((1+rate/100), (months*1)));
    			String interestSubsequentMonthsAsString = String.format("%.2f", interestSubsequentMonths);
    			totalGain = interestSubsequentMonths - current;
    			totalGainAsString = String.format("%.2f", totalGain);
    			totalInterestLabel.setText("You gained $ " + totalGainAsString + " over a period of " + 
    			Double.toString(months + 5)+" months");
    			
    			savingsAfterInterestLabel.setText("$ " + interestSubsequentMonthsAsString);
    			messageLabel.setText("Interest calculated Succesfully");
    		}
    	}catch(NumberFormatException nfe) {
    		messageLabel.setText("INVALID AMOUNT TO DEPOSIT: \n Enter amount as a dollar value (x.xx)");
    	}
    	

    }
    
    public String getTotal() {
    	return savingsAfterInterestLabel.getText();
    }

    @FXML
    void depositToChequing(ActionEvent event) {
        	String savings = savingsAfterInterestLabel.getText().substring(2);
        	double savingsDouble = Double.parseDouble(savings);
        	System.out.println(savings);
        	double userFunds = Double.parseDouble(user.getFunds());
        	double finalAmount = userFunds + savingsDouble;
        	System.out.println(userFunds);

        	String finalAmountAsString = Double.toString(finalAmount);
        	
        	Date time = new Date();
        	String timeStamp = time.toString().substring(0, 16) + " MST";
        	
        	Transaction deposit = new Transaction(user.getUsername(), "Deposit", "$ " + getCurrentFunds(),
        			"$ " + savings, "Savings Acct", "$ " + finalAmountAsString, timeStamp);
        	
        	try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
                bw.write(deposit.getUsername());
                bw.newLine();
                bw.write(deposit.getType());
                bw.newLine();
                bw.write(deposit.getInitialBalance());
                bw.newLine();
                bw.write(deposit.getAmount());
                bw.newLine();
                bw.write(deposit.getUsernameRecieved());
                bw.newLine();
                bw.write(deposit.getFinalBalance());
                bw.newLine();
                bw.write(deposit.getTimeStamp());
                bw.newLine();
                
            }
            catch (IOException e){
                e.printStackTrace();
            }

    }
    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
    	try {
    	double initial = Double.parseDouble(user.getFunds());
    	double initialAndSavings = initial + Double.parseDouble(savingsAfterInterestLabel.getText().substring(2));
    	System.out.println(initialAndSavings);
    	String initialAndSavingsAsString = String.format("%.2f",initialAndSavings);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        dashboardController.getUser(this.user);
        dashboardController.displayName(this.user);
		dashboardController.updateFunds("$ " + initialAndSavingsAsString);
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();
    	}catch(StringIndexOutOfBoundsException e) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
    		root = loader.load();
    		DashboardController dashboardController = loader.getController(); 
            dashboardController.getUser(this.user);
            dashboardController.displayName(this.user);
            dashboardController.displayCurrentFunds("$ " + getCurrentFunds());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        	stage.show();
    	}

    }

}
