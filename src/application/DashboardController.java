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
import javafx.stage.Stage;

public class DashboardController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String username;

    @FXML
    private Button historyButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button transferButton;
    
    public void displayName(String username) throws IOException {
    	welcomeLabel.setText("Welcome to your dashboard,  " + username + 
    			". Please select an option from the left.");
    	
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
//		 root = loader.load();
//    	CreateAccountController createAccountController = loader.getController();
//    	welcomeLabel.setText("welcome" + createAccountController.getFirstName());
    }
    String getUsername(String username) {
    	this.username = username;
    	return username;
    }
    
    public void logOut(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("LogOut.fxml"));
    	root = loader.load();
    	
    	LogOutController controller = loader.getController();
        controller.setPreScene(logoutButton.getScene());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }
    
}
