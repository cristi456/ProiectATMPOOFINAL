package application;



import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import tests.TestRun;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;


public class Main extends Application {
    

	@Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("/resources_view/LoginScreen.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            Image mainIcon = new Image("/files/mainIcon.png");
            primaryStage.getIcons().add(mainIcon);
            primaryStage.setTitle("ATM BT");
			
		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
    	TestRun.main(args);
        launch(args);
    }
    
}