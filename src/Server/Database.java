package Server;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Connection con;

    /**
     * Laczy z baza danych po podaniu nazwy uzytkownika oraz hasla
     * @param user
     * @param password
     * @return
     */
    private Connection connect(String user, String password) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_project", user, password);
            System.out.println("Connected to databse");
            return con;

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }


}
