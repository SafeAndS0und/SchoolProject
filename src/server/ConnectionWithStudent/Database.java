package server.ConnectionWithStudent;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    server.Database db = new server.Database();
    private Connection con;
    private PreparedStatement prepstmt;
    private ResultSet rs;
    public ArrayList<Questions> questionsList = new ArrayList<>();


    public void connect() {
        con = db.connect("root", "");
    }

    /**
     * Wczytuje pytanie z bazy danych o podanym ID oraz przekształca je na obiekt klasy Questions
     */
    public void transformQuestions(int questionID) {

        String query = "SELECT * FROM questions, teacher WHERE questions.teacherID=teacher.ID AND questions.ID=?";

        try {
            prepstmt = con.prepareStatement(query);
            prepstmt.setInt(1, questionID);
            rs = prepstmt.executeQuery();

            Questions q = new Questions();
            while (rs.next()) {
                String question = rs.getString("question");
                String aA = rs.getString("answerA");
                String aB = rs.getString("answerB");
                String aC = rs.getString("answerC");
                String aD = rs.getString("answerD");
                String correct = rs.getString("correctAnswer");
                String category = rs.getString("category");
                int teacherID = rs.getInt("teacherID");


                q.setQuestion(question);
                q.setAnswerA(aA);
                q.setAnswerB(aB);
                q.setAnswerC(aC);
                q.setAnswerD(aD);
                q.setCorrectAnswer(correct);
                q.setTeacherID(teacherID);
                q.setCategory(category);

                questionsList.add(q);


            }


        } catch (Exception e) {
            System.out.println(e);

        }

    }
    public int checkHowMany(){
        String query = "SELECT * FROM questions, teacher WHERE questions.teacherID=teacher.ID";
        int number = 0;
        try{
            prepstmt = con.prepareStatement(query);
            rs = prepstmt.executeQuery();

            while(rs.next()){
                number++;
            }
            return number;
        }catch(Exception e){
            e.printStackTrace();
            return number;
        }

    }



}
