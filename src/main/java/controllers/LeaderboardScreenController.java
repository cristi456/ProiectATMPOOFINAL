package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import model.Account;

public class LeaderboardScreenController {
	List<Account> allAccounts;
	
	public void initialize(URL location, ResourceBundle resources) {
	    allAccounts = Controller.getInstance().getAllAccounts();
	}

	public void showAccounts(ActionEvent event) {
	    for(Account user : allAccounts) {
	        System.out.println(user);
	    }
	}

}
