package Server;

import java.sql.*;

public class Database {

    private Connection con;
    private PreparedStatement prepstmt;
    private ResultSet rs;

    /**
     * Laczy z baza danych po podaniu nazwy uzytkownika oraz hasla
     * @param user
     * @param password
     * @return
     */
    public Connection connect(String user, String password) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", user, password);
            System.out.println("Connected to databse");
            return con;

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    /**
     * Dodaje nauczyciela
     * @param name
     * @param password
     */
    
    public void addTeacher(String name, String password) {
    	String query = "INSTERT INTO teachers (username, password)" + "VALUES (?,?)";
    	
    	try {
    		prepstmt = con.prepareStatement(query);
    		prepstmt.setString(1, name);
    		prepstmt.setString(2, password);
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }

    /**
     * Tworzy zapytanie np.
     * select * from teacher
     */
    public ResultSet rs(String query) {
    	try {
    		rs = prepstmt.executeQuery(query);
    		return rs;
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	return rs;
    }

}
