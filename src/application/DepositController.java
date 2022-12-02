package application;

import java.io.IOException;

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
	private Stage stage;

    @FXML
    private Button confirmDepositButton;
    
    @FXML
    private Label currentFundsLabel;
    
    @FXML
    private TextField amountTextfield;
    
    @FXML
    private Button doneButton;
    
    
    public void setUsername(String username) {
    	String welcome = "Welcome to your dashboard, . Please select an option from the left.";
    	int noName = welcome.length();
    	int withName = username.length();
    	int nameLength = withName - noName;
    	this.username = username.substring(27, (27 + nameLength));
    	
    }
    
    double getAmount() {
    	double amount = Double.parseDouble(amountTextfield.getText());
    	return amount;
    }

    public void displayFunds(String currentFunds) {
    	currentFundsLabel.setText(currentFunds);
    }
    
    public String getTotal() {
    	return currentFundsLabel.getText();
    }
    

    @FXML
    void ConfirmDeposit(ActionEvent event) {
    	double current = Double.parseDouble(currentFundsLabel.getText());
    	double amountAsDouble = getAmount();
    	double total = current + amountAsDouble;
    	currentFundsLabel.setText(Double.toString(total));

    }
    
    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        
		dashboardController.displayName(this.username);
		dashboardController.updateFunds("$ " + getTotal());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }
   

}
