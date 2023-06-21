package controllers;


//import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
//import java.net.URL;
import java.util.List;
//import java.util.ResourceBundle;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import service.UserService;
import service.UserServiceException;
import javafx.scene.Node;

public class Controller implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField usersTextField;
	
	@FXML
	private TextField accountTextField;
	
	@FXML
	private PasswordField enterPinField;
	
	@FXML
	private String userNameLabel;
	
	private Account currentLogged;

	private static Controller instance;
	
	List<Account> allAccounts;

    public Controller() {
        instance = this;
    }

    public List<Account> getAllAccounts() {
		return allAccounts;
	}

	public void setAllAccounts(List<Account> allAccounts) {
		this.allAccounts = allAccounts;
	}

	public static Controller getInstance() {
        return instance;
    }

    public String username() {
        return accountTextField.getText();
    }
	
	public void switchToScene1(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	public void switchToScene2(ActionEvent event) throws Exception {
		UserService userservice = new UserService();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");

		UserDao userDao = new UserDao(entityManagerFactory);
		String accountText = accountTextField.getText();
		String pinText = enterPinField.getText();
		
		try {
			Account loggedUser = userservice.findUser(accountText, pinText);
			
			allAccounts = userservice.getAllUsers();
			
			//Collections.sort(allAccounts, Comparator.comparing(Account::getBalance).reversed());
			//System.out.println(allAccounts);
	    	
			for(Account user : allAccounts) 
	        	System.out.println(user);
			
	        for(Account user : allAccounts) {
	        	//System.out.println(user);
	        	if(user.getAccountNumber().equals(loggedUser.getAccountNumber()) && user.getPin().equals(loggedUser.getPin())) {
	        		user.setBalance(user.getBalance()+10);
	        		userDao.update(user);
	        		currentLogged = user;
	        		break;
	        	}
	        }

			 if (userservice.findUser(accountTextField.getText(), enterPinField.getText())!= null){ 
			 root =FXMLLoader.load(getClass().getResource("/resources_view/AppMainScreen.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
			 scene = new Scene(root);
			 stage.setScene(scene);
			 stage.show(); 
			 }
		}catch (UserServiceException e) {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Login Failed");
	        alert.setHeaderText(null);
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
		}
	}
	
	public void switchToScene3(ActionEvent event) throws IOException {
		UserService userservice = new UserService();
		//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
		usersTextField.setVisible(true);
		allAccounts = userservice.getAllUsers();
		
		Collections.sort(allAccounts, Comparator.comparing(Account::getBalance).reversed());
    	
		StringBuilder usersText = new StringBuilder();
		
    	for(Account user : allAccounts) {
    		System.out.println(user);
    		usersText.append(user.toString()).append("\n");
    	}
    	
    	usersTextField.setText(usersText.toString());
	}
	
	public void registerButton(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("/resources_view/Register.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	

	public Account getCurrentLogged() {
		return currentLogged;
	}

	public void setCurrentLogged(Account currentLogged) {
		this.currentLogged = currentLogged;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		usersTextField.setVisible(false);
	}
}
