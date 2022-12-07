package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
	private Stage stage;
	private Scene scene;
	
	
	private ObservableList<String> employmentStatusList = FXCollections.observableArrayList("Employed", "Self-employed",
			"Student", "Unemployed");
	private ObservableList<String> genderList = FXCollections.observableArrayList("Mr.", "Mrs.", "Ms.");

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
	
  
    public void initializeChoiceBoxes() {
    	employmentStatusChoiceBox.setItems(employmentStatusList);
    	titleChoiceBox.setItems(genderList);
    }
    
    @FXML
    private void createAccount(ActionEvent event) throws IOException {
    	
    	User newUser = new User();
    	newUser.setTitle(titleChoiceBox.getValue());
    	newUser.setFirstName(firstNameTextfield.getText());
    	newUser.setLastName(lastNameTextfield.getText());
    	newUser.setUsername(createUsernameTextfield.getText());
    	newUser.setPin(createPinPasswordField.getText());
    	newUser.setPin2(reEnterPasswordField.getText());
    	newUser.setdOfB(dobDatePicker.getValue());
    	newUser.setAddress(addressTextArea.getText());
    	newUser.setEmploymentStatus(employmentStatusChoiceBox.getValue());
    	
    	try {
    		User user = new User(newUser.getTitle(), newUser.getFirstName(), newUser.getLastName(), 
    				newUser.getUsername(), newUser.getPin(), newUser.getPin2(), newUser.getdOfB(), 
    				newUser.getAddress(),newUser.getEmploymentStatus(), newUser.getFunds());
    		
    		try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
	            bw.write(user.getUsername());
	            bw.newLine();
	            bw.write(user.getPin());
	            bw.newLine();
	            bw.write(user.getFirstName());
	            bw.newLine();
	            bw.write(user.getLastName());
	            bw.newLine();
	            bw.write(user.getTitle());
	            bw.newLine();
	            bw.write(user.getEmploymentStatus());
	            bw.newLine();
	            bw.write(user.getAddress());
	            bw.newLine();
	            bw.write(user.getdOfB().toString());
	            bw.newLine();
	            bw.write(user.getFunds());
	            bw.newLine();
	        }
	        catch (IOException e){
	            e.printStackTrace();
	        }
			createAccountErrorLabel.setText("Account created successfully");
    		
    	}catch(InvalidInputException iie) {
    		createAccountErrorLabel.setText(iie.getMessage());
    	}
    	
//    	if (!newUser.getUsername().equals("") && !newUser.getFirstName().equals("")&&
//    			!newUser.getTitle().equals("") && !newUser.getLastName().equals("")
//    			&& !newUser.getPin().equals("") && newUser.getdOfB() != LocalDate.of(1, 1, 1) && 
//    			!newUser.getAddress().equals("") && !newUser.getEmploymentStatus().equals("")) {
//    			if(createPinPasswordField.getText().length() == 4) {
//    					if(createPinPasswordField.getText().equals(reEnterPasswordField.getText())) {
//            		Scanner read = new Scanner(f); 
//    				int noOfLines=0; // count how many lines in the file
//    				while(read.hasNextLine()){
//    				      noOfLines++;
//    				      read.nextLine();
//    				}
//    				read.close();
//    				Scanner read2 = new Scanner(f);
//
//    				for(int i=0; i < noOfLines; i++){
//    				   if(read2.nextLine().equals(newUser.getUsername())) {
//    					   createAccountErrorLabel.setText("Username is taken, please try a different one.");
//    					   read2.close();
//    					   return;
//    				   }
//    				}read2.close();
//    				try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
//    					System.out.println("here");
//        	            bw.write(newUser.getUsername());
//        	            bw.newLine();
//        	            bw.write(newUser.getPin());
//        	            bw.newLine();
//        	            bw.write(newUser.getFirstName());
//        	            bw.newLine();
//        	            bw.write(newUser.getLastName());
//        	            bw.newLine();
//        	            bw.write(newUser.getTitle());
//        	            bw.newLine();
//        	            bw.write(newUser.getEmploymentStatus());
//        	            bw.newLine();
//        	            bw.write(newUser.getAddress());
//        	            bw.newLine();
//        	            bw.write(newUser.getdOfB().toString());
//        	            bw.newLine();
//        	            bw.write(newUser.getFunds());
//        	            bw.newLine();
//        	            
//        	        }
//        	        catch (IOException e){
//        	            e.printStackTrace();
//        	        }
//        			createAccountErrorLabel.setText("Account created successfully");
//            	}else {
//            		createAccountErrorLabel.setText("ERROR: Pins do not match");
//            	}
//            	
//				
//        	}else {
//        		createAccountErrorLabel.setText("ERROR: Pin must be 4 digits");
//        	}
//    	}else {
//    		createAccountErrorLabel.setText("ERROR: Mandatory fields left blank");
//    	}
    	
    	
    	
    	
    	
//    	if (firstNameTextfield.getText() != "" && lastNameTextfield.getText() != "" 
//    			&& titleChoiceBox.getValue() != null && employmentStatusChoiceBox.getValue() != null &&
//    				createUsernameTextfield.getText() !="" &&  createPinPasswordField.getText() !="") {
//    		if (createPinPasswordField.getText().length() == 4) {
//    			if (createPinPasswordField.getText().equals(reEnterPasswordField.getText())) {
//    				Scanner read = new Scanner(f); 
//    				int noOfLines=0; // count how many lines in the file
//    				while(read.hasNextLine()){
//    				      noOfLines++;
//    				      read.nextLine();
//    				}
//    				read.close();
//    				Scanner read2 = new Scanner(f);
//    
//    				//loop through every line in the file and check against the user name & password 
//    				//(inputs saved in blocks of lines
//    				for(int i=0; i < noOfLines; i++){
//    				   if(read2.nextLine().equals(createUsernameTextfield.getText())) {
//    					   createAccountErrorLabel.setText("Username is taken, please try a different one.");
//    					   read2.close();
//    					   return;
//    				   }
//    				}read2.close();
//    				
//        			try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
//        	            bw.write(createUsernameTextfield.getText());
//        	            bw.newLine();
//        	            bw.write(createPinPasswordField.getText());
//        	            bw.newLine();
//        	            bw.write(firstNameTextfield.getText());
//        	            bw.newLine();
//        	            bw.write(lastNameTextfield.getText());
//        	            bw.newLine();
//        	            bw.write(titleChoiceBox.getValue());
//        	            bw.newLine();
//        	            bw.write(employmentStatusChoiceBox.getValue());
//        	            bw.newLine();
//        	            bw.write("0.0");
//        	            bw.newLine();
//        	            
//        	        }
//        	        catch (IOException e){
//        	            e.printStackTrace();
//        	        }
//        			createAccountErrorLabel.setText("Account created successfully");
//            		
//            	}else {
//            		createAccountErrorLabel.setText("ERROR: Pins do not match.");
//    		}}else {
//    			createAccountErrorLabel.setText("Pin must be 4 digits");
//    		}
//        	
//    	}else {
//    		createAccountErrorLabel.setText("ERROR: Mandatory field(s) left blank.");
//    	}

    }

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();  
    }

   

}
