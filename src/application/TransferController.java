package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TransferController extends DashboardController{
	private Scene scene;
	private Parent root;
	private User user;

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
    	this.username = username;
    }
    
    public void getUser(User user) {
    	this.user = user;
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
			}else {
				usernameMessageLabel.setText("No account found with specified username");
				usernameMessageLabel.setTextFill(Color.RED);
			}
		}

    }

    @FXML
    void confirmTransfer(ActionEvent event) throws IOException {
    	String amountAsString = amountTextfield.getText();
		boolean valid = amountAsString.matches("^(\\$|)([1-9]\\d{0,2}(\\,\\d{3})*|([1-9]\\d*))(\\.\\d{2})?$");
		
	if(valid) {
    	try {
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
    				sc.next();
    				sc.next();
    				double recieverFunds = Double.parseDouble(sc.next());
    				sc.close();
    				
    				double recieverFundsAfterTransfer = recieverFunds +  amount;
    				double senderFundsAfterTransfer = senderFunds - amount;
    				transferMessageLabel.setText("Transfer Successful");
    				   
    				currentFundsLabel.setText(Double.toString(senderFundsAfterTransfer));
    				updateFile("users.txt", transferUsername, (getUsernameLineNo(transferUsername)+9), 
    						Double.toString(recieverFundsAfterTransfer));
    				updateFile("users.txt", senderUsername, (getUsernameLineNo(senderUsername)+9),
    						Double.toString(senderFundsAfterTransfer));
    				
    				Date time = new Date();
    		    	String timeStamp = time.toString();
    		    	
    		    	Transaction transferSender = new Transaction(senderUsername, "Transfer", Double.toString(senderFunds),
    		    			Double.toString(amount), transferUsername, Double.toString(senderFundsAfterTransfer), timeStamp);
    		    	
    		    	Transaction transferReciever = new Transaction(transferUsername , "Transfer", Double.toString(recieverFunds),
    		    			Double.toString(amount), senderUsername, Double.toString(recieverFundsAfterTransfer), timeStamp);
    		    	
    		    	try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
    		            bw.write(transferSender.getUsername());
    		            bw.newLine();
    		            bw.write(transferSender.getType());
    		            bw.newLine();
    		            bw.write(transferSender.getInitialBalance());
    		            bw.newLine();
    		            bw.write(transferSender.getAmount());
    		            bw.newLine();
    		            bw.write(transferSender.getUsernameRecieved());
    		            bw.newLine();
    		            bw.write(transferSender.getFinalBalance());
    		            bw.newLine();
    		            bw.write(transferSender.getTimeStamp());
    		            bw.newLine();
    		            bw.write(transferReciever.getUsername());
    		            bw.newLine();
    		            bw.write(transferReciever.getType());
    		            bw.newLine();
    		            bw.write(transferReciever.getInitialBalance());
    		            bw.newLine();
    		            bw.write(transferReciever.getAmount());
    		            bw.newLine();
    		            bw.write(transferReciever.getUsernameRecieved());
    		            bw.newLine();
    		            bw.write(transferReciever.getFinalBalance());
    		            bw.newLine();
    		            bw.write(transferReciever.getTimeStamp());
    		            bw.newLine();
    		            
    		        }
    		        catch (IOException e){
    		            e.printStackTrace();
    		        }
    				break;
    			}
    			
    		}
    	}
    	}catch(NumberFormatException ife) {
    		transferMessageLabel.setText("INVALID CHARACTERS: \n Amount should contain numbers and one decimal "
    				+ "point only.");
    	}
	}else {
		transferMessageLabel.setText("INVALID INPUT: \n Amount should be entered as a dollar amount."
				+ "\n i.e (x.xx) or (12.34)");
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
        
		dashboardController.getUser(this.user);
		dashboardController.displayName(this.user);
		dashboardController.updateFunds("$ " + getTotal());
		
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }

}

