package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class AddQuestionController {

	Database db = new Database();
	MainScreenController mainScreenController = new MainScreenController();
	TeacherPanelScreenController tpsc = new TeacherPanelScreenController();
	Main main = new Main();
	
	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement prepstmt;
	private ResultSet rs;

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

	int teaID = mainScreenController.teacherID;
	int selectedCheckBoxs;
	String corrAnswer;
	
	public void initialization() {
	}

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
		System.out.println(selectedCheckBoxs);
	}

	@FXML
	void addQuestion() {
		checkCheckBoxs();
		if (questionField.getText().isEmpty() || answerA.getText().isEmpty() || answerB.getText().isEmpty()
				|| answerC.getText().isEmpty() || answerD.getText().isEmpty() || category.getText().isEmpty()) {
			System.out.println("Wypelnij wszystkie pola");
		} else if (selectedCheckBoxs == 0) {
			System.out.println("Wybierz odpowiedz");
		} else if (selectedCheckBoxs > 1) {
			System.out.println("Musi byc tylko jedna odpowiedz");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Dodano pytanie");
		}
	}

}
