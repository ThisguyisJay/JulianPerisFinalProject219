package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.stage.Stage;

public class DashboardController{
	File users = new File("users.txt");
	private Scene scene;
	private Parent root;
	private Stage stage;
	String username;
	

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
    
//    private String currentFunds = currentFundsLabel.getText();
    
    
    public void displayName(String username) throws IOException {
    	welcomeLabel.setText("Welcome to your dashboard, " + username + 
    			". Please select an option from the left.");
    }
    
    public String getUsername() {
    	String welcome = "Welcome to your dashboard, . Please select an option from the left.";
    	int noName = welcome.length();
//    	System.out.println(noName);
    	int withName= welcomeLabel.getText().length();
//    	System.out.println(withName);
    	int nameLength = withName - noName;
    	String username = welcomeLabel.getText();
    	username.substring(27, (27 + nameLength));
    	return username;
    }
  
    public int getUsernameLineNo() throws FileNotFoundException {
    	String welcome = "Welcome to your dashboard, . Please select an option from the left.";
    	
    	int noName = welcome.length();
    	
    	int withName= welcomeLabel.getText().length();

    	int nameLength = withName - noName;
    	String username = welcomeLabel.getText();
    	this.username = username.substring(27, (27 + nameLength));
    	System.out.print(this.username);
    	
    	
    	Scanner read = new Scanner(users); 
		int noOfLines=0; // count how many lines in the file
		int userLineNo = 0;
		 
		while(read.hasNextLine()){
		      noOfLines++;
		      read.nextLine();
		}
		
		read.close();
		Scanner read2 = new Scanner(users);
		 

		//loop through every line in the file and check against the user name & password (inputs saved in blocks of lines
		for(int i=0; i < noOfLines; i++){
		   if(read2.nextLine().equals(this.username)){
		      userLineNo = i;
		   }
		}
		read2.close();
		return userLineNo;
		
    }
    public void updateFunds(String funds) throws IOException {
    	currentFundsLabel.setText(funds);
		
    	updateFile("users.txt",(getUsernameLineNo() + 7) , funds);
    }
    
    public void updateFile(String filePath, int deleteLine, String newLine) throws IOException {
    	String welcome = "Welcome to your dashboard, . Please select an option from the left.";
    	
    	int noName = welcome.length();
    	
    	int withName= welcomeLabel.getText().length();

    	int nameLength = withName - noName;
    	String username = welcomeLabel.getText();
    	this.username = username.substring(27, (27 + nameLength));
    	
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
    				pw.println(newLine.substring(2));
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
    
    public void displayCurrentFunds(String username) throws IOException {
		Scanner sc = new Scanner(users);
		while (sc.hasNext()) {
			if(sc.next().equals(username)) {
				sc.nextLine();
				sc.next();
				sc.next();
				sc.next();
				sc.next();
				sc.next();
				String currentFunds = sc.next();
				   
				sc.close();
				   
				currentFundsLabel.setText("$ " + currentFunds);
				break;
			}
		}
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
    
    public void depositMoney(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Deposit.fxml"));
		root = loader.load();
		
		DepositController depositController = loader.getController(); 
		depositController.displayFunds(currentFundsLabel.getText().substring(2));
		depositController.setUsername(getUsername());
		
		 
		 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    public void withdrawMoney(ActionEvent event) throws IOException{
    	
    }
    
}
