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
    private Button depositToSavingsBtn;

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
    public void setUsername(String username) {
    	this.username = user.getUsername();
    }
    
    private String getCurrentFunds() {
    	return user.getChequingFunds();
    }
    
    public void setInvis() {
    	monthsTextfield.setVisible(false);
    	calculateInterestBtn.setVisible(false);
    	monthsLabel.setVisible(false);
    }

    @FXML
    private void deposit(ActionEvent event) {
    	monthsLabel.setVisible(false);
    	monthsTextfield.setVisible(false);
    	calculateInterestBtn.setVisible(false);
    	try {
    		double amount = Double.parseDouble(initialSavingsTextfield.getText());
    		monthsLabel.setVisible(true);
    		monthsTextfield.setVisible(true);
    		calculateInterestBtn.setVisible(true);
    		String amountAsString = String.format("%.2f", amount);
        	initialSavingsLabel.setText("$ " + amountAsString);
        	messageLabel.setText("Deposit successful");
    	}catch(NumberFormatException nfe) {
    		messageLabel.setText("INVALID AMOUNT TO DEPOSIT: \n Enter amount as a dollar value (x.xx)");
    	}
    	
    }

    @FXML
    private void calculateInterest(ActionEvent event) {
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
    			totalInterestLabel.setText("You gained \n $ " + totalGainAsString + "\n over a period of 5 months.");
    		}
    		if (months > 5) {
    			arrowLabel.setVisible(true);
    			rate = 2.50;
    			months = months - 5;
    			double interestSubsequentMonths = interest5Months * (Math.pow((1+rate/100), (months*1)));
    			String interestSubsequentMonthsAsString = String.format("%.2f", interestSubsequentMonths);
    			totalGain = interestSubsequentMonths - current;
    			totalGainAsString = String.format("%.2f", totalGain);
    			months = months + 5;
    			String monthsAsString = String.format("%.0f", months);
    			totalInterestLabel.setText("You gained \n$ " + totalGainAsString + "\n over a period of " + 
    			monthsAsString +" months.");
    			
    			savingsAfterInterestLabel.setText("$ " + interestSubsequentMonthsAsString);
    			messageLabel.setText("Interest calculated Succesfully");
    		}
    	}catch(NumberFormatException nfe) {
    		messageLabel.setText("INVALID ENTRY: \n Enter amount of months as a whole number.");
    	}
    	

    }
    
    public String getTotal() {
    	return savingsAfterInterestLabel.getText();
    }

    @FXML
    private void depositToSavings(ActionEvent event) {
        	String savings = savingsAfterInterestLabel.getText().substring(2);
        	double savingsDouble = Double.parseDouble(savings);
        	double userFunds = Double.parseDouble(user.getChequingFunds());
        	double finalAmount = userFunds + savingsDouble;

        	String finalAmountAsString = Double.toString(finalAmount);
        	
        	Date time = new Date();
        	String timeStamp = time.toString().substring(0, 16) + " MST";
        	
        	Transaction deposit = new Transaction(user.getUsername(),"Savings", "Deposit", "$ " + getCurrentFunds(),
        			"$ " + savings, "N/A", "$ " + finalAmountAsString, timeStamp);
        	
        	try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
                bw.write(deposit.getUsername());
                bw.newLine();
                bw.write(deposit.getAccount());
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
    	double initial = Double.parseDouble(user.getChequingFunds());
    	double initialAndSavings = initial + Double.parseDouble(savingsAfterInterestLabel.getText().substring(2));
    	String initialAndSavingsAsString = String.format("%.2f",initialAndSavings);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        dashboardController.getUser(this.user);
        dashboardController.displayName(this.user);
        dashboardController.displayCurrentFunds(this.username);
		dashboardController.updateSavingsFunds("$ " + initialAndSavingsAsString);
		
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
