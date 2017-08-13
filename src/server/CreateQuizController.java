package server;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateQuizController {
	
	MainScreenController mainScreenController;
	
	public void setMainController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

    @FXML
    private TextField question;

    @FXML
    private TextField answerA;

    @FXML
    private TextField answerB;

    @FXML
    private TextField answerC;

    @FXML
    private TextField answerD;

    @FXML
    private Label numberOfCreatedQuestion;

    @FXML
    private TextField category;

    @FXML
    private CheckBox checkBoxA;

    @FXML
    private CheckBox checkBoxB;

    @FXML
    private CheckBox checkBoxC;

    @FXML
    private CheckBox checkBoxD;

    @FXML
    void addNextQuestion() {

    }

    @FXML
    void endCreation() {

    }

}
