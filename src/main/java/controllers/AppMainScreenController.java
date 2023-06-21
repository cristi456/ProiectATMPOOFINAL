package controllers;

import application.Saluturi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;

public class AppMainScreenController  implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Account currentAccount;
	
	@FXML
	private TextField numerarLabel;
	
	@FXML
	private TextField numerarLabel2;
	
	@FXML
	private Text areyousureText;
	
	@FXML
	private TextField currentUserLabel;
	
	@FXML
	private TextField pinNouLabel;
	
	@FXML
	private Button dep;
	
	@FXML
	private Button retragereNumerarr;
	
	@FXML
	private Button yesButton;
	
	@FXML
	private Button noButton;
	
	@FXML
	private Button pinNouu;
	
	@FXML
	private AnchorPane nameLabel;
	
	@FXML
	private Label userNameLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentAccount = Controller.getInstance().getCurrentLogged();
		// TODO Auto-generated method stub
		getNumerarLabel().setVisible(false);
		dep.setVisible(false);
		yesButton.setVisible(false);
		noButton.setVisible(false);
		areyousureText.setVisible(false);
		numerarLabel2.setVisible(false);
		retragereNumerarr.setVisible(false);
		pinNouLabel.setVisible(false);
		pinNouu.setVisible(false);
		
		
		currentUserLabel.setText("Name: " + currentAccount.getName() + " -> Balance: " + currentAccount.getBalance());
		//currentUserLabel.setWrapText(true);
		currentUserLabel.setPrefHeight(50); // Adjust the height as needed


		System.out.println("test= " + currentAccount);
		userNameLabel.setText(selectRandomSalut() + ", " + currentAccount.getName());

	}
	
	
	public void logOutButton(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void menuItem1(ActionEvent event) {
		pinNouLabel.setVisible(false);
		pinNouu.setVisible(false);
		numerarLabel2.setVisible(false);
		retragereNumerarr.setVisible(false);
		
		getNumerarLabel().setVisible(true);					///DEPUNERE
		dep.setVisible(true);
	}
	
	public void menuItem2(ActionEvent event) {
		pinNouLabel.setVisible(false);
		pinNouu.setVisible(false);
		getNumerarLabel().setVisible(false);
		dep.setVisible(false);
		
		numerarLabel2.setVisible(true);					///RETRAGERE
		retragereNumerarr.setVisible(true);
	}
	
	
	public void menuItem3(ActionEvent event) {
		getNumerarLabel().setVisible(false);
		dep.setVisible(false);
		numerarLabel2.setVisible(false);
		retragereNumerarr.setVisible(false);
		
		pinNouLabel.setVisible(true);					///SCHIMBARE PIN
		pinNouu.setVisible(true);
	}
	
	
	@FXML
	public void depunereNumerar(ActionEvent event) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
		UserDao userDao = new UserDao(entityManagerFactory);
		
		String sum = getNumerarLabel().getText();
		float number = Float.parseFloat(sum);
		currentAccount.setBalance(currentAccount.getBalance() + number);

		currentUserLabel.setText("Name: " + currentAccount.getName() + " -> Balance: " + currentAccount.getBalance());
		userDao.update(currentAccount);
		getNumerarLabel().setVisible(false);
		dep.setVisible(false);
	}
	
	@FXML
	public void deleteCurrentUser(ActionEvent event) {
		areyousureText.setVisible(true);
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}
	
	@FXML
	public void noButton(ActionEvent event) {
		yesButton.setVisible(false);
		noButton.setVisible(false);
		areyousureText.setVisible(false);
	}
	
	@FXML
	public void yesButton(ActionEvent event) throws IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
		UserDao userDao = new UserDao(entityManagerFactory);
		userDao.remove(currentAccount, currentAccount.getIduser());
		
		yesButton.setVisible(false);
		noButton.setVisible(false);
		areyousureText.setVisible(false);
		Controller.getInstance().switchToScene1(event);
	}
	
	@FXML
	public void retragereNumerar(ActionEvent event) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
		UserDao userDao = new UserDao(entityManagerFactory);
		
		String sum = numerarLabel2.getText();
		float number = Float.parseFloat(sum);
		currentAccount.setBalance(currentAccount.getBalance() - number);

		currentUserLabel.setText("Name: " + currentAccount.getName() + " -> Balance: " + currentAccount.getBalance());
		userDao.update(currentAccount);
		numerarLabel2.setVisible(false);
		retragereNumerarr.setVisible(false);
	}
	
	@FXML
	public void pinNou(ActionEvent event) throws IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("App");
		UserDao userDao = new UserDao(entityManagerFactory);
		
		String newPin = pinNouLabel.getText();
		currentAccount.setPin(newPin);

		userDao.update(currentAccount);
		pinNouLabel.setVisible(false);
		pinNouu.setVisible(false);
		
		root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public String selectRandomSalut() {
        Saluturi[] saluturiArray = Saluturi.values();
        List<Saluturi> greet = new ArrayList<>();
        String regex = "^[A-Z].*";
        Pattern pattern = Pattern.compile(regex);

        for (Saluturi salut : saluturiArray) {
            String value = salut.getSalut();
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                greet.add(salut);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(greet.size());
        return greet.get(randomIndex).getSalut();
    }


	public TextField getNumerarLabel() {
		return numerarLabel;
	}


	public void setNumerarLabel(TextField numerarLabel) {
		this.numerarLabel = numerarLabel;
	}
}
