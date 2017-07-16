package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RegisterScreenController {
	
	MainScreenController mainScreenController;
	Database db;
	
	public void setMainController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField password2Field;

    @FXML
    private ImageView cube1;

    @FXML
    private ImageView cube2;

    @FXML
    private ImageView cube3;

    @FXML
    void back() {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LoginScreen.fxml"));
    	Pane pane = null;
    	try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	LoginScreenController loginScreenController = loader.getController();
    	loginScreenController.setMainController(mainScreenController);
    	mainScreenController.setScene(pane);
    }

    @FXML
    void register() {
    	db.addTeacher(usernameField.getText(), passwordField.getText(), password2Field.getText());
    }

}
