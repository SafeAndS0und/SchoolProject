package server.ConnectionWithStudent;

import java.util.ArrayList;

/**
 * Created by Nikodem on 25.08.2017.
 */
public class Results {

    public String name = null;
    public String result = null;
    public int howManyStudents = 0;
    private static Results results;

    private Results(){

    }

    public static Results getInstance() {
        if(results==null){
            results = new Results();
        }
        return results;
    }

    public int getHowManyStudents() {
        return howManyStudents;
    }

    public void setHowManyStudents(int howManyStudents) {
        this.howManyStudents = howManyStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
