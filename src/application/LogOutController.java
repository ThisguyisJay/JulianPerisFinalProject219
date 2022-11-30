package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogOutController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button logOutCancelButton;

    @FXML
    private Button signOutButton;
    
    private Scene preScene;
    
    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    

    @FXML
    void logOut(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));    	
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

}