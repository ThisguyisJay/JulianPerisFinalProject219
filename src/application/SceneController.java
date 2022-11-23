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

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button signUpButton;
    
    @FXML
    private Button createAccountButton;

    @FXML
    private PasswordField password;

    @FXML
    private Label logInErrorLabel;

    @FXML
    private Button logInButton;

    @FXML
    private TextField username;

    
    
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
    void userLogIn(ActionEvent event) {

    }

    @FXML
    void ff3b3b(ActionEvent event) {

    }

    @FXML
    void e85b5b(ActionEvent event) {

    }

   

}
