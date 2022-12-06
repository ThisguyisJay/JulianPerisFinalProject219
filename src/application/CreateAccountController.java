package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
	private Stage stage;
	private Scene scene;
	
	
	ObservableList<String> employmentStatusList = FXCollections.observableArrayList("Employed", "Self-employed",
			"Student", "Unemployed");
	ObservableList<String> genderList = FXCollections.observableArrayList("Mr.", "Mrs.", "Ms.");

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
    private Label createAccountErrorLabel;
    
    @FXML
    private TextArea addressTextArea;
    
    @FXML
    private DatePicker dobDatePicker;
    
    @FXML
    private ChoiceBox<String> titleChoiceBox;
    
    @FXML
    private ChoiceBox<String> employmentStatusChoiceBox;
	
  
    void initializeChoiceBoxes() {
    	employmentStatusChoiceBox.setItems(employmentStatusList);
    	titleChoiceBox.setItems(genderList);
    }
    
    @FXML
    void createAccount(ActionEvent event) throws IOException {
    	File f = new File("users.txt");
    	
    	if (firstNameTextfield.getText() != "" && lastNameTextfield.getText() != "" 
    			&& titleChoiceBox.getValue() != null && employmentStatusChoiceBox.getValue() != null &&
    				createUsernameTextfield.getText() !="" &&  createPinPasswordField.getText() !="") {
    		if (createPinPasswordField.getText().length() == 4) {
    			if (createPinPasswordField.getText().equals(reEnterPasswordField.getText())) {
    				Scanner read = new Scanner(f); 
    				int noOfLines=0; // count how many lines in the file
    				while(read.hasNextLine()){
    				      noOfLines++;
    				      read.nextLine();
    				}
    				read.close();
    				Scanner read2 = new Scanner(f);
    
    				//loop through every line in the file and check against the user name & password 
    				//(inputs saved in blocks of lines
    				for(int i=0; i < noOfLines; i++){
    				   if(read2.nextLine().equals(createUsernameTextfield.getText())) {
    					   createAccountErrorLabel.setText("Username is taken, please try a different one.");
    					   read2.close();
    					   return;
    				   }
    				}read2.close();
    				
        			try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
        	            bw.write(createUsernameTextfield.getText());
        	            bw.newLine();
        	            bw.write(createPinPasswordField.getText());
        	            bw.newLine();
        	            bw.write(firstNameTextfield.getText());
        	            bw.newLine();
        	            bw.write(lastNameTextfield.getText());
        	            bw.newLine();
        	            bw.write(titleChoiceBox.getValue());
        	            bw.newLine();
        	            bw.write(employmentStatusChoiceBox.getValue());
        	            bw.newLine();
        	            bw.write("0.0");
        	            bw.newLine();
        	            
        	        }
        	        catch (IOException e){
        	            e.printStackTrace();
        	        }
        			createAccountErrorLabel.setText("Account created successfully");
            		
            	}else {
            		createAccountErrorLabel.setText("ERROR: Pins do not match.");
    		}}else {
    			createAccountErrorLabel.setText("Pin must be 4 digits");
    		}
        	
    	}else {
    		createAccountErrorLabel.setText("ERROR: Mandatory field(s) left blank.");
    	}

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();  
    }

   

}
