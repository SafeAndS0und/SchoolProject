package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    	if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || password2Field.getText().isEmpty()) {
    		System.out.println("Wypelnij wszystkie pola");
    	} else {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", "root", "");
	    	stmt = con.createStatement();
	    	
	    	String query = "SELECT teacher.username, teacher.password FROM teacher";
	    	rs = stmt.executeQuery(query);
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
    		while(rs.next()) {
    			if(usernameField.getText().equals(rs.getObject("username"))) {
    				System.out.println("Username jest zajety");
    				break;
    			}
    			else if(!passwordField.getText().equals(password2Field.getText())) {
    				System.out.println("Hasla nie sa takie same");
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
    				System.out.println("Dodano nowego nauczyciela do bazy");
    				break;
    			}
    		}
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	}
    }

}
