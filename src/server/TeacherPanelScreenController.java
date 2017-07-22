package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TeacherPanelScreenController {
	
	@FXML
    private AnchorPane window;
	
	MainScreenController mainScreenController;
	Database db = new Database();
	
	public void setMainController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}
	
	@FXML
	public void addQuestion() {
		System.out.println(mainScreenController.teacherID);
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("addQuestion.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setScene(pane);
		//INSERT INTO `questions` (`ID`, `question`, `answerA`, `answerB`, `answerC`, `answerD`, `correctAnswer`, `teacherID`, `category`) VALUES (NULL, 'test5', 'tak', 'nie', 'moze', 'nwm', 'A', '1', 'eee');
	}
	
	public void setScene(Pane pane) {
    	window.getChildren().clear();
    	window.getChildren().add(pane);
    }
}
