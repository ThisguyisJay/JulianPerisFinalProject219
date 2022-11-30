package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashboardController extends LogInController{

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
    
    public void displayName(String username) {
    	welcomeLabel.setText("Welcome " + username);
    }

}
