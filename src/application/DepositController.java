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
    private TextField amountTextfield;
    
    @FXML
    private Button doneButton;
    
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public void getUser(User user) {
    	this.user = user;
    }
    
    double getAmount() {
    	double amount = Double.parseDouble(amountTextfield.getText());
    	return amount;
    }

    public void displayFunds(String currentFunds) {
    	currentFundsLabel.setText("$ " + currentFunds);
    }
    
    public String getTotal() {
    	return currentFundsLabel.getText();
    }
    

    @FXML
    void ConfirmDeposit(ActionEvent event) {
    	double current = Double.parseDouble(currentFundsLabel.getText().substring(2));
    	double amountAsDouble = getAmount();
    	double total = current + amountAsDouble;
    	currentFundsLabel.setText("$ " + Double.toString(total));
    	Date time = new Date();
    	String timeStamp = time.toString().substring(0, 16);
    	
    	Transaction deposit = new Transaction(user.getUsername(), "Deposit", "$ " + Double.toString(current),
    			"$ " + Double.toString(amountAsDouble), "N/A", "$ " + Double.toString(total),timeStamp);
    	
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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        dashboardController.getUser(this.user);
        dashboardController.displayName(this.user);
		dashboardController.updateFunds(getTotal());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }
   

}
