package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
     * Dodaje ucznia do bazy danych
     * @param name imie
     * @param lastName nazwisko
     * @param whatClass klasa
     */
    public void addStudent(String name, String lastName, String whatClass){

        String query = "INSERT INTO student (name, lastName, whatClass)" +
                "VALUES (?,?,?)";

        try{
            prepstmt = con.prepareStatement(query);
            prepstmt.setString(1,name);
            prepstmt.setString(2,lastName);
            prepstmt.setString(3,whatClass);
            prepstmt.execute();

        }catch (Exception e ){
            System.out.println(e);
        }
    }
}
