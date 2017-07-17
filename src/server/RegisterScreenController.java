package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegisterScreenController {
	
	MainScreenController mainScreenController;
	Database db;
	private Connection con;
    private Statement stmt;
    private PreparedStatement prepstmt;
    private ResultSet rs;
	
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
    public Label registerInfo;
    
    public void initialize() {
    	registerInfo.setText("");
    }

    @FXML
    void back() {
    	//Laduje pane
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
    	//Sprawdza czy pola sa puste
    	if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || password2Field.getText().isEmpty()) {
    		registerInfo.setText("Wypelnij wszystkie pola");
    	} else {
    		
    	//laczenie z baza i ladowania zapytania	
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", "root", "");
	    	stmt = con.createStatement();
	    	
	    	String query = "SELECT teacher.username, teacher.password FROM teacher";
	    	rs = stmt.executeQuery(query);
	    	
	    	//Dodaje nauczyciela gdy tabela 'teacher' jest pusta
			if(!rs.next() && passwordField.getText().equals(passwordField.getText())){
				query = "INSERT INTO teacher (username, password)" + "VALUES (?,?)";
				try{
					prepstmt = con.prepareStatement(query);
					prepstmt.setString(1,usernameField.getText());
					prepstmt.setString(2,passwordField.getText());
					prepstmt.execute();

				}catch (Exception e ){
					System.out.println(e);
				}
				System.out.println("Dodano nowego nauczyciela do bazy");
			}else{
				rs = stmt.executeQuery(query);
			}
			//petla sprawdzajaca czy nazwa uzytkownika jest zajeta i hasla identyczne
    		while(rs.next()) {
    			if(usernameField.getText().equals(rs.getString("username"))) {
    				registerInfo.setText("Username jest zajety");
    				break;
    			}
    			else if(!passwordField.getText().equals(password2Field.getText())) {
    				registerInfo.setText("Hasla nie sa takie same");
    				break;
    			}
    			else if(rs.isLast()) {
    				query = "INSERT INTO teacher (username, password)" + "VALUES (?,?)";
    				try{
    		            prepstmt = con.prepareStatement(query);
    		            prepstmt.setString(1,usernameField.getText());
    					prepstmt.setString(2,passwordField.getText());
    		            prepstmt.execute();

    		        }catch (Exception e ){
    		            System.out.println(e);
    		        }
    				registerInfo.setText("Pomyslnie dodano " + usernameField.getText() + " do bazy");
    				break;
    			}
    		}
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	}
    }

}
