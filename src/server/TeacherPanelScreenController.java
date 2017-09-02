package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import server.ConnectionWithStudent.Server;

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
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("createQuiz.fxml"));
    	Pane pane = null;
    	try {
    		pane = loader.load();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	CreateQuizController createQuizController = loader.getController();
    	createQuizController.setMainController(mainScreenController);
    	setScene(pane);
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
    public void options() {
        System.out.println("Options");
    }

    @FXML
    public void infoOfAccount() {
        System.out.println("Information about account");
    }
    
    @FXML
    public void signOut() {
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

    public void setScene(Pane pane) {
        window.getChildren().clear();
        window.getChildren().add(pane);
    }
}
