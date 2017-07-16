package server.ConnectionWithStudent;

public class Questions {

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;
    private String category;
    private int teacherID;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public String toString(){
        return question + "\n" + answerA + " " + answerB  + " " + answerC  + " " + answerD +
                "\nCorrect: " + correctAnswer + " \nFrom category: " + category + " \nteacherID: "+teacherID +"\n";
    }
}
