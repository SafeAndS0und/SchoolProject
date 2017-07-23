package server;

import java.sql.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Database {
	
	MainScreenController mainScreenController;
	LoginScreenController loginScreenController;

    private Connection con;
    private Statement stmt;
    private PreparedStatement prepstmt;
    private ResultSet rs;
    
    public int teacherID;
    boolean zalogowano = false;
    String loginInfo;
    

    /**
     * Laczy z baza danych po podaniu nazwy uzytkownika oraz hasla
     * @param user
     * @param password
     * @return
     */
    public Connection connect(String user, String password) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", user, password);
            return con;

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    /** 
     * Tworzy statement
     * @return
     */
    
    public Statement statement() {
    	try {
    		stmt = con.createStatement();
    		return stmt;
    		
    	}catch (Exception e) {
    		System.out.println(e);
    	}
    	return stmt;
    }
    
    /**
     * Tworzy zapytanie SQL po podaniu zapytania (??? xD)
     * @param query
     * @return
     */
    
    public ResultSet resultSet(String query) {
    	try {
    		rs = stmt.executeQuery(query);
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	return rs;
    }
	
	/**
	 * Sprawdza czy nauczyciel znajduje sie w DB po podaniu username oraz hasla
	 * Gdy username i haslo sa poprawne, nauczyciel loguje sie do programu
	 * @param username
	 * @param password
	 */
	
	public void signInTeacher(String username, String password) {
		zalogowano = false;
		//laczenie z SQL i tworzenie statement
		connect("root", "");
		statement();
		
		//zapytanie SQL
		String query = "SELECT teacher.username, teacher.password FROM teacher";
		resultSet(query);
		
		try {
			
			//petla sprawdzajaca czy istnieje podany username i haslo
			while(rs.next()) {
				if(username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
					loginInfo = "Zalogowano";
					zalogowano = true;
					getID(username, password);
					break;
					
				}
				else if(username.equals(rs.getString("username"))) {
					if(!password.equals(rs.getString("password"))) {
						loginInfo = "Niepoprawne haslo";
						break;
					}
				}
				else {
					loginInfo = "Niepoprawny username lub haslo";
				}
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void getID(String username, String password) {
		connect("root", "");
		statement();
		
		String query = "SELECT * FROM teacher WHERE username = \"" + username + "\" "
				+ "AND password = \"" + password + "\"";
        resultSet(query);
        try {
        	while(rs.next()) {
        		teacherID = rs.getInt("ID");
        		System.out.println("ID nauczyciela : " + teacherID);
//        		mainScreenController.teacherID = teacherID;
        		break;
        	}
        }catch(Exception e) {
        	System.out.println(e);
        }
		System.out.println(query);
	}

}
