package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransferController extends DashboardController{
	private Scene scene;
	private Parent root;

    @FXML
    private Button searchUsernameBtn;

    @FXML
    private Label usernameMessageLabel;

    @FXML
    private TextField amountTextfield;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField transferUsernameTextfield;

    @FXML
    private Label transferMessageLabel;

    @FXML
    private Button doneBtn;
    
    @FXML
    private Label invisLabel;

    @FXML
    private Label currentFundsLabel;
    
    public void setUsername(String username) {
    	String welcome = "Welcome to your dashboard, . Please select an option from the left.";
    	int noName = welcome.length();
    	int withName = username.length();
    	int nameLength = withName - noName;
    	this.username = username.substring(27, (27 + nameLength));
    }
    
    public void setInvisible() {
    	amountTextfield.setVisible(false);
    	confirmBtn.setVisible(false);
    	invisLabel.setVisible(false);
    }
    
    public String getTotal() {
    	return currentFundsLabel.getText();
    }
    
    public void displayFunds(String currentFunds) {
    	currentFundsLabel.setText(currentFunds);
    }

    @FXML
    void searchForUsername(ActionEvent event) throws FileNotFoundException {
    	String transferUsername = transferUsernameTextfield.getText();
    	Scanner sc = new Scanner(users);
		while (sc.hasNext()) {
			if(sc.next().equals(transferUsername)) {
				usernameMessageLabel.setText("Account found");
				amountTextfield.setVisible(true);
				confirmBtn.setVisible(true);
				invisLabel.setVisible(true);
			   
				sc.close();
				break;
			}
		}

    }

    @FXML
    void confirmTransfer(ActionEvent event) throws IOException {
    	String senderUsername = this.username;
    	double amount = Double.parseDouble(amountTextfield.getText());
    	double senderFunds = Double.parseDouble(currentFundsLabel.getText());
    	if(amount > senderFunds) {
    		transferMessageLabel.setText("Insufficient Funds");
    	}else {
    		String transferUsername = transferUsernameTextfield.getText();
        	Scanner sc = new Scanner(users);
    		while (sc.hasNext()) {
    			if(sc.next().equals(transferUsername)) {
    				sc.nextLine();
    				sc.next();
    				sc.next();
    				sc.next();
    				sc.next();
    				sc.next();
    				double recieverFunds = Double.parseDouble(sc.next());
//    				System.out.println(recieverFunds);
    				sc.close();
    				
    				recieverFunds += amount;
//    				System.out.println(recieverFunds);
    				senderFunds -= amount;
    				transferMessageLabel.setText("Transfer Successful");
//    				System.out.println(Double.toString(recieverFunds));
    				   
    				currentFundsLabel.setText(Double.toString(senderFunds));
    				updateFile("users.txt", transferUsername, (getUsernameLineNo(transferUsername)+7), 
    						Double.toString(recieverFunds));
    				updateFile("users.txt", senderUsername, (getUsernameLineNo(senderUsername)+7),
    						Double.toString(senderFunds));
    				break;
    			}
    		}
    	}
    	
    	

    }
    
public void updateFile(String filePath, String username, int deleteLine, String newLine) throws IOException {
    	String tempFile = "temp.txt";
    	File oldFile = new File(filePath);
    	File newFile = new File(tempFile);
    	
    	int line = 0;
    	String currentLine;
    	
    	try {
    		FileWriter fw = new FileWriter(tempFile, true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		PrintWriter pw = new PrintWriter(bw);
    		
    		FileReader fr = new FileReader(users);
    		BufferedReader br = new BufferedReader(fr);
    		
    		
    		while((currentLine = br.readLine()) != null) {
    			line++;
    			if(deleteLine != line) {
    				pw.println(currentLine);
    			}else {
    				pw.println(newLine);
    			}	
    		}
    		pw.flush();
    		pw.close();
    		fr.close();
    		br.close();
    		bw.close();
    		fw.close();
    		
    		oldFile.delete();
    		File users = new File(filePath);
    		newFile.renameTo(users);
    	}catch(Exception e) {
    		
    	}
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
		root = loader.load();
		DashboardController dashboardController = loader.getController(); 
        
		dashboardController.displayName(this.username);
		dashboardController.updateFunds("$ " + getTotal());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }

}

