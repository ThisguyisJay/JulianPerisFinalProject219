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
import javafx.stage.Stage;

public class DashboardController{
	File users = new File("users.txt");
	private Scene scene;
	private Parent root;
	

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
    
    @FXML
    private Label currentFundsLabel;
    
    
    public void displayName(String username) throws IOException {
    	welcomeLabel.setText("Welcome to your dashboard, " + username + 
    			". Please select an option from the left.");
    }
    
    public void displayCurrentFunds(String username) throws IOException {
    	Scanner read = new Scanner(users); 
		int noOfLines=0; // count how many lines in the file
		 
		while(read.hasNextLine()){
		      noOfLines++;
		      read.nextLine();
		}
		read.close();

		Scanner sc = new Scanner(users);
		//loop through every line in the file and check against the user name & password (inputs saved in pairs of lines
		for(int i=0; i < noOfLines; i++){
		   if(sc.nextLine().equals(username)){
			   sc.next();
			   sc.next();
			   sc.next();
			   sc.next();
			   sc.next();
			   Double currentFunds = sc.nextDouble();
			   sc.close();
			
			   currentFundsLabel.setText("$ " + currentFunds.toString());
			   break;
			   
		   }
		}sc.close();
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
