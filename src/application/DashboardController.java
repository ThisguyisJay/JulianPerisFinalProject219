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
import javafx.stage.Stage;

public class DashboardController{
	File users = new File("users.txt");
	private Scene scene;
	private Parent root;
	private Stage stage;
	String username;
	private User user;
	

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
    private Button withdrawalButton;
    
    @FXML
    private Label currentFundsLabel;
    
    @FXML
    private Button savingsAccountBtn;
    
    @FXML
    private Label currentSavingsLabel;
    
    
    /** When user is taken to dashboard scene, title, firstname, and lastName will be displayed in label.
     * 
     * @param user
     * @throws IOException
     */
    public void displayName(User user) throws IOException {
    	welcomeLabel.setText("Welcome to your dashboard, " + user.getTitle() + " " + user.getFirstName() + 
    			" " + user.getLastName() + ". \nPlease select an option from the left.");
    }
    
    /**When user is taken to dashboard, instance of User used to log in is passed to this controller.
     * 
     * @param user
     */
    public void getUser(User user) {
    	this.user = user;
    }
    /**Using instance of user passed from log in page, returns instance of users username.
     * 
     * @return instance of users username.
     */
    public String getUsername() {
    	username = this.user.getUsername();
    	return username;
    }
  
    /** Using instance of user's username, reads textfile and counts number of lines until username matches.
     * 
     * @param username
     * @return line number in text file of user's username.
     * @throws FileNotFoundException
     */
    public int getUsernameLineNo(String username) throws FileNotFoundException {
    	this.username = username;
    	
    	Scanner read = new Scanner(users); 
		int noOfLines=0; // count how many lines in the file
		int userLineNo = 0;
		 
		while(read.hasNextLine()){
		      noOfLines++;
		      read.nextLine();
		}
		
		read.close();
		Scanner read2 = new Scanner(users);
		 
		for(int i=0; i < noOfLines; i++){
		   if(read2.nextLine().equals(this.username)){
		      userLineNo = i;
		   }
		}
		read2.close();
		return userLineNo;
		
    }
    /**Updates savings funds label to new amount.
     * 
     * @param funds
     * @throws IOException
     */
    public void updateSavingsFunds(String funds) throws IOException{
    	currentSavingsLabel.setText(funds);
    	updateFile("users.txt", getUsername(), (getUsernameLineNo(getUsername())+10), funds);
    }
    /**Updates chequing funds label to new amount.
     * 
     * @param funds
     * @throws IOException
     */
    public void updateChequingFunds(String funds) throws IOException {
    	currentFundsLabel.setText(funds);
    	updateFile("users.txt", getUsername(), (getUsernameLineNo(getUsername()) + 9) , funds);
    }
    /**Updates text file by copying each line until username is found, then copies only the lines up to whichever
     * needs to be deleted, and then writes new line instead, then continues copying.
     * 
     * @param filePath
     * @param username
     * @param deleteLine
     * @param newLine
     * @throws IOException
     */
    public void updateFile(String filePath, String username, int deleteLine, String newLine) 
    		throws IOException {
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
    /**
     * When user logs in, current funds are collected from textfile and displayed in labels.
     * @param username
     * @throws IOException
     */
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
				sc.next();
				sc.next();
				String currentChequingFunds = sc.next();
				String currentSavingsFunds = sc.next();
				
				sc.close();
				   
				currentFundsLabel.setText("$ " + currentChequingFunds);
				currentSavingsLabel.setText("$ " + currentSavingsFunds);
				break;
			}
		}
    }
    /**
     * Takes user to log out scene.
     * @param event
     * @throws IOException
     */
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
    /**
     * takes user to deposit scene, displaying current funds in chequing.
     * @param event
     * @throws IOException
     */
    public void depositMoney(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Deposit.fxml"));
		root = loader.load();
		
		DepositController depositController = loader.getController(); 
		depositController.displayFunds(currentFundsLabel.getText().substring(2));
		depositController.getUser(this.user);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    /**
     * takes user to withdraw scene displaying current funds in chequing.
     * @param event
     * @throws IOException
     */
    public void withdrawMoney(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Withdraw.fxml"));
		root = loader.load();
		
		WithdrawController withdrawController = loader.getController(); 
		withdrawController.displayFunds(currentFundsLabel.getText());
		withdrawController.setUsername(getUsername());
		withdrawController.getUser(this.user);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
    /**
     * Takes user to money transfer scene displaying current funds in chequing.
     * @param event
     * @throws IOException
     */
    public void moneyTransfer(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
		root = loader.load();
		
		TransferController transferController = loader.getController(); 
		transferController.displayFunds(currentFundsLabel.getText().substring(2));
		transferController.setUsername(getUsername());
		transferController.getUser(user);
		transferController.setInvisible();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    /**
     * takes user to view Bank Statement Scene. Displaying all transactions made.
     * @param event
     * @throws IOException
     */
    public void viewBankStatement(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("BankStatement.fxml"));
		root = loader.load();
		
		BankStatementController bankStatementController = loader.getController();
		bankStatementController.setUsername(getUsername());
		bankStatementController.getAllTransactions();
		bankStatementController.setPreScene(logoutButton.getScene());
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
    /**
     * Takes user to Savings Acccount scene.
     * @param event
     * @throws IOException
     */
    public void viewSavingsAccount(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SavingsAccount.fxml"));
		root = loader.load();
		
		SavingsAccountController savingController = loader.getController();
		savingController.setInvis();
		savingController.getUser(user);
		savingController.setUsername(getUsername());
		
		
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
}
