package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button switchToLoginButton;

    @FXML
    private PasswordField reEnterPasswordField;

    @FXML
    private TextField createUsernameTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private PasswordField createPinPasswordField;

    @FXML
    private TextField firstNameTextfield;

    @FXML
    private DatePicker DOBDatePicker;
    
    @FXML
    private Label createAccountErrorLabel;
    
    @FXML
    private ChoiceBox<Integer> accountTypeChoiceBox;

    @FXML
    void createAccount(ActionEvent event) {
    	
    	if (firstNameTextfield.getText() != "" && lastNameTextfield.getText() != "" 
    			&& //accountTypeChoiceBox.getValue() != null &&
    				createUsernameTextfield.getText() !="" &&  createPinPasswordField.getText() !="") {
    		
    		if (createPinPasswordField.getText().equals(reEnterPasswordField.getText())) {
        		
        		Account user = new Account(firstNameTextfield.getText(), lastNameTextfield.getText(), 
        				createUsernameTextfield.getText(), //accountTypeChoiceBox.getValue(), 
        				createPinPasswordField.getText());
        		user.addAcount(user);
        		createAccountErrorLabel.setText("Account created successfully");
        		
        	}else {
        		createAccountErrorLabel.setText("ERROR: Pins do not match.");
        	}
    	}else {
    		createAccountErrorLabel.setText("ERROR: Mandatory field(s) left blank.");
    	}

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
    	    root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
    	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    scene = new Scene(root);
    	   	stage.setScene(scene);
    	   	stage.show();
    	    
    }

   

}
