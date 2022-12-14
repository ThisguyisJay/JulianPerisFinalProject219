package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BankStatementController extends DashboardController{
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private TableView<Transaction> transactions;
	
	@FXML
	private TableColumn<Transaction, String> accountColumn;
	
	@FXML
	private TableColumn<Transaction, String> typeColumn;
	
	@FXML
	private TableColumn<Transaction, String> initialBalanceColumn;
	
	@FXML
	private TableColumn<Transaction, String> finalBalanceColumn;
	
	@FXML
	private TableColumn<Transaction, String> amountColumn;
	
	@FXML
	private TableColumn<Transaction, String> timeStampColumn;
	
	@FXML
	private TableColumn<Transaction, String> recieverColumn;

    @FXML
    private Button backBtn;
    
    private Scene preScene;
	private Stage stage;
	private ObservableList<Transaction> allTransactions = FXCollections.observableArrayList();
    

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }
    

    @FXML
    private void returnToDashboard(ActionEvent event) throws IOException {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show();
    }
    
    public void setUsername(String username) {
    	this.username = username;
    	usernameLabel.setText(this.username);
    }
    
    public ObservableList<Transaction> getTransactions(){
    	return allTransactions;
    }
    
    private void displayData(ObservableList<Transaction> everyTransaction) {
    	accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("account"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
    	initialBalanceColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("initialBalance"));
    	amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
    	recieverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("usernameRecieved"));
    	finalBalanceColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("finalBalance"));
    	timeStampColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("timeStamp"));
    	
    	transactions.setItems(everyTransaction);
    	
    }



	public void getAllTransactions() throws FileNotFoundException {
		Scanner input = new Scanner(new File("Transactions.txt"));
	    input.useDelimiter("\n");


	    Transaction[] transactionsArray = new Transaction[0];
	    while(input.hasNext()) {
	    	if(input.next().equals(this.username)) {
	    		String account = input.next();
	    		String type = input.next();
	    		String initialBalance = input.next();
	    		String amount = input.next();
	    		String usernameRecieved = input.next();
	    		String finalBalance = input.next();
	    		String timeStamp = input.next();
	    		
	    		Transaction newTransaction = new Transaction(this.username,account,type,initialBalance,amount,
	    				usernameRecieved, finalBalance,timeStamp);
	    		
	    		allTransactions.add(newTransaction);
	    		transactionsArray = addTransaction(transactionsArray,newTransaction);
	    		
	    	}
	    }displayData(allTransactions);   
	}

	private static Transaction[] addTransaction(Transaction[] transactionsArray, Transaction transactionToAdd) {
	    Transaction[] newTransaction = new Transaction[transactionsArray.length + 1];
	    System.arraycopy(transactionsArray, 0, newTransaction, 0, transactionsArray.length);
	    newTransaction[newTransaction.length - 1] = transactionToAdd;

	    return newTransaction;
	}


}

