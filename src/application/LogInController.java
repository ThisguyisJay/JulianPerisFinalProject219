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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button signUpButton;
    
    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Button logInButton;

    @FXML
    private TextField usernameTextfield;
    
    @FXML
    private Button cancelButton;
    
    
    public void cancelProgram(ActionEvent event) {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    public void userLogIn(ActionEvent event) {
    	if(usernameTextfield.getText().isBlank() == false || passwordPasswordField.getText().isBlank() == false) {
    		loginErrorLabel.setText("pressed log in");
    	}else {
    		loginErrorLabel.setText("Mandatory field(s) left blank");
    	}
    	
    }

    
    
    @FXML
    public void getAccountInfo(ActionEvent enterGetAccountInfoEvent) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
    	stage = (Stage)((Node)enterGetAccountInfoEvent.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }



    @FXML
    void createAccount(ActionEvent enterCreateAccountEvent) throws IOException {
    	
    	root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
    	stage = (Stage)((Node)enterCreateAccountEvent.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();

    }


    @FXML
    void ff3b3b(ActionEvent event) {

    }

    @FXML
    void e85b5b(ActionEvent event) {

    }

   

}
