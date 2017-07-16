package server;

import java.sql.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Database {
	
	MainScreenController mainScreenController;

    private Connection con;
    private Statement stmt;
    private PreparedStatement prepstmt;
    private ResultSet rs;
    
    boolean zalogowano = false;
    

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
     * Dodaje nauczyciela po podaniu nazwy uzytkownika oraz hasla
     * @param username
     * @param password
     */

	public void addTeacher(String username, String password, String password2) {
		connect("root", "");
		statement();
		
		String query = "SELECT teacher.username, teacher.password FROM teacher";
 		resultSet(query);
		
		try {
    		while(rs.next()) {
    			if(username.equals(rs.getObject("username"))) {
    				System.out.println("Username jest zajety");
    				break;
    			}
    			else if(!password.equals(password2)) {
    				System.out.println("Hasla nie sa takie same");
    				break;
    			}
    			else if(rs.isLast()) {
    				query = "INSERT INTO teacher (username, password)" + "VALUES (?,?)";
    				try{
    		            prepstmt = con.prepareStatement(query);
    		            prepstmt.setString(1,username);
    		            prepstmt.setString(2,password);
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
	
	/**
	 * Sprawdza czy nauczyciel znajduje sie w DB po podaniu username oraz hasla
	 * Gdy username i haslo sa poprawne, nauczyciel loguje sie do programu
	 * @param username
	 * @param password
	 */
	
	public void signInTeacher(String username, String password) {
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
					System.out.println("Zalogowano");
					zalogowano = true;
					break;
					
				}
				else {
					System.out.println("Niepoprawny username lub haslo");
					zalogowano = false;
				}
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
