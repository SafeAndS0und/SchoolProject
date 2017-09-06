package server.ConnectionWithStudent;

import java.util.ArrayList;


public class Results {


    public int howManyStudents = 0;
    private static Results results;

    private Results() {

    }

    public static Results getInstance() {
        if (results == null) {
            results = new Results();
        }
        return results;
    }

    public ArrayList<Answer> list = new ArrayList();

    public ArrayList<Answer> getList() {
        return list;
    }

    public int getHowManyStudents() {
        return howManyStudents;
    }

    public void setHowManyStudents(int howManyStudents) {
        this.howManyStudents = howManyStudents;
    }
}

