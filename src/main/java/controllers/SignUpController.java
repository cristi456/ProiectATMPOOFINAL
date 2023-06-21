package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;
import service.UserService;

public class SignUpController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
    private TextField accountNo;
	
	@FXML
    private Text bienvenida;

    @FXML
    private TextField nameLabel;

    @FXML
    private PasswordField pinField;

	public void signUpButton(ActionEvent event) throws Exception {
		UserService userService = new UserService();
		Account userToBeInserted = new Account(accountNo.getText(), nameLabel.getText(), pinField.getText(), 0);
		userService.addUser(userToBeInserted);
		System.out.println("User registered successfuly!");
		bienvenida.setText("User registered successfuly!");
	}
	
	public void goBack(ActionEvent event) throws IOException{
		 root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}

}
