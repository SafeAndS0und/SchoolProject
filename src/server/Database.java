package server;

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
    
	public void addTeacher(String username, String password) {
		String query = "INSERT INTO teacher (username, password)" + "VALUES (?,?)";

		try {
    		prepstmt = con.prepareStatement(query);
    		prepstmt.setString(1, username);
    		prepstmt.setString(2, password);
    		prepstmt.execute();
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }
	
	public void checkTeacher(String username, String password) {
		
	}

}
