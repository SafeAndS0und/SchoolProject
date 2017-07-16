package server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginScreenController {
	
	MainScreenController mainScreenController;
	
	public void setMainController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

    @FXML
    private AnchorPane LoginScreenBackground;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
    public void initialize() {
    	
    }

    @FXML
    void login() {

    }

    @FXML
    void register() {

    }

}
