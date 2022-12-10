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
	
    /** Sets choiceBox options to pre-determined lists
     * 
     */
    public void initializeChoiceBoxes() {
    	employmentStatusChoiceBox.setItems(employmentStatusList);
    	titleChoiceBox.setItems(genderList);
    }
    
    /** If all information is filled in, when user presses create account, all information is grabbed 
     * from textfields and a new instance of CreateUser is created and written to text file users. If anything 
     * left blank, nothing happens.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    private void createAccount(ActionEvent event) throws IOException {
    	CreateUser newUser = new CreateUser();
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
    		CreateUser user = new CreateUser(newUser.getTitle(), newUser.getFirstName(), newUser.getLastName(), 
    				newUser.getUsername(), newUser.getPin(), newUser.getPin2(), newUser.getdOfB(), 
    				newUser.getAddress(),newUser.getEmploymentStatus(), newUser.getChequingFunds(), 
    				newUser.getSavingFunds());
    		
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
	            bw.write(user.getChequingFunds());
	            bw.newLine();
	            bw.write(user.getSavingFunds());
	            bw.newLine();
	        }
	        catch (IOException e){
	            e.printStackTrace();
	        }
			createAccountErrorLabel.setText("Account created successfully");
    		
    	}catch(InvalidInputException iie) {
    		createAccountErrorLabel.setText(iie.getMessage());
    	}

    }

    /**When user presses switch to log in button, scene changes to log in scene.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();  
    }

   

}
