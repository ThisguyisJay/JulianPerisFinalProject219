package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
    private PasswordField pinPasswordField;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Button logInButton;

    @FXML
    private TextField usernameTextfield;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Label welcomeLabel;
    
    
    public void cancelProgram(ActionEvent event) {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML 
    public void userLogIn(ActionEvent event) throws IOException {
    	boolean grantAccess = false;
    	File f = new File("users.txt");
    	String username = usernameTextfield.getText();
    	String pin = pinPasswordField.getText();
    	
    	if(usernameTextfield.getText().isBlank() == false || pinPasswordField.getText().isBlank() == false) {
    		Scanner read = new Scanner(f); 
			int noOfLines=0; // count how many lines in the file
			 
			while(read.hasNextLine()){
			      noOfLines++;
			      read.nextLine();
			}
			read.close();
			Scanner read2 = new Scanner(f);
			 

			//loop through every line in the file and check against the user name & password (inputs saved in blocks of lines
			for(int i=0; i < noOfLines; i++){
			   if(read2.nextLine().equals(username)){ // if the same user name
			      i++;
			      if(read2.nextLine().equals(pin)){ // check password
			         grantAccess=true; // if also same, change boolean to true
			         read2.close();
			         break; // and break the for-loop
			      }
			   }
			}read2.close();
			 if(grantAccess){
			    // let the user continue 
			    // and do other stuff, for example: move to next window ..etc
				
				 FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
				 root = loader.load();
				 DashboardController dashboardController = loader.getController();
				 
				 dashboardController.displayName(username);
				 
				 dashboardController.displayCurrentFunds(username);
				 
				 
				 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				 scene = new Scene(root);
				 stage.setScene(scene);
				 stage.show();
				 
			 }
			 else{
			     // return Alert message to notify the deny
				 loginErrorLabel.setText("log in unsuccessful");
			 }
    			
    	}else {
    		loginErrorLabel.setText("Mandatory field(s) left blank");
    	}
    	
    }
    

    
    
    @FXML
    public void getAccountInfo(ActionEvent enterGetAccountInfoEvent) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
    	root = loader.load();
    	CreateAccountController createAccountController = loader.getController();
    	createAccountController.initializeChoiceBoxes();
    	
    	stage = (Stage)((Node)enterGetAccountInfoEvent.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }



   

}
