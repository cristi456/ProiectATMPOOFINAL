package controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Account;
import service.UserService;

public class UserController {
	@FXML
    public ListView<Account> userListView;

    private List<Account> allUsers;

    public List<Account> getAllUsers() {
        return allUsers;
    }

	@FXML
	void initialize() {
		UserService userService = new UserService();
		List<Account> allUsers = userService.getAllUsers();

		System.out.println(allUsers);
		userListView.setItems(FXCollections.observableArrayList(new ArrayList<Account>(allUsers)));

		// test login
		try {
			System.out.println(userService.findUser("test", "1234").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
