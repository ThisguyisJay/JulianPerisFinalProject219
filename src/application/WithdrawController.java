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

public class WithdrawController extends DashboardController{
	private Scene scene;
	private Parent root;
	private User user;

    @FXML
    private Button withdrawCancelBtn;
    
    @FXML
    private Label currentFundsLabel;

    @FXML
    private Button withdrawButton;

    @FXML
    private TextField withdrawTextfield;
    
    @FXML
    private Label errorMessageLabel;

    @FXML
    void withdraw(ActionEvent event) {
    	try {
    	errorMessageLabel.setText("");
    	double current = Double.parseDouble(currentFundsLabel.getText().substring(2));
    	double amountAsDouble = getAmount();
    	if(current >= amountAsDouble) {
    		double total = current - amountAsDouble;
    		currentFundsLabel.setText("$ " + Double.toString(total));
    		errorMessageLabel.setText("Withdrew successfully");
    		Date time = new Date();
        	String timeStamp = time.toString();
        	
        	Transaction withdrawal = new Transaction(user.getUsername(), "Withdrawal", 
        			"$ " + Double.toString(current), "$ " + Double.toString(amountAsDouble), 
        			"N/A", "$ " + Double.toString(total), timeStamp);
        	
        	
        	try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
                bw.write(withdrawal.getUsername());
                bw.newLine();
                bw.write(withdrawal.getType());
                bw.newLine();
                bw.write(withdrawal.getInitialBalance());
                bw.newLine();
                bw.write(withdrawal.getAmount());
                bw.newLine();
                bw.write(withdrawal.getUsernameRecieved());
                bw.newLine();
                bw.write(withdrawal.getFinalBalance());
                bw.newLine();
                bw.write(withdrawal.getTimeStamp());
                bw.newLine();
                
            }
            catch (IOException e){
                e.printStackTrace();
            }
    	}else {
    		errorMessageLabel.setText("Insufficient funds");
    	}
    	}catch(NumberFormatException ife){
    		errorMessageLabel.setText("INVALID CHARACTERS: \n Amount should contain numbers and one decimal"
    				+ " point only.");
    	}
   
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public void getUser(User user) {
    	this.user = user;
    }
    
    public void displayFunds(String currentFunds) {
    	currentFundsLabel.setText(currentFunds);
    }
    
    public String getTotal() {
    	return currentFundsLabel.getText();
    }
    
    double getAmount() {
    	double amount = Double.parseDouble(withdrawTextfield.getText());
    	return amount;
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        
		dashboardController.getUser(this.user);
		dashboardController.displayName(user);
		dashboardController.updateFunds(getTotal());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }

}
