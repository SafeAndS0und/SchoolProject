package server;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddQuestionController implements Initializable {

	Database db = new Database();
	MainScreenController mainScreenController = new MainScreenController();
	TeacherPanelScreenController tpsc = new TeacherPanelScreenController();
	Main main = new Main();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		server.ConnectionWithStudent.Database db = new server.ConnectionWithStudent.Database();
		db.connect();
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	private Connection con;
	private Statement stmt;
	private PreparedStatement prepstmt;
	private ResultSet rs;
	
	@FXML
	private AnchorPane window;

	@FXML
	private TextField questionField;

	@FXML
	private TextField answerA;

	@FXML
	private TextField answerB;

	@FXML
	private TextField answerC;

	@FXML
	private TextField answerD;

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
	private Button backk;

	public Label wrong;

	int teaID = mainScreenController.teacherID;
	int selectedCheckBoxs;
	String corrAnswer;
	int howManyQuestions = 0;


	public void checkCheckBoxs() {
		selectedCheckBoxs = 0;

		if (checkBoxA.isSelected()) {
			selectedCheckBoxs++;
			corrAnswer = "A";
		}
		if (checkBoxB.isSelected()) {
			selectedCheckBoxs++;
			corrAnswer = "B";
		}
		if(checkBoxC.isSelected()) {
			selectedCheckBoxs++;
			corrAnswer = "C";
		}
		if(checkBoxD.isSelected()) {
			selectedCheckBoxs++;
			corrAnswer = "D";
		}
	}
	
	@FXML
	void back() {
		
		window.getChildren().clear();
	}

	@FXML
	void addQuestion() {
		checkCheckBoxs();
		if (questionField.getText().isEmpty() || answerA.getText().isEmpty() || answerB.getText().isEmpty()
				|| answerC.getText().isEmpty() || answerD.getText().isEmpty() || category.getText().isEmpty()) {
			wrong.setText("Wypełnij wszystkie pola!");
		} else if (selectedCheckBoxs == 0) {
			wrong.setText("Wybierz poprawną odpowiedź!");
		} else if (selectedCheckBoxs > 1) {
			wrong.setText("Zaznacz tylko jedna poprawną odpowiedź!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", "root", "");
				stmt = con.createStatement();
				
				String query = "INSERT INTO questions (ID, question, answerA, answerB, answerC," + 
						"answerD, correctAnswer, teacherID, category) VALUES (NULL, ?," + 
						"?, ?, ?, ?, ?, ?, ?)";
				prepstmt = con.prepareStatement(query);
				prepstmt.setString(1,questionField.getText());
				prepstmt.setString(2,answerA.getText());
				prepstmt.setString(3,answerB.getText());
				prepstmt.setString(4,answerC.getText());
				prepstmt.setString(5,answerD.getText());
				prepstmt.setString(6,corrAnswer);
				prepstmt.setInt(7,mainScreenController.teacherID);
				prepstmt.setString(8,category.getText());
				prepstmt.execute();
				howManyQuestions++;
				wrong.setText("Dodano pytanie " + howManyQuestions+"!");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public void startQuiz() {
		server.ConnectionWithStudent.Server.serverStart();
		wrong.setText("Postawiono serwer z dodanymi pytaniami, przejdź teraz do zakładki 'Wyniki'!");

//		try{
//			Parent root = FXMLLoader.load(getClass().getResource("Results.fxml"));
//			Stage stage = new Stage();
//			stage.setScene(new Scene(root,650,350));
//			stage.setTitle("Quiz");
//			stage.show();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}


}
