package Server;

import java.sql.*;

public class Database {

    private Connection con;
    private Statement stmt;
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
     * Tworzy statement (nwm co to xd)
     * @return
     */
    public Statement statement() {
    	try {
    		stmt = con.createStatement();
    		return stmt;
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	return stmt;
    }

    /**
     * Tworzy zapytanie np.
     * select * from teacher
     */
    public ResultSet rs(String query) {
    	try {
    		rs = stmt.executeQuery(query);
    		return rs;
    		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	return rs;
    }

}
