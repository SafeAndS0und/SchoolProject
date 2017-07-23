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
	
	public void initialization() {
	}
	
	@FXML
	//metoda rozpoczynajaca kartkowke
	public void startTheQuiz() {
		System.out.println("Start the Quiz");
	}
	
	//metoda otwierajaca okienko z opcja dodawania pytania
	@FXML
	public void addQuestion() {
		System.out.println("Add Question");
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("addQuestion.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AddQuestionController addQuestionController = loader.getController();
		addQuestionController.setMainScreenController(mainScreenController);
		setScene(pane);
		//INSERT INTO `questions` (`ID`, `question`, `answerA`, `answerB`, `answerC`, `answerD`, `correctAnswer`, `teacherID`, `category`) VALUES (NULL, 'test5', 'tak', 'nie', 'moze', 'nwm', 'A', '1', 'eee');
	}
	
	@FXML
	public void showQuestions() {
		System.out.println("Show questions");
	}
	
	@FXML
	public void options() {
		System.out.println("Options");
	}
	
	@FXML
	public void infoOfAccount() {
		System.out.println("Information about account");
	}
	
	//uruchamia server
	@FXML
	public void server() {
		server.ConnectionWithStudent.Server.serverStart();
	}
	
	public void setScene(Pane pane) {
    	window.getChildren().clear();
    	window.getChildren().add(pane);
    }
}
