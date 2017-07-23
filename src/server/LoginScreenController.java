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
	Main main = new Main();
	
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
    public TextArea infoArea;
    
    @FXML
    public Label info;
    
    public void initialize() {
    	info.setText("");
    }

    @FXML
    void login() {
    	
    	if(loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
    		info.setVisible(true);
    		info.setText("Wypelnij wszystkie pola");
    	}
    	else {
    		db.signInTeacher(loginField.getText(), passwordField.getText());
    		//Pobiera stringa z informacja o logwaniu
    		info.setText(db.loginInfo);
    		
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
				main.id = db.teacherID;
				mainScreenController.teacherID = db.teacherID;
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
