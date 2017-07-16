package server.ConnectionWithStudent;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    server.Database db = new server.Database();
    private Connection con;
    private PreparedStatement prepstmt;
    private ResultSet rs;
    private ArrayList<Questions> questionsList = new ArrayList<>();


    public void connect(){
      con = db.connect("root","");
    }

    public void readQuestions(){

        String query = "SELECT * FROM questions";

    }




}
