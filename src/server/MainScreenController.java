package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainScreenController {

    @FXML
    private StackPane mainScreen;
    
    public void initialize() {
    	loginScreen();
    }
    
    public void loginScreen() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LoginScreen.fxml"));
    	Pane pane = null;
    	try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	LoginScreenController loginScreenController = loader.getController();
    	loginScreenController.setMainController(this);
    	setScene(pane);
    	
    }
    
    public void setScene(Pane pane) {
    	mainScreen.getChildren().clear();
    	mainScreen.getChildren().add(pane);
    }

}
