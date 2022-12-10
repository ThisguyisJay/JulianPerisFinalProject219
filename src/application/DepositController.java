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

public class DepositController extends DashboardController{
	private Scene scene;
	private Parent root;
	private User user;

    @FXML
    private Button confirmDepositButton;
    
    @FXML
    private Label currentFundsLabel;
    
    @FXML
    private Label depositMessageLabel;
    
    @FXML
    private TextField amountTextfield;
    
    @FXML
    private Button doneButton;
    
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public void getUser(User user) {
    	this.user = user;
    }

    public void displayFunds(String currentFunds) {
    	currentFundsLabel.setText("$ " + currentFunds);
    }
    
    private String getTotal() {
    	return currentFundsLabel.getText();
    }
    

    @FXML
    private void ConfirmDeposit(ActionEvent event) {
    	depositMessageLabel.setText("");
    	String amount = amountTextfield.getText();
    	Date time = new Date();
		String timeStamp = time.toString().substring(0, 16) + " MST";
    	double current = Double.parseDouble(currentFundsLabel.getText().substring(2));
    	
    	Transaction deposit = new Transaction();

    	try {
			deposit.setAmount(amount);
			double amountAsDouble = Double.parseDouble(deposit.getAmount());
			double finalBalance = current + amountAsDouble;
			String amountAsString = String.format("%.2f", amountAsDouble);
			deposit.setAmount(amountAsString);
			deposit.setUsername(user.getUsername());
			deposit.setAccount("Chequing");
			deposit.setType("Deposit");
			deposit.setInitialBalance(currentFundsLabel.getText());
			deposit.setUsernameRecieved("N/A");
			deposit.setTimeStamp(timeStamp);
			String finalAsString = String.format("%.2f", finalBalance);
			deposit.setFinalBalance(finalAsString);
			currentFundsLabel.setText("$ " + finalAsString);
			depositMessageLabel.setText("Deposit Successful!");

			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
				bw.newLine();
    			bw.write(deposit.getUsername());
    			bw.newLine();
    			bw.write(deposit.getAccount());
    			bw.newLine();
    			bw.write(deposit.getType());
    			bw.newLine();
    			bw.write(deposit.getInitialBalance());
    			bw.newLine();
    			bw.write("$ " + deposit.getAmount());
    			bw.newLine();
    			bw.write(deposit.getUsernameRecieved());
    			bw.newLine();
    			bw.write("$ " + deposit.getFinalBalance());
    			bw.newLine();
    			bw.write(deposit.getTimeStamp());
    			bw.newLine();
            
    		}
    		catch (IOException e){
    			e.printStackTrace();
    		}
		} catch (InvalidInputException e) {
			depositMessageLabel.setText(e.getMessage());
		}
    	deposit.setUsername(user.getUsername());
    	deposit.setType("Deposit");
    	deposit.setInitialBalance(Double.toString(current));

    }
    
    @FXML
    private void returnToDashboard(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        dashboardController.getUser(this.user);
        dashboardController.displayName(this.user);
		dashboardController.updateChequingFunds(getTotal());
		dashboardController.displayCurrentFunds(this.user.getUsername());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }
   

}
