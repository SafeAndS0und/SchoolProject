package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class LoginScreenController {
	
	MainScreenController mainScreenController;
	Database db = new Database();
	
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

    @FXML
    private ImageView cube1;

    @FXML
    private ImageView cube2;

    @FXML
    private TextArea infoArea;
    
    @FXML
    private Label info;

    @FXML
    void login() {
    	String loginText = loginField.getText();
    	String passwordText = passwordField.getText();
    	
    	if(loginText.isEmpty()) {
    		System.out.println("Wypelnij pole z loginem");
    	}
    	else if(passwordText.isEmpty()) {
    		System.out.println("Wypelnij pole z haslem");
    	}
    	else {
    		db.signInTeacher(loginField.getText(), passwordField.getText());
    		if(db.zalogowano == true) {
    			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TeacherPanelScreen.fxml"));
				Pane pane = null;
				try {
					pane = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				TeacherPanelScreenController teacherPanelScreenController = loader.getController();
				teacherPanelScreenController.setMainController(mainScreenController);
				mainScreenController.setScene(pane);
    		}
    	}
    }

    @FXML
    void register() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("RegisterScreen.fxml"));
    	Pane pane = null;
    	try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	RegisterScreenController registerScreenController = loader.getController();
    	registerScreenController.setMainController(mainScreenController);
    	mainScreenController.setScene(pane);
    }

}
